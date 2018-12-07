package com.example.carl.seg2105project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private String userEmail;
    private TextView textView;

    private Button serviceProviderEdit, serviceProviderView;


    private FloatingActionButton addService;

    private ListView myListView;
    public ListView adminView;
    private ArrayAdapter<String> myAdapter;
    ArrayList<String> listKeys = new ArrayList<String>();
    private ArrayList<String> myServices;

    private Boolean itemSelected = false;

    EditText serviceNameEdit;
    EditText serviceRateEdit;

    AlertDialog.Builder alertDialogBuilder;

    static String aType="";
    static String aEmail="";

    DatabaseReference ref1;
    DatabaseReference userServicesRef;
    Intent intent2;

    @Override
    public void onBackPressed() {
        intent2 = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent2);
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        serviceProviderEdit = findViewById(R.id.button_serviceProviderDetails);
        serviceProviderEdit.setOnClickListener(this);

        serviceProviderView = findViewById(R.id.button_serviceProviderView);
        serviceProviderView.setOnClickListener(this);
        addService = findViewById(R.id.floatingActionButton);


//<<<<<<< HEAD


        addService = findViewById(R.id.floatingActionButton);

        myListView = (ListView)findViewById(R.id.listView);
        adminView = (ListView)findViewById(R.id.listView);

        myServices = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(WelcomeActivity.this,  android.R.layout.simple_list_item_multiple_choice, myServices);
//=======
         myListView = (ListView)findViewById(R.id.listView);
         adminView = (ListView)findViewById(R.id.listView);

         myServices = new ArrayList<>();
         myAdapter = new ArrayAdapter<String>(WelcomeActivity.this,  android.R.layout.simple_list_item_1, myServices);

        //deleteServiceButton.setEnabled(false);
