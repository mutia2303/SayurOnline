package com.mobile.mutia.sayuronline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class Sayur4Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView4)
    RecyclerView recyclerView;

    private TextView textView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayur4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Sayur");

        textView = (TextView)findViewById(R.id.textview);
        progressBar = findViewById(R.id.progress);

        ButterKnife.bind(this);
        dataSayur4();
    }

    private void dataSayur4() {

        ConfigRetrofit.getInstance().getApi().getSayur4().enqueue(new Callback<ResponseSayur>() {
            @Override
            public void onResponse(Call<ResponseSayur> call, Response<ResponseSayur> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if (data == 1) {
                        List<Sayur> sayur = response.body().getDatanya();
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Sayur4Activity.this));
                        AdapterSayur adapter = new AdapterSayur(Sayur4Activity.this, sayur);
                        recyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sayur4Activity.this, 2);
                        recyclerView.setLayoutManager(gridLayoutManager);

                        progressBar.setVisibility(View.GONE);
                    } else {
                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(Sayur4Activity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSayur> call, Throwable t) {

            }
        });
    }
}
