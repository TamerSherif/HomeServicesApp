package com.example.carl.seg2105project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class service_providers extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayAdapter<String> myArrayAdapter;
    ListView myListView;
    Intent intent1;

    @Override
    public void onBackPressed() {
        intent1 = new Intent(service_providers.this, WelcomeActivity.class);
        startActivity(intent1);
        return;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers);
        myListView = findViewById(R.id.listView2);
        myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArrayList);

        Bundle b = getIntent().getExtras();
        String selectedItem = "";
        if(b != null)
            selectedItem = b.getString("selectedItem");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("services").child(selectedItem).child("providers");
        myListView.setAdapter(myArrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotProvider : dataSnapshot.getChildren()) {
                    String myChildValues = dataSnapshotProvider.getValue(String.class);

                    myListView.setAdapter(myArrayAdapter);
                    myArrayList.add(myChildValues);
                }
                myArrayAdapter.notifyDataSetChanged();
                myListView.setAdapter(myArrayAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String aEmail = (String)myListView.getItemAtPosition(position);

                return void;
            } });*/

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aEmail = (String)myListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), aEmail, Toast.LENGTH_SHORT).show();

                showSPInfo(findViewById(R.id.listView2), aEmail);
            }

            public void showSPInfo(View view, String aEmail) {

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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                spc = spc.getServiceProviderProfile(aEmail, database);
                tv.setText(spc.toString());


                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }

            });







}}
