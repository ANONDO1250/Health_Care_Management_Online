package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class Totaluser extends AppCompatActivity {

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
        setContentView(R.layout.activity_totaluser);

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
            View myView=layoutInflater.inflate(R.layout.totluser_design,null);


            @SuppressLint({"MissingInflatedId", "LocalSuppress"})

            TextView Email=myView.findViewById(R.id.Email);
            TextView username=myView.findViewById(R.id.username);
            TextView phnmber=myView.findViewById(R.id.phnmber);
            TextView password=myView.findViewById(R.id.password);


            HashMap<String,String> hashMap=arrayList.get(i);
         //   String id=hashMap.get("id");
            String email=hashMap.get("email");
            String username5=hashMap.get("username");
            String phone=hashMap.get("phone");
            String password5=hashMap.get("password");


            //======================================================
            //  String pic_title=hashMap.get("pic_title");

         /*   Picasso.get().load(pic_title)
                    .placeholder(R.drawable.main_home_icon)
                    .into(d1_img1);

          */ //===================================================
            Email.setText(email);
            username.setText(username5);
            phnmber.setText(phone);
            password.setText(password5);


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
        String url = "http://onlineshp.scopesoftwarecompany.com/greentest/signupview.php";



        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.d("serverres", jsonArray.toString());
                simpleProgressBar.setVisibility(View.GONE);

                try {
                    for (int x = 0; x < jsonArray.length(); x++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String email = jsonObject.getString("email");
                        String username = jsonObject.getString("username");
                        String phone = jsonObject.getString("phone");
                        String password = jsonObject.getString("password");


                        hashMap = new HashMap<>();
                        hashMap.put("email", email);
                        hashMap.put("username", username);
                        hashMap.put("phone", phone);
                        hashMap.put("password", password);


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

        RequestQueue requestQueue = Volley.newRequestQueue(Totaluser.this);
        requestQueue.add(arrayRequest);

        // set the maximum width and height of the ImageView to 200 pixels


    }
}