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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.model.ResponseInsertEditHapusSayur;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditGambarActivity extends AppCompatActivity {

    private String gambar, id_sayur;
    private Button btn_edit, btn_gambar;

    Bitmap bitmap;
    ImageView imageView;
    private static final int GAMBAR = 100;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gambar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Gambar");

        imageView = (ImageView) findViewById(R.id.image_sayur);

        String gambar = getIntent().getStringExtra("gambar");
        Picasso.with(this).load(gambar).into(imageView);

        id_sayur = getIntent().getStringExtra("id_sayur");

        btn_gambar = (Button) findViewById(R.id.btn_gambar);
        btn_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_gambar();
            }
        });

        btn_edit = (Button) findViewById(R.id.btn_edit_data);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });
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

    private void editData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sedang memproses");
        progressDialog.setMessage("Silahkan tunggu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        gambar = convertToString();

        ConfigRetrofit.getInstance().getApi().edit_gambar(id_sayur, gambar).enqueue(new Callback<ResponseInsertEditHapusSayur>() {
            @Override
            public void onResponse(Call<ResponseInsertEditHapusSayur> call, Response<ResponseInsertEditHapusSayur> response) {
                if (response.isSuccessful()) {
                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if (data == 1) {
                        Intent intent = new Intent(EditGambarActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(EditGambarActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditGambarActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseInsertEditHapusSayur> call, Throwable t) {

            }
        });
    }
}
