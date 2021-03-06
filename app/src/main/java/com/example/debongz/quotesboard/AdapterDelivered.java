package com.example.debongz.quotesboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by DEBONGZ on 4/19/2018.
 */

public class AdapterDelivered extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataOpen> data= Collections.emptyList();
    DataOpen current;
    int currentPos=0;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterDelivered(Context context, List<DataOpen> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_quotes, parent,false);
        AdapterDelivered.MyHolder holder=new AdapterDelivered.MyHolder(view);
        return holder;
    }
    // Bind data
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        AdapterDelivered.MyHolder myHolder= (AdapterDelivered.MyHolder) holder;
        DataOpen current=data.get(position);

        myHolder.txtQnum.setText(current.order_quote_number);
        myHolder.txtQshop_name.setText(current.order_cust_name);
        myHolder.txtQshop_area.setText(current.order_cust_area);
        //myHolder.txtQID.setText(current.order_id);
        myHolder.txtQdate.setText(current.order_date);
        //myHolder.txtQdate_required.setText(current.order_date_required);


    }
    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  {
        RelativeLayout quote_line;
        TextView txtQnum;
        TextView txtQshop_name;
        TextView txtQshop_area;
        //TextView txtQID;
        TextView txtQdate;
        //TextView txtQdate_required;




        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            quote_line=  (RelativeLayout)  itemView.findViewById(R.id.quote_line);
            txtQnum = (TextView) itemView.findViewById(R.id.aqnumber);
            txtQshop_name = (TextView) itemView.findViewById(R.id.acust_name);
            txtQshop_area = (TextView) itemView.findViewById(R.id.aarea);
            //txtQID = (TextView) itemView.findViewById(R.id.likes_user_txt);
            txtQdate = (TextView) itemView.findViewById(R.id.adate);
            //txtQdate_required = (TextView) itemView.findViewById(R.id.likes_user_txt);


        }


    }

}
