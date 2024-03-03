package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {
    EditText login_username ,login_password;
    Button login_button,adminBtn;
    TextView register_nu;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        login_username= findViewById(R.id.r_email);
        login_password= findViewById(R.id.r_Cpassword);
        login_button= findViewById(R.id.add_cart_med);
        register_nu= findViewById(R.id.registered);
        adminBtn=findViewById(R.id.adminBtn);
     //   Database db = new Database(getApplicationContext(),"myhealth",null,1);

        //here i removed sqlite method and add mysql
        requestQueue = Volley.newRequestQueue(this);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//startActivity(new Intent(Login.this,Home.class));
                loginUser();

            }
        });

    register_nu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this,Register.class));
                }
            } );

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,AdminLogin.class));

            }
        });
    }   //-----oncreate end---------

    private void loginUser() {
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        // Replace with your server URL
        String url = "https://onlineshp.scopesoftwarecompany.com/greentest/signupview.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject user = response.getJSONObject(i);
                                String storedUsername = user.getString("username");
                                String storedPassword = user.getString("password");

                                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                                    // Login successful
                                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,Home.class));
                                    return;
                                }
                            }
                            // Login failed
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        // Handle error
                        Toast.makeText(Login.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }
}