package com.example.car_service_wecarcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class lcAddActivity extends AppCompatActivity {

    EditText sertype,cartype,fuelexpert,picktime,delitime,extrachar;
    Button btnAdd,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_add);

        sertype = (EditText) findViewById(R.id.sertypetext);
        cartype = (EditText)findViewById(R.id.cartypetext);
        fuelexpert = (EditText)findViewById(R.id.fuelexperttext);
        picktime = (EditText)findViewById(R.id.picktimetext);
        delitime = (EditText)findViewById(R.id.delitimetext);
        extrachar = (EditText)findViewById(R.id.extrachartext);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

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
        Map<String,Object> map = new HashMap<>();
        map.put("sertype",sertype.getText().toString());
        map.put("cartype",cartype.getText().toString());
        map.put("fuelexpert",fuelexpert.getText().toString());
        map.put("picktime",picktime.getText().toString());
        map.put("delitime",delitime.getText().toString());
        map.put("extrachar",extrachar.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("vehicles").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(lcAddActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(lcAddActivity.this, "Error While Creating a Loaner Car Request", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        sertype.setText("");
        cartype.setText("");
        fuelexpert.setText("");
        picktime.setText("");
        delitime.setText("");
        extrachar.setText("");

    }
}