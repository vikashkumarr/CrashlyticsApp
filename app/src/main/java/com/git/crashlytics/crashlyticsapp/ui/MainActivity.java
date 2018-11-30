package com.git.crashlytics.crashlyticsapp.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.git.crashlytics.core.common.ApiClient;
import com.git.crashlytics.core.common.ApiInterface;
import com.git.crashlytics.crashlyticsapp.R;
import com.git.crashlytics.crashlyticsapp.adapter.CrashlyticsAdapter;
import com.git.crashlytics.crashlyticsapp.model.CraslyticsIssues;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CrashlyticsAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CraslyticsIssues>> call = service.getIssuesList();
        call.enqueue(new Callback<List<CraslyticsIssues>>() {
            @Override
            public void onResponse(Call<List<CraslyticsIssues>> call, Response<List<CraslyticsIssues>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<CraslyticsIssues>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<CraslyticsIssues> issuess) {
        recyclerView = findViewById(R.id.recyclerview_issues);
        adapter = new CrashlyticsAdapter(this,issuess);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}

