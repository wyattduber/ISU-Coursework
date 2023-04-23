package com.example.basicapicalls;

import static com.example.basicapicalls.api.ApiClientFactory.GetPhotoApi;
import static com.example.basicapicalls.api.ApiClientFactory.GetPostApi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.basicapicalls.api.SlimCallback;
import com.example.basicapicalls.model.Post;
import com.example.basicapicalls.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView apiText1 = findViewById(R.id.activitymain_tv1);
        apiText1.setMovementMethod(new ScrollingMovementMethod());

        Button photoButton = findViewById(R.id.btn_getPhoto);
        EditText photoNumInput = findViewById(R.id.activityMain_PhotoNumInput);


        //text box is now scrollable
        //this is for multiple ones to go in same text box

//

//GetPhotoApi().getAllPhoto().enqueue(new SlimCallback<List<Photo>>(photos -> {
//           apiText1.setText("");
//           for (int i = 0; i < photos.size(); i++) {
//               apiText1.append(photos.get(i).printable());
//
//          }
//      }, "multiple photos api"));
//


        //for one photo
//        GetPhotoApi().getFirstPhoto().enqueue(new SlimCallback<Photo>(responsePhoto -> {
//            apiText1.setText(responsePhoto.printable());
//        }));



       photoButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               GetPhotoApi().getPhotoByNum(photoNumInput.getText().toString()).enqueue(new SlimCallback<Photo>(photo -> {
                   apiText1.setText(photo.printable());
               }));
           }
       });


    }
}




