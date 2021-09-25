package com.example.car_service_wecarcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class DsMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainDsAdapter  mainDsAdapter;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.dsrv);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));



        FirebaseRecyclerOptions<MainDsModel> options =
                new FirebaseRecyclerOptions.Builder<MainDsModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().child("request"), MainDsModel.class)
                        .build();

        mainDsAdapter = new MainDsAdapter(options);
        recyclerView.setAdapter(mainDsAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DsMainActivity.this,addDsActivity.class));

            }
        });

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

        getMenuInflater().inflate(R.menu.searchds,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<MainDsModel> options =
                new FirebaseRecyclerOptions.Builder<MainDsModel>()
                        .setQuery(FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().child("request").orderByChild("name").startAt(str).endAt(str+"~"), MainDsModel.class)
                        .build();

        mainDsAdapter = new MainDsAdapter(options);
        mainDsAdapter.startListening();
        recyclerView.setAdapter(mainDsAdapter);

    }
}