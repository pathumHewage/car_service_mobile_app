package com.example.car_service_wecarcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addDsActivity extends AppCompatActivity {

    EditText name,phoneNo,location,brand,vModel,vMake,fuelType,date;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ds);

        name = (EditText) findViewById(R.id.txtName);
        phoneNo = (EditText) findViewById(R.id.txtPhone);
        location = (EditText) findViewById(R.id.txtLocation);
        brand = (EditText) findViewById(R.id.txtbrand);
        vModel = (EditText) findViewById(R.id.txtvMake);
        vMake = (EditText) findViewById(R.id.txtvMake);
        fuelType = (EditText) findViewById(R.id.txtfuelType);
        date = (EditText) findViewById(R.id.txtdate);

       btnAdd = (Button) findViewById(R.id.btnAdd);
       btnBack = (Button) findViewById(R.id.btnBack);

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               insertData();
               clearAll();

           }
       });

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });



    }

    private void insertData()
    {
        Map<String,Object> map =new HashMap<>();

        map.put("name",name.getText().toString());
        map.put("phoneNo",phoneNo.getText().toString());
        map.put("location",location.getText().toString());
        map.put("brand",brand.getText().toString());
        map.put("vModel",vModel.getText().toString());
        map.put("vMake",vMake.getText().toString());
        map.put("fuelType",fuelType.getText().toString());
        map.put("date",date.getText().toString());


        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().child("request").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addDsActivity.this, "Data insertion Successfully", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(addDsActivity.this, "Error Data insertion ", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll()
    {
        name.setText("");
        phoneNo.setText("");
        location.setText("");
        brand.setText("");
        vModel.setText("");
        vMake.setText("");
        fuelType.setText("");
        date.setText("");

    }

}