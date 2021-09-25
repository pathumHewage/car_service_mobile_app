package com.example.car_service_wecarcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    lcMainAdapter lcmainAdpater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<lcMainModel> options =
                new FirebaseRecyclerOptions.Builder<lcMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vehicles"), lcMainModel.class)
                        .build();

        lcmainAdpater = new lcMainAdapter(options);
        recyclerView.setAdapter(lcmainAdpater);

    }

    @Override
    protected void onStart() {
        super.onStart();
        lcmainAdpater.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        lcmainAdpater.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.lcsearch,menu);
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
        FirebaseRecyclerOptions<lcMainModel> options =
                new FirebaseRecyclerOptions.Builder<lcMainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("vehicles").orderByChild("sertype").startAt(str).endAt(str+"~"), lcMainModel.class)
                        .build();

        lcmainAdpater = new lcMainAdapter(options);
        lcmainAdpater.startListening();
        recyclerView.setAdapter(lcmainAdpater);

    }
}



































