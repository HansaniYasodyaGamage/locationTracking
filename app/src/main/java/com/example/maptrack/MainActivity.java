package com.example.maptrack;

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

    EditText etSource,etDestination;
    Button btnTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btnTrack = findViewById(R.id.bt_track);

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                if (sSource.equals("") && sDestination.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Enter both location", Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sSource,sDestination);
                }
            }
        });

    }
        private void DisplayTrack(String sSource,String sDestination){
        //If the device does not have a map installed, then redirect it to play sore
            try{
                Uri uri=Uri.parse("http://www.google.co.in/maps/dir/"+sSource+"/"+sDestination);
        //Initialize intent with action view
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                //set package
                intent.setPackage("com.google.android.apps.maps");

                // set flag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
        }catch (ActivityNotFoundException e){
                //when google map is not installed
                //initialize uri
                Uri uri=Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps");

                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                //
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
    }
}