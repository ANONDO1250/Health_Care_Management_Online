package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

public class Ambulance extends AppCompatActivity {

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
        setContentView(R.layout.activity_ambulance);

        recyclerview1=findViewById(R.id.recyclerview1);
        simpleProgressBar=findViewById(R.id.simpleProgressBar);
        Createtable1();




    } //-onCreate End-------------

    //---------oncreate end---------------

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
            View myView=layoutInflater.inflate(R.layout.ambulance_design_part,null);
            ImageView dial_01_image=myView.findViewById(R.id.dial_01_image);
            ImageView dial_02_image=myView.findViewById(R.id.dial_02_image);
            ImageView copy_image_01=myView.findViewById(R.id.copy_image_01);
            ImageView copy_image_02=myView.findViewById(R.id.copy_image_02);

            TextView ambulance_man_name=myView.findViewById(R.id.ambulance_man_name);
            TextView number1_text=myView.findViewById(R.id.number1_text);
            TextView number2_text=myView.findViewById(R.id.number2_text);
            TextView station_name_fire=myView.findViewById(R.id.station_name_fire);

            HashMap<String,String> hashMap=arrayList.get(i);
            String station_name=hashMap.get("station_name");
            String ambulnc_man_name1=hashMap.get("ambulnc_man_name");
            String num1=hashMap.get("num_01");
            String num2=hashMap.get("num_02");

            //======================================================
            //  String pic_title=hashMap.get("pic_title");

         /*   Picasso.get().load(pic_title)
                    .placeholder(R.drawable.main_home_icon)
                    .into(d1_img1);

          */ //===================================================
            station_name_fire.setText(station_name);
            ambulance_man_name.setText(ambulnc_man_name1);
            number1_text.setText(num1);
            number2_text.setText(num2);

//====================================================
            //============1st dial start=============================================
            dial_01_image.setOnClickListener(new View.OnClickListener() {
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
            //============1nd dial end  ===============================================


            // ============2nd dial start=============================================

            dial_02_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    for (int  i=0;i<arrayList.size();i++){
                        for (int num2 = 0; num2<11; num2++)
                        {

                        }
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(num2), null));
                        startActivity(intent);

                    }

                }
            });
            //==============2nd dial end=====================

//============start copy 1 click =========================================
            copy_image_01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Context context = getApplicationContext();
                    Toast.makeText(context, "Copied", Toast.LENGTH_LONG).show();

                    for (int  i=0;i<arrayList.size();i++){

                        for (int num1= 0; num1<11; num1++){



                        }
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",num1);
                        clipboard.setPrimaryClip(clip);




                    }



                }
            });
            //===========end copy 1 click=================

            //==========start copy 2 click==================================
            copy_image_02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Context context = getApplicationContext();
                    Toast.makeText(context, "Copied", Toast.LENGTH_LONG).show();

                    for (int  i=0;i<arrayList.size();i++){

                        for (int num2= 0; num2<11; num2++){


                        }
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",num2);
                        clipboard.setPrimaryClip(clip);




                    }



                }
            });
//===========================================================================================
            return myView;


        }






    }



    //-----------------



    //========create table 1 start==================

    //-----------------------------------
    public void Createtable1 () {

        String url = "http://onlineshp.scopesoftwarecompany.com/greentest/ambulance.php";



        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.d("serverres", jsonArray.toString());
                simpleProgressBar.setVisibility(View.GONE);

                try {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String station_name = jsonObject.getString("station_name");
                        String ambulnc_man_name = jsonObject.getString("ambulnc_man_name");
                        String num_01 = jsonObject.getString("num_01");
                        String num_02 = jsonObject.getString("num_02");

                        hashMap = new HashMap<>();
                        hashMap.put("station_name", station_name);
                        hashMap.put("ambulnc_man_name", ambulnc_man_name);
                        hashMap.put("num_01", num_01);
                        hashMap.put("num_02", num_02);

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

        RequestQueue requestQueue = Volley.newRequestQueue(Ambulance.this);
        requestQueue.add(arrayRequest);

        // set the maximum width and height of the ImageView to 200 pixels


    }


}