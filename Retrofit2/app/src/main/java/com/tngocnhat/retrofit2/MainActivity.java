package com.tngocnhat.retrofit2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tngocnhat.retrofit2.adapter.CategoryAdapter;
import com.tngocnhat.retrofit2.model.Category;
import com.tngocnhat.retrofit2.network.BaseClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://app.iotstar.vn:8081/";

    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategories = findViewById(R.id.rvCategories);
        progressBar = findViewById(R.id.progressBar);

        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this);
        rvCategories.setAdapter(adapter);

        fetchCategories();
    }

    private void fetchCategories() {
        progressBar.setVisibility(View.VISIBLE);

        APIService service = BaseClient.createService(BASE_URL, APIService.class);
        Call<List<Category>> call = service.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    List<Category> list = response.body();
                    if (list == null) list = new ArrayList<>();

                    adapter.setItems(list);  // <-- CHỈ CẦN DÒNG NÀY

                } else {
                    Toast.makeText(MainActivity.this, "Response error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
