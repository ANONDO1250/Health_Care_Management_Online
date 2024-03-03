package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminLogin extends AppCompatActivity {
    EditText login_username ,login_password;
    Button login_button,userBtn;

    private RequestQueue requestQueue;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login_username= findViewById(R.id.r_email);
        login_password= findViewById(R.id.r_Cpassword);
        login_button= findViewById(R.id.add_cart_med);
        userBtn= findViewById(R.id.userBtn);

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this,Login.class));
            }
        });



        requestQueue = Volley.newRequestQueue(this);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();

            }
        });
    } //----oncreate end---

    private void loginUser() {
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        // Replace with your server URL
        String url = "https://onlineshp.scopesoftwarecompany.com/greentest/admin/adminlogin.php";

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
                                    Toast.makeText(AdminLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AdminLogin.this,HomeAdmin.class));
                                    return;
                                }
                            }
                            // Login failed
                            Toast.makeText(AdminLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AdminLogin.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }
}