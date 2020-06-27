package com.mobile.mutia.sayuronline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.adapter.AdapterTransaksi;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponseTransaksi;
import com.mobile.mutia.sayuronline.model.Transaksi;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewtransaksi)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Transaksi");

        ButterKnife.bind(this);
        transaksi();
    }

    private void transaksi() {

        Pengguna pengguna = SharedPrefManager.getInstance(TransaksiActivity.this).getUser();

        ConfigRetrofit.getInstance().getApi().getTransaksi(pengguna.getId_pengguna(), pengguna.getStatus(), pengguna.getUsername()).enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                String pesan = response.body().getPesan();
                int data = response.body().getData();

                if (data == 1) {
                    List<Transaksi> transaksi = response.body().getDatanya();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TransaksiActivity.this));
                    AdapterTransaksi adapter = new AdapterTransaksi(TransaksiActivity.this, transaksi);
                    recyclerView.setAdapter(adapter);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(TransaksiActivity.this, 1);
                    recyclerView.setLayoutManager(gridLayoutManager);
                } else {
                    Toast.makeText(TransaksiActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {

            }
        });
    }
}
