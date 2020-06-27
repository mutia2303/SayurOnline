package com.mobile.mutia.sayuronline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponsePassword;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordActivity extends AppCompatActivity {

    private EditText editPassword, editConfirm;
    private Button btn_ubah_password;
    private String password, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        editPassword = findViewById(R.id.editPassword);

//        btn_ubah_password = findViewById(R.id.btn_ubah_password);
//        btn_ubah_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ubah_password();
//            }
//        });
    }

    private void ubah_password() {

        Pengguna pengguna = SharedPrefManager.getInstance(UbahPasswordActivity.this).getUser();

        password = editPassword.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        } else {
            ConfigRetrofit.getInstance().getApi().ubahPassword(pengguna.getId_pengguna(), password).enqueue(new Callback<ResponsePassword>() {
                @Override
                public void onResponse(Call<ResponsePassword> call, Response<ResponsePassword> response) {
                    if (response.isSuccessful()) {
                        String pesan  = response.body().getPesan();
                        int sukses = response.body().getSukses();

                        if (sukses == 1) {
                            Toast.makeText(UbahPasswordActivity.this, pesan, Toast.LENGTH_SHORT).show();

                            SharedPrefManager.getInstance(UbahPasswordActivity.this).saveUser(response.body().getPengguna());
                            Intent intent = new Intent(UbahPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(UbahPasswordActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponsePassword> call, Throwable t) {

                }
            });
        }
    }
}
