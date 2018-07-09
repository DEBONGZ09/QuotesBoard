package com.example.debongz.quotesboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

public class AdapterOpen extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataOpen> data= Collections.emptyList();
    DataOpen current;
    int currentPos=0;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterOpen(Context context, List<DataOpen> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_quotes, parent,false);
        AdapterOpen.MyHolder holder=new AdapterOpen.MyHolder(view);
        return holder;
    }
    // Bind data
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        AdapterOpen.MyHolder myHolder= (AdapterOpen.MyHolder) holder;
        DataOpen current=data.get(position);

        myHolder.txtQnum.setText(current.order_quote_number);
        myHolder.txtQshop_name.setText(current.order_cust_name);
        myHolder.txtQshop_area.setText(current.order_cust_area);
        //myHolder.txtQID.setText(current.order_id);
        myHolder.txtQdate.setText(current.order_date);
        //myHolder.txtQdate_required.setText(current.order_date_required);

        String diff = current.order_diff;
        if (diff.equalsIgnoreCase("okay")){
            myHolder.txtQnum.setTextColor(Color.parseColor("#777777"));
            myHolder.txtQshop_name.setTextColor(Color.parseColor("#777777"));
            myHolder.txtQshop_area.setTextColor(Color.parseColor("#777777"));
            myHolder.txtQdate.setTextColor(Color.parseColor("#777777"));
        }
        else  if (diff.equalsIgnoreCase("mid")){
            myHolder.txtQnum.setTextColor(Color.parseColor("#f2602b"));
            myHolder.txtQshop_name.setTextColor(Color.parseColor("#f2602b"));
            myHolder.txtQshop_area.setTextColor(Color.parseColor("#f2602b"));
            myHolder.txtQdate.setTextColor(Color.parseColor("#f2602b"));
        }
        else  if (diff.equalsIgnoreCase("high")){
            myHolder.txtQnum.setTextColor(Color.parseColor("#d10000"));
            myHolder.txtQshop_name.setTextColor(Color.parseColor("#d10000"));
            myHolder.txtQshop_area.setTextColor(Color.parseColor("#d10000"));
            myHolder.txtQdate.setTextColor(Color.parseColor("#d10000"));

        }


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

            quote_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String sepostid = textmPostID.getText().toString();
                    String q_number = txtQnum.getText().toString();
                    Intent intent = new Intent(context, AsignByTab.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("q_number", q_number);
                    context.startActivity(intent);
                    ((Activity)context).finish();



                }
            });
            quote_line.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Vibrator vi = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vi.vibrate(VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE));
                    }else{
                        //deprecated in API 26
                        vi.vibrate(100);
                    }

                    new AlertDialog.Builder(context)
                            .setTitle("Notice")
                            .setMessage("You are about to delete " + txtQshop_name.getText().toString()+"'s order")
                            .setCancelable(true)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    String q_number = txtQnum.getText().toString();
                                    Intent intent=new Intent(context, DeleteQuote.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("q_number", q_number);
                                    context.startActivity(intent);
                                    //do smth. with it.
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    return false;
                }
            });

        }


    }

}
