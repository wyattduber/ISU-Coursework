package com.example.android_unit2_1_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStringRequest;
    private Button jsonRequest;
    private Button imageRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link button to code
        btnStringRequest =findViewById(R.id.btnstr);
        jsonRequest =findViewById(R.id.btnjson);
        imageRequest =findViewById(R.id.btnimg);

    //send to string page
    btnStringRequest.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, StringrequestActivity.class);
            startActivity(intent);
        }

});

    //send to json page
    jsonRequest.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, JsonActivity.class);
            startActivity(intent);
        }
    });

    //send to image page
    imageRequest.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
           Intent intent = new Intent(MainActivity.this,ImageActivity.class);
           startActivity(intent);
        }
    });


}
}
