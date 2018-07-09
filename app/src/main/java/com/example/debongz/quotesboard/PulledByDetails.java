package com.example.debongz.quotesboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PulledByDetails extends AppCompatActivity {
  TextView quote, shopname;
  ImageView asigner, puller;
  ProgressBar myp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulled_by_details);
        getSupportActionBar().hide();
        quote = (TextView)findViewById(R.id.qntxt);
        shopname = (TextView)findViewById(R.id.sntxt);

        asigner = (ImageView)findViewById(R.id.byimg);
        puller = (ImageView)findViewById(R.id.toimg);

        Intent in = getIntent();
        String q_number= in.getExtras().getString("q_number");
        lookdetails(q_number);



    }
    public void lookdetails(final String q_number){

        RequestQueue requestQueue = Volley.newRequestQueue(PulledByDetails.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_FETCH_PULLED_BY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(PulledByDetails.this, "res: "+response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String q_number = jsonObject1.getString("q_number");
                    String shop_name = jsonObject1.getString("shop_name");
                    String asigner_sig = jsonObject1.getString("asigner_sig");
                    String puller_sig = jsonObject1.getString("puller_sig");

                    quote.setText(q_number);
                    shopname.setText(shop_name);


                    Glide.with(PulledByDetails.this)
                            .load(asigner_sig)
                            .fitCenter()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .dontAnimate()
                            .into(asigner);

                    Glide.with(PulledByDetails.this)
                            .load(puller_sig)
                            .fitCenter()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .dontAnimate()
                            .into(puller);



                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//show the red progressbar

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("q_number",q_number);

                return stringMap;
            }

        };

        requestQueue.add(stringRequest);
        //initialize the progress dialog and show it



        //mybarlook.setVisibility(View.VISIBLE);
    }
}
