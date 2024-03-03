package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doctors_details extends AppCompatActivity {
    private String [][] PHYSICIAN = {
            {"Name : Dr. Mehedi Hasan","Address : DMC ","Experience : 5 Years ","Fees : Tk. 2500"},
            {"Name : Md Fayaj Uddin ","Address : MMCH ","Experience : 7 Years ","Fees : Tk. 2500"},
            {"Name : Dr. Sumaiya ","Address : RMCH ","Experience : 5 Years ","Fees : Tk. 2500"},
            {"Name : Dr. Maruf ","Address : CMCH ","Experience : 3 Years ","Fees : Tk. 2500"},
            {"Name : Dr. Diner ","Address : Ibney Sina ","Experience : 5 Years ","Fees : TK. 1000"},
            {"Name : Miss. Monisha  ","Address : LAB AID ","Experience : 3 Years ","Fees : TK. 2000"}
    };
    private String [][] DENTIST= {
            {"Name : Dr. Asha ","Address : KUMUDINI WOMEN'S MEDICAL COLLAGE  ","Experience : 2 Years ","Fees : TK. 2000"},
            {"Name : Dr. Fayaj Uddin ","Address : Green Medical Collage ","Experience : 4 Years ","Fees : TK. 2000"},
            {"Name : Dr. Mehedi ","Address : BRB Hospital ","Experience : 4 Years ","Fees : TK. 1000"},
            {"Name : Dr. Mohon ","Address : Dhaka Dental Care ","Experience : 12 Years ","Fees : TK. 1800"},
            {"Name : Dr. Hasan ","Address : Dental Care Clinic ","Experience : 27 Years ","Fees : TK. 500"},
            {"Name : Dr. Akil ","Address : LAB Aid ","Experience : 1 Year ","Fees : TK. 620"}
    };
    private String [][] SURGEON= {
            {"Name : Dr. Anondo ","Address : DMC","Experience : 38 Years ","Fees : TK. 3000"},
            {"Name : Dr. Shakib","Address : GREEN HOSPITAL ","Experience : 7 Years ","Fees : TK. 1500"},
            {"Name : Dr. Maruf","Address : MMCH ","Experience : 18 Years ","Fees : TK. 2000"},
            {"Name : Prof. Dr. Mehedi ","Address : CMH ","Experience : 33 Years ","Fees : TK. 1000"},
            {"Name : Dr. Nishad ","Address : HART FOUNDATION ","Experience : 15 Years ","Fees :TK. 2000"},
            {"Name : Dr. Shimanto","Address : NATIONAL HART  FOUNDATION ","Experience : 20 Years ","Fees : TK. 1000"}
    };
    private String [][] DIETITIAN= {
            {"Name : Ms. Sithi Rani ","Address : EVER CARE ","Experience : 13 Years ","Fees : TK. 2500"},
            {"Name : Ms. Asha ","Address : DMCH ","Experience : 14 Years ","Fees : TK. 2500"},
            {"Name : Ms. MONI","Address : LAB AID ","Experience : 7 Years ","Fees : TK. 2000"},
            {"Name : Dr. Prattay ","Address : MMCH ","Experience : 25 Years ","Fees : TK. 2500"},
            {"Name : Ms. Wafia","Address : CMCH ","Experience : 2 Years ","Fees :TK. 2500"},
            {"Name : Dr. Hasan ","Address : CMH ","Experience : 8 Years ","Fees : TK. 1500"} } ;

    private String [][] CARDIOLOGIST={
            {"Name : Dr. Mehedi ","Address :POPULAR DIGONOSTIC   ","Experience : 12 Years ","Fees : TK. 1800"},
            {"Name : Dr. Md Fayaj Uddin ","Address : International Hospital ","Experience : 12 Years ","Fees : TK. 1500"},
            {"Name : Dr. Prattay","Address :  Medical Center ","Experience : 7 Years ","Fees : TK. 1500"},
            {"Name : Asha ","Address : GUB Diagnostic Center  ","Experience : 25Years ","Fees : TK. 2000"},
            {"Name : Dr. Mim ","Address : DMCH ","Experience : 7 Years ","Fees : TK. 2000"},
            {"Name : Assist. Prof. Dr. Mehedi  Hasan Anondo","Address : Al Safiya Medical and Diagnostic ","Experience : 18 Years ","Fees : TK. 1500"}
    };

    TextView doctext;
    ListView docdetails;
    Button backtomydoc;
    String [][] doc_details= {};
    ArrayList list;
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_details);
        doctext=findViewById(R.id.lab);
        docdetails=findViewById(R.id.cartdetails);
        backtomydoc=findViewById(R.id.backtomain);

        Intent it = getIntent();
        String title= it.getStringExtra("title");
        doctext.setText(title);

        if(title.compareTo("PHYSICIAN") == 0){
            doc_details = PHYSICIAN ;
        }
        if(title.compareTo("DENTIST") == 0){
            doc_details = DENTIST ;
        }
        if(title.compareTo("SURGEON") == 0){
            doc_details = SURGEON ;
        }
        if(title.compareTo("DIETITIAN") == 0){
            doc_details = DIETITIAN ;
        }
        if(title.compareTo("CARDIOLOGIST") == 0){
            doc_details = CARDIOLOGIST ;
        }
        

        backtomydoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctors_details.this,findmydoc.class));
            }
        });
        list = new ArrayList();
        for(int i = 0 ; i < doc_details.length;i++){
            item = new HashMap<String, String>();
            item.put("docname",doc_details[i][0]);
            item.put("docaddress",doc_details[i][1]);
            item.put("docexperience",doc_details[i][2]);
            item.put("docfees",doc_details[i][3]+" only");
            list.add(item);
        }
        SimpleAdapter adap = new SimpleAdapter(
                this,list,
                R.layout.detaildesign,
                new String[]{"docname","docaddress","docexperience","docfees"},
                new int[]{R.id.t_name, R.id.t_price,
                R.id.docexperience, R.id.docfees});
        docdetails.setAdapter(adap);
        docdetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(doctors_details.this,booking.class);
                intent.putExtra("docname",doc_details[i][0]);
                intent.putExtra("docaddress",doc_details[i][1]);
                intent.putExtra("docfees",doc_details[i][3]+" only");

                startActivity(intent);
            }
        });}


}