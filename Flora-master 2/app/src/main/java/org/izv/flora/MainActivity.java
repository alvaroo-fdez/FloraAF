package org.izv.flora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.flora.adapter.FloraAdapter;
import org.izv.flora.adapter.FloraViewHolder;
import org.izv.flora.model.entity.CreateResponse;
import org.izv.flora.model.entity.RowsResponse;
import org.izv.flora.model.entity.Flora;
import org.izv.flora.model.api.FloraClient;
import org.izv.flora.view.AddFloraActivity;
import org.izv.flora.view.AddImagenActivity;
import org.izv.flora.viewmodel.AddFloraViewModel;
import org.izv.flora.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloraClient floraClient;
    private FloatingActionButton fabAdd;
    public static int dondepasa;
    public static String nombre;
    private SwipeRefreshLayout refreshLayout;
    public static long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        refreshLayout = findViewById(R.id.swipeRefreshLayout);

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> openAddActivity());

        dondepasa=0;
        nombre = "";
        RecyclerView rvFlora = findViewById(R.id.rvFlora);
        rvFlora.setLayoutManager(new LinearLayoutManager(this));

        FloraAdapter floraAdapter = new FloraAdapter(this);
        rvFlora.setAdapter(floraAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivityViewModel mavm = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);
                mavm.getFloraLiveData().observe(MainActivity.this, floraPlural -> {
                    //recyclerview
                    floraAdapter.setFloraList(floraPlural);
                });
                mavm.getFlora();
                refreshLayout.setRefreshing(false);
            }
        });

        MainActivityViewModel mavm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mavm.getFloraLiveData().observe(this, floraPlural -> {
            //recyclerview
            floraAdapter.setFloraList(floraPlural);
        });
        mavm.getFlora();
    }

    private void openAddImagenActivity() {
        Intent intent = new Intent(this, AddImagenActivity.class);
        startActivity(intent);
    }

    private void openAddActivity() {
        Intent intent = new Intent(this, AddFloraActivity.class);
        startActivity(intent);
    }
}