//>>>>>>> 97d9b7eb8c7d0ef94b134c671b7d49c0650f5f4f

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        userEmail = user.getEmail();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        ref1 = database.getReference("services");
        userServicesRef = database.getReference("users");


        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                switch(aType){
                    case "ServiceProvider":

                        String selectedItem =(String) (myListView.getItemAtPosition(position));
                        FirebaseUser user = mAuth.getCurrentUser();

                        Map<String, Object> userServicesUpdate = new HashMap<>();
                        Map<String, Object> servicesUpdate = new HashMap<>();

                        userServicesUpdate.put(selectedItem, selectedItem);
                        servicesUpdate.put(user.getUid(), user.getEmail());


                        //put it under the respective user
                        userServicesRef.child(user.getUid()).child("currentServices").updateChildren(userServicesUpdate);
                        ref1.child((String)(myListView.getItemAtPosition(position))).child("providers").updateChildren(servicesUpdate);
                        Toast.makeText(WelcomeActivity.this, (String)(myListView.getItemAtPosition(position))+" has been added to your list of services", Toast.LENGTH_LONG).show();

                        //put it under the services key
                        break;

                    case "HomeOwner":
                        Intent intent1 = new Intent(WelcomeActivity.this, service_providers.class);
                        Bundle b = new Bundle();
                        b.putString("selectedItem",(String) (myListView.getItemAtPosition(position)));
                        intent1.putExtras(b);
                        startActivity(intent1);
                        finish();
                        break;

                    case "Admin":
                        ref1.child((String)(myListView.getItemAtPosition(position))).removeValue();
                        Toast.makeText(WelcomeActivity.this, (String)(myListView.getItemAtPosition(position))+" has been deleted from services available", Toast.LENGTH_LONG).show();
                        break;
                }

                return true;
            }
        });



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textView = findViewById(R.id.textView_welcomeMessage);

                for(DataSnapshot userSnap : dataSnapshot.getChildren()){
                    User userInfo = userSnap.getValue(User.class);
                    if(userInfo.getEmail().equals(userEmail)){
                        aType=userInfo.getAccountType();
                        aEmail=userEmail;
                    }


                }
                String prompt = new String("");
                if (aType.matches("Admin")){
                    textView.setText("Delete: long press & Edit: click");

                }
                else if (aType.matches("ServiceProvider")){
                    textView.setText("Hey Service Provider, if you long press you can add the service to your list of services");
                }
                else{
                    textView.setText("Hey Home Owner, if you long press you can see the list of SPs that can provide this service you have selected & their info!");
                }



                if(!aType.equals("ServiceProvider")) {
                    serviceProviderEdit.setVisibility(View.GONE);
                    serviceProviderView.setVisibility(View.GONE);
                }

                if(!aType.equals("Admin")) {
                    addService.hide();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myServices.clear();

                for(DataSnapshot dataSnapshotService : dataSnapshot.getChildren()){
                    Service service = dataSnapshotService.getValue(Service.class);
                    myServices.add(service.getServiceName());
                }
                myAdapter.notifyDataSetChanged();
                myListView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(WelcomeActivity.this, aType, Toast.LENGTH_LONG).show();
                        if (aType.matches("Admin")){
                            ref1.child((String)(myListView.getItemAtPosition(position))).removeValue();
                            View inpView = findViewById(R.id.serviceVieww);
                            addServiceOnClick(inpView);
                        }
                    }
                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_services, menu);
        MenuItem item = menu.findItem(R.id.listView);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_serviceProviderDetails:
                goToEditServiceProviderInfo(view);
                break;
            case R.id.button_serviceProviderView:
                showSPInfo(view);
                break;
        }
    }

    public void addServiceOnClick(View view) {
        if(aType.equals("HomeOwner")){
            Toast.makeText(getApplicationContext(), "You are a HomeOwner you cannot add a service! Sign in as an Admin to add services.", Toast.LENGTH_SHORT).show();
        }
        else{
            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(this);
            View promptsView = li.inflate(R.layout.dialog_layout, null);

            alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set prompts.xml to alert dialog builder
            alertDialogBuilder.setView(promptsView);

            alertDialogBuilder.setTitle("Add a Service");

            alertDialogBuilder.show();

            serviceNameEdit = (EditText) promptsView
                    .findViewById(R.id.nameEdit);

            serviceRateEdit = (EditText) promptsView
                    .findViewById(R.id.rateEdit);

            alertDialogBuilder.setCancelable(true);
        }
    }



    public void goToEditServiceProviderInfo(View view) {
        Intent intent = new Intent(this, ServiceProviderInfo.class);
        startActivity(intent);
    }

    public void showSPInfo(View view) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.sp_info, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        TextView tv = popupWindow.getContentView().findViewById(R.id.TextView_SPInfo);


        serviceProviderCondenser spc = new serviceProviderCondenser();
        spc = spc.getServiceProviderProfile(aEmail, FirebaseDatabase.getInstance());
        tv.setText(spc.toString());


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }


    public void addToDatabase(View v) {

        String name = serviceNameEdit.getText().toString();
        String rate = serviceRateEdit.getText().toString();

       Service service = new Service(name, rate);

//<<<<<<< HEAD
       //VALIDATION OF SERVICE RATE AND NAME
       if (service.getServiceName().matches("") || service.getServiceRate().matches("")) {
           if (service.getServiceName().matches("") && service.getServiceRate().matches("")){
               Toast.makeText(getApplicationContext(), "You did not enter a service name or service rate", Toast.LENGTH_SHORT).show();

           }
           else if (service.getServiceName().matches("")){
               Toast.makeText(getApplicationContext(), "You did not enter a service name", Toast.LENGTH_SHORT).show();
           }
           else {
               Toast.makeText(getApplicationContext(), "You did not enter a service rate", Toast.LENGTH_SHORT).show();

           }

        }

        else {

           if (service.getServiceRate().matches("-?\\d+(.\\d+)?") == false){
               Toast.makeText(getApplicationContext(), "Please input a numeric value for the service rate", Toast.LENGTH_SHORT).show();
           }
           else{
               ref1.child(service.getServiceName()).setValue(service);
               Intent intent = new Intent(this, WelcomeActivity.class);
               startActivity(intent);

           }
       }
    }

}
