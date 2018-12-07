package com.example.carl.seg2105project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;


public class ServiceCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> myServices = new ArrayList<String>();
    private Context context;



    public ServiceCustomAdapter (ArrayList<String> serviceList, Context context) {
        this.myServices = serviceList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myServices.size();
    }

    @Override
    public Object getItem(int pos) {
        return myServices.get(pos);
    }

   @Override //may not work?? here to satisfy class
    public long getItemId(int pos) {
        return getItemId(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.service_item_layout, null);
        }

        //Handle TextView and display string from your list
        //@TODO check if this works, may only be displaying name and not also the hourly rate
        TextView listItemText = (TextView)view.findViewById(R.id.textView_serviceName);
        listItemText.setText(myServices.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.button_delete);
        Button addBtn = (Button)view.findViewById(R.id.button_edit);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                myServices.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
/*public class ServiceCustomAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<Service> myServices;

    public ServiceCustomAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.myServices = serviceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_item_layout, viewGroup,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SimpleViewHolder) viewHolder).bindData(myServices.get(i));
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.service_item_layout;
    }

    // @TODO
    // make this return the number of services on firebase
    @Override
    public int getItemCount() {
        return myServices.size();
    }
}*/
