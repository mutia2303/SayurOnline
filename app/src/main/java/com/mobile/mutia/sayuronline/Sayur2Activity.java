package com.mobile.mutia.sayuronline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.adapter.AdapterSayur;
import com.mobile.mutia.sayuronline.model.ResponseSayur;
import com.mobile.mutia.sayuronline.model.Sayur;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sayur2Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView;

    private TextView textView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayur2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Sayur");

        textView = (TextView)findViewById(R.id.textview);
        progressBar = findViewById(R.id.progress);

        ButterKnife.bind(this);
        dataSayur2();
    }

    private void dataSayur2() {

        ConfigRetrofit.getInstance().getApi().getSayur2().enqueue(new Callback<ResponseSayur>() {
            @Override
            public void onResponse(Call<ResponseSayur> call, Response<ResponseSayur> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if (data == 1) {
                        List<Sayur> sayur = response.body().getDatanya();
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Sayur2Activity.this));
                        AdapterSayur adapter = new AdapterSayur(Sayur2Activity.this, sayur);
                        recyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sayur2Activity.this, 2);
                        recyclerView.setLayoutManager(gridLayoutManager);

                        progressBar.setVisibility(View.GONE);
                    } else {
                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(Sayur2Activity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSayur> call, Throwable t) {

            }
        });
    }
}
