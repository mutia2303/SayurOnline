package com.mobile.mutia.sayuronline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponseInsertEditHapusSayur;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSayurActivity extends AppCompatActivity {

    private EditText edit_id_pasar, edit_nama_sayur, edit_keterangan, edit_stok, edit_harga;
    private String id_sayur, id_pengguna, id_pasar, nama_sayur, keterangan, stok, harga;
    private Button btn_edit;

    Bitmap bitmap;
    ImageView imageView;
    private static final int GAMBAR = 100;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sayur);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Data Sayur");

//        id_sayur = (TextView) findViewById(R.id.edit_id_sayur);
        edit_id_pasar = (EditText) findViewById(R.id.edit_id_pasar);
        edit_nama_sayur = (EditText) findViewById(R.id.edit_nama_sayur);
        edit_keterangan = (EditText) findViewById(R.id.edit_keterangan);
        edit_stok = (EditText) findViewById(R.id.edit_stok);
        edit_harga = (EditText) findViewById(R.id.edit_harga);

        Pengguna pengguna = SharedPrefManager.getInstance(EditSayurActivity.this).getUser();
        id_pengguna = pengguna.getId_pengguna();
//        id_sayur.setText(getIntent().getStringExtra("id_sayur"));
        id_sayur = getIntent().getStringExtra("id_sayur");
        edit_id_pasar.setText(getIntent().getStringExtra("id_pasar"));
        edit_nama_sayur.setText(getIntent().getStringExtra("nama_sayur"));
        edit_keterangan.setText(getIntent().getStringExtra("keterangan"));
        edit_stok.setText(getIntent().getStringExtra("stok"));
        edit_harga.setText(getIntent().getStringExtra("harga"));

        btn_edit = (Button) findViewById(R.id.btn_edit_data);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });
    }

    private void editData() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sedang memproses");
        progressDialog.setMessage("Silahkan tunggu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Pengguna pengguna = SharedPrefManager.getInstance(EditSayurActivity.this).getUser();
//        id_pengguna = pengguna.getId_pengguna();
        id_pasar = edit_id_pasar.getText().toString().trim();
        nama_sayur = edit_nama_sayur.getText().toString().trim();
        keterangan = edit_keterangan.getText().toString().trim();
        stok = edit_stok.getText().toString().trim();
        harga = edit_harga.getText().toString().trim();

        if (id_pasar.isEmpty() || nama_sayur.isEmpty() || keterangan.isEmpty() || stok.isEmpty() || harga.isEmpty()) {
            Toast.makeText(EditSayurActivity.this, "Field Belum di Inputkan", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (id_pasar.equals("6") || id_pasar.equals("7") || id_pasar.equals("8") || id_pasar.equals("9") || id_pasar.length()>1) {
            edit_id_pasar.setError("Masukkan data dari 1 - 5");
            edit_id_pasar.requestFocus();
            progressDialog.dismiss();
            return;
        }

        ConfigRetrofit.getInstance().getApi().edit_sayur(pengguna.getId_pengguna(), id_sayur, id_pasar, nama_sayur, stok, keterangan, harga).enqueue(new Callback<ResponseInsertEditHapusSayur>() {
            @Override
            public void onResponse(Call<ResponseInsertEditHapusSayur> call, Response<ResponseInsertEditHapusSayur> response) {

                if (response.isSuccessful()) {

                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if (data == 1) {
                        Intent intent = new Intent(EditSayurActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(EditSayurActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(EditSayurActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseInsertEditHapusSayur> call, Throwable t) {

            }
        });
    }
}
