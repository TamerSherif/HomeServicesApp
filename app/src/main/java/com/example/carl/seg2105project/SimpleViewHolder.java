package com.example.carl.seg2105project;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView serviceName;
    private TextView serviceRate;
    private Button deleteButton;
    private Button editButton;

    public SimpleViewHolder(final View itemView) {
        super(itemView);
        serviceName = (TextView) itemView.findViewById(R.id.textView_serviceName);
        serviceRate = (TextView) itemView.findViewById(R.id.textView_serviceRate);
        deleteButton = itemView.findViewById(R.id.button_delete);
        editButton = itemView.findViewById(R.id.button_edit);
    }

    public void bindData(final Service viewModel) {
        serviceName.setText(viewModel.getServiceName());
        serviceRate.setText(viewModel.getServiceRate());
    }

}
