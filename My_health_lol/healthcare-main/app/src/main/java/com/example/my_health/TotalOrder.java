package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TotalOrder extends AppCompatActivity {

    GridView recyclerview1;
    //String server="";
    ProgressBar simpleProgressBar;
    // LottieAnimationView loading;
    //  private LottieAnimationView loading;
    // private Handler handler = new Handler(Looper.getMainLooper());


    //========For Recycle1 declear================ ======
    ArrayList<HashMap<String, String>> arrayList = new ArrayList();
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_order);

        recyclerview1=findViewById(R.id.recyclerview1);
        simpleProgressBar=findViewById(R.id.simpleProgressBar);
        Createtable1();

    }//----oncrate end----

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater= getLayoutInflater();
            View myView=layoutInflater.inflate(R.layout.order_design,null);


            @SuppressLint({"MissingInflatedId", "LocalSuppress"})

            TextView orderlist=myView.findViewById(R.id.orderlist);
            TextView customername=myView.findViewById(R.id.customername);
            TextView prdctname=myView.findViewById(R.id.prdctname);
            TextView phnmber=myView.findViewById(R.id.phnmber);
            TextView adress=myView.findViewById(R.id.adress);
            TextView tnjksn=myView.findViewById(R.id.tnjksn);
            TextView totaltaka=myView.findViewById(R.id.totaltaka);

            HashMap<String,String> hashMap=arrayList.get(i);
            String id=hashMap.get("id");
            String cstmr_name=hashMap.get("cstmr_name");
            String c_phone=hashMap.get("c_phone");
            String adress5=hashMap.get("adress");
            String trnx_id=hashMap.get("trnx_id");
            String prudct_name=hashMap.get("prudct_name");
            String finalTotalamount=hashMap.get("finalTotalamount");

            //======================================================
            //  String pic_title=hashMap.get("pic_title");

         /*   Picasso.get().load(pic_title)
                    .placeholder(R.drawable.main_home_icon)
                    .into(d1_img1);

          */ //===================================================
            orderlist.setText(id);
            customername.setText(cstmr_name);
            prdctname.setText(prudct_name);
            phnmber.setText(c_phone);
            adress.setText(adress5);
            tnjksn.setText(trnx_id);
            totaltaka.setText(finalTotalamount);

//====================================================
            //============1st dial start=============================================
          /*  dial_01_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int  i=0;i<arrayList.size();i++){
                        for (int num1 = 0; num1<11; num1++)
                        {

                        }
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(num1), null));
                        startActivity(intent);

                    }

                }
            });

           */
            //============1nd dial end  ===============================================


            // ============2nd dial start=============================================


//===========================================================================================
            return myView;


        }






    }



    //-----------------



    //========create table 1 start==================

    //-----------------------------------
    public void Createtable1 () {

        //---by this link , we get total order list
        String url = "http://onlineshp.scopesoftwarecompany.com/greentest/orderview.php";



        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.d("serverres", jsonArray.toString());
                simpleProgressBar.setVisibility(View.GONE);

                try {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String cstmr_name = jsonObject.getString("cstmr_name");
                        String c_phone = jsonObject.getString("c_phone");
                        String adress = jsonObject.getString("adress");
                        String trnx_id = jsonObject.getString("trnx_id");
                        String prudct_name = jsonObject.getString("prudct_name");
                        String finalTotalamount = jsonObject.getString("finalTotalamount");
                        String id = jsonObject.getString("id");

                        hashMap = new HashMap<>();
                        hashMap.put("cstmr_name", cstmr_name);
                        hashMap.put("c_phone", c_phone);
                        hashMap.put("adress", adress);
                        hashMap.put("trnx_id", trnx_id);
                        hashMap.put("prudct_name", prudct_name);
                        hashMap.put("finalTotalamount", finalTotalamount);
                        hashMap.put("id", id);

                        arrayList.add(hashMap);
                    }
                    //   Adapter.notifyDataSetChanged();

                    MyAdapter myAdapter=new MyAdapter();
                    recyclerview1.setAdapter(myAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(TotalOrder.this);
        requestQueue.add(arrayRequest);

        // set the maximum width and height of the ImageView to 200 pixels


    }
}