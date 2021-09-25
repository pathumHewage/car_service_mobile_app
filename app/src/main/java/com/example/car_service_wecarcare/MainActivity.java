package com.example.car_service_wecarcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainDsAdapter  mainDsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   doorStep

        recyclerView = (RecyclerView) findViewById(R.id.dsrv);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MainDsModel> options =
                new FirebaseRecyclerOptions.Builder<MainDsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("request"), MainDsModel.class)
                        .build();

        mainDsAdapter = new MainDsAdapter(options);
        recyclerView.setAdapter(mainDsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainDsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainDsAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        

        return super.onCreateOptionsMenu(menu);

      
    }
}