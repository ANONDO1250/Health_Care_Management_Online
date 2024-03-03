package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeAdmin extends AppCompatActivity {
    LinearLayout labtest,buymedicine,findmydoc,articles,orders,logout,c_findamb,helpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

      //  labtest = findViewById(R.id.c_labtest);
       // buymedicine= findViewById(R.id.c_buymedicine);
      //  findmydoc = findViewById(R.id.c_finddoc);
        articles = findViewById(R.id.c_articles);
        orders = findViewById(R.id.c_orderdetails);
        logout = findViewById(R.id.logout);
     //   c_findamb=findViewById(R.id.c_findamb);
     //   helpline=findViewById(R.id.helpline);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                startActivity(new Intent(HomeAdmin.this,AdminLogin.class));
            }

        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this,TotalOrder.class));
            }
        });


        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this,Totaluser.class));

            }
        });


    } //---oncreate end---------
}