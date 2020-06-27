package com.mobile.mutia.sayuronline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponseInsertEditHapusSayur;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editId_pasar, editNama_sayur, editKeterangan, editHarga, editStok;
    private String id_pengguna, id_pasar, nama_sayur, keterangan, gambar, harga, stok;
    private ProgressDialog loadingBar;

    Bitmap bitmap;
    ImageView imageView;
    private static final int GAMBAR = 100;

    private ProgressDialog progressDialog;

//    private int PICK_IMAGE_REQUEST = 1;
//
//    private static final int STORAGE_PERMISSION_CODE = 123;
//
//    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tambah Data Sayur");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        Pengguna pengguna = SharedPrefManager.getInstance(InsertDataActivity.this).getUser();
        id_pengguna = pengguna.getId_pengguna();

        editId_pasar =(EditText) findViewById(R.id.edit_id_pasar);
        editNama_sayur =(EditText) findViewById(R.id.edit_nama_sayur);
        editKeterangan =(EditText) findViewById(R.id.edit_keterangan);
        editHarga = (EditText) findViewById(R.id.edit_harga);
        editStok = (EditText) findViewById(R.id.edit_stok);

        imageView = (ImageView) findViewById(R.id.image_sayur);

        findViewById(R.id.btn_insert_data).setOnClickListener(this);
        findViewById(R.id.btn_gambar).setOnClickListener(this);

    }

    private void select_gambar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GAMBAR);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GAMBAR && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void insert_sayur() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sedang memproses");
        progressDialog.setMessage("Silahkan tunggu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Pengguna pengguna = SharedPrefManager.getInstance(InsertDataActivity.this).getUser();
//        id_pengguna = pengguna.getId_pengguna();
        id_pasar = editId_pasar.getText().toString().trim();
        nama_sayur = editNama_sayur.getText().toString().trim();
        keterangan = editKeterangan.getText().toString().trim();
        harga = editHarga.getText().toString().trim();
        stok = editStok.getText().toString().trim();
        gambar = convertToString();

        if (id_pasar.isEmpty() || nama_sayur.isEmpty() || keterangan.isEmpty() || gambar.isEmpty() || harga.isEmpty() || stok.isEmpty()) {
            Toast.makeText(InsertDataActivity.this, "Field Belum di Inputkan", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if (id_pasar.equals("6") || id_pasar.equals("7") || id_pasar.equals("8") || id_pasar.equals("9") || id_pasar.length()>1) {
            editId_pasar.setError("Masukkan data dari 1 - 5");
            editId_pasar.requestFocus();
            progressDialog.dismiss();
            return;
        }

        ConfigRetrofit.getInstance().getApi().insertSayur(pengguna.getId_pengguna(), id_pasar, nama_sayur, stok, keterangan, harga, gambar).enqueue(new Callback<ResponseInsertEditHapusSayur>() {
            @Override
            public void onResponse(Call<ResponseInsertEditHapusSayur> call, Response<ResponseInsertEditHapusSayur> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if(data == 1) {
                        Intent intent = new Intent(InsertDataActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(InsertDataActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(InsertDataActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertEditHapusSayur> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_data:
                insert_sayur();
                break;

            case R.id.btn_gambar:
                select_gambar();
                break;
        }
    }


}
