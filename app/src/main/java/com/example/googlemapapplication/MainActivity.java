package com.example.googlemapapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    EditText esourese,edestinstion;
    Button trackbtn,currebtn,searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        esourese=(EditText)findViewById(R.id.et_source);
        edestinstion=(EditText)findViewById(R.id.et_destination);
        currebtn=(Button)findViewById(R.id.btn_curre);
        searchbtn=(Button)findViewById(R.id.btn_search);
        trackbtn=(Button)findViewById(R.id.btn_track);
        trackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourcestr=esourese.getText().toString().trim();
                String destinationstr=edestinstion.getText().toString().trim();
                if (sourcestr.equals("")&&destinationstr.equals("")){
                    Toast.makeText(MainActivity.this, "Enter both location", Toast.LENGTH_SHORT).show();
                }else {
                    DisplayTrack(sourcestr,destinationstr);
                }
            }

            private void DisplayTrack(String sourcestr, String destinationstr) {
                try {
                    Uri uri=Uri.parse("https://www.google.co.in/maps/dir/"+sourcestr+"/"+destinationstr);
                    Intent i=new Intent(Intent.ACTION_VIEW,uri);
                    i.setPackage("com.google.android.apps.maps");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                }catch (ActivityNotFoundException e){
                    Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    Intent i=new Intent(Intent.ACTION_VIEW,uri);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }


        });
        currebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CurrentLocationActivity.class);
                startActivity(i);
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });
    }
}