package com.mobile.mutia.sayuronline;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponseInsertEditHapusSayur;
import com.mobile.mutia.sayuronline.model.ResponseInsertTransaksi;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SayurDetailActivity extends AppCompatActivity {

    ImageView imageViewDetail;
    TextView textNamaPasar, textNamaPengguna, textNamaSayur, textKeterangan, textStok, textJumlah, textHarga, textKetHarga, textId;

    private ImageButton btnChat, btnBeli, btnEdit, btnHapus, btnEditgambar;
    private Button btnPlus, btnMinus;
    String id_sayur, id_pasar, stok2, id_pengguna, harga, jumlah, nama, username, gambar;
    int jml;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayur_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Sayur");

        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);
        textNamaPasar = (TextView) findViewById(R.id.txtNamaPasar);
        textNamaPengguna = (TextView) findViewById(R.id.txtNamaPengguna);
        textNamaSayur = (TextView) findViewById(R.id.txtNamaSayur);
        textKeterangan = (TextView) findViewById(R.id.txtKeterangan);
        textStok = (TextView) findViewById(R.id.txtStok);
        textJumlah = (TextView) findViewById(R.id.textJumlah);
        textHarga = (TextView) findViewById(R.id.txtHarga);
        textKetHarga = (TextView) findViewById(R.id.txtKetHrg);

        gambar = getIntent().getStringExtra("gambar");
        Picasso.with(this).load(gambar).into(imageViewDetail);

        id_pengguna = getIntent().getStringExtra("id_pengguna");
        id_sayur = getIntent().getStringExtra("id_sayur");
        id_pasar = getIntent().getStringExtra("id_pasar");
        username = getIntent().getStringExtra("username");
        textNamaPasar.setText(getIntent().getStringExtra("nama_pasar"));
        textNamaPengguna.setText(getIntent().getStringExtra("nama"));
        textNamaSayur.setText(getIntent().getStringExtra("nama_sayur"));
        textStok.setText(getIntent().getStringExtra("stok"));
        textKeterangan.setText(getIntent().getStringExtra("keterangan"));
        textHarga.setText(getIntent().getStringExtra("harga"));
        textKetHarga.setText(getIntent().getStringExtra("keterangan"));

        jml = 0;
        stok2 = getIntent().getStringExtra("stok");
        textJumlah.setText(Integer.toString(jml));

        //String nama_pasar = textNamaPasar.getText().toString();
        final String nama_sayur = textNamaSayur.getText().toString().trim();
        final String keterangan = textKeterangan.getText().toString().trim();
        final String stok = textStok.getText().toString().trim();
        harga = textHarga.getText().toString().trim();
        nama = textNamaPengguna.getText().toString().trim();

        btnChat = (ImageButton) findViewById(R.id.btn_Chat);
        btnBeli = (ImageButton) findViewById(R.id.btn_Beli);
        btnEdit = (ImageButton) findViewById(R.id.btn_Edit);
        btnHapus = (ImageButton) findViewById(R.id.btn_Hapus);
        btnEditgambar = (ImageButton) findViewById(R.id.btn_Edit_gambar);

        btnPlus = (Button) findViewById(R.id.btn_Plus);
        btnMinus = (Button) findViewById(R.id.btn_Minus);

        final Pengguna pengguna = SharedPrefManager.getInstance(SayurDetailActivity.this).getUser();

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null
                && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
            if (pengguna.getStatus().equals("penjual")) {
                btnPlus.setVisibility(View.GONE);
                btnMinus.setVisibility(View.GONE);
                textJumlah.setVisibility(View.GONE);
                btnBeli.setVisibility(View.GONE);
            }

            if (pengguna.getStatus().equals("pembeli")) {
                btnEdit.setVisibility(View.GONE);
                btnHapus.setVisibility(View.GONE);
                btnEditgambar.setVisibility(View.GONE);
            }

            if (pengguna.getId_pengguna().equals(id_pengguna)) {
                btnEdit.setVisibility(View.VISIBLE);
                btnHapus.setVisibility(View.VISIBLE);
                btnEditgambar.setVisibility(View.VISIBLE);
            } else {
                btnEdit.setVisibility(View.GONE);
                btnHapus.setVisibility(View.GONE);
                btnEditgambar.setVisibility(View.GONE);
            }
        }

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                        if (jml < Integer.parseInt(stok2)) {
                            jml = jml + 1;
                            textJumlah.setText(Integer.toString(jml));
//                        jumlah = textJumlah.getText().toString().trim();
                        } else {

                        }
//                    jumlah = textJumlah.getText().toString().trim();
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                        if (jml > 0) {
                            jml = jml - 1;
                            textJumlah.setText(Integer.toString(jml));
//                        jumlah = textJumlah.getText().toString().trim();
                        } else {
                            jml = 0;
                            textJumlah.setText(Integer.toString(jml));
                        }
//                    jumlah = textJumlah.getText().toString().trim();
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {

                    Intent intent = new Intent(SayurDetailActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        });

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                    beliData();
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                        Intent intent = new Intent(SayurDetailActivity.this, EditSayurActivity.class);
                        intent.putExtra("id_sayur", id_sayur);
                        intent.putExtra("id_pasar", id_pasar);
                        intent.putExtra("nama_sayur", nama_sayur);
                        intent.putExtra("keterangan", keterangan);
                        intent.putExtra("stok", stok);
                        intent.putExtra("harga", harga);
                        startActivity(intent);
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnEditgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                    Intent intent = new Intent(SayurDetailActivity.this, EditGambarActivity.class);
                    intent.putExtra("id_sayur", id_sayur);
                    intent.putExtra("gambar", gambar);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser != null
                        && SharedPrefManager.getInstance(SayurDetailActivity.this).isLoggedIn()) {
                        hapusData();
                } else {
                    Intent intent = new Intent(SayurDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void hapusData() {

        AlertDialog dialog = new AlertDialog.Builder(SayurDetailActivity.this).create();
        dialog.setTitle("Konfirmasi");

        dialog.setMessage("Apakah kamu yakin ingin menghapus sayur ini?");
        dialog.setCancelable(false);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConfigRetrofit.getInstance().getApi().hapus_sayur(id_sayur).enqueue(new Callback<ResponseInsertEditHapusSayur>() {
                    @Override
                    public void onResponse(Call<ResponseInsertEditHapusSayur> call, Response<ResponseInsertEditHapusSayur> response) {

                        if (response.isSuccessful()) {
                            String pesan = response.body().getPesan();
                            int data = response.body().getData();

                            if (data == 1) {
                                Toast.makeText(SayurDetailActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SayurDetailActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SayurDetailActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsertEditHapusSayur> call, Throwable t) {

                    }
                });
                dialog.dismiss();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);

        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLUE);
    }

    private void beliData() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sedang memproses");
        progressDialog.setMessage("Silahkan tunggu ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final Pengguna pengguna = SharedPrefManager.getInstance(SayurDetailActivity.this).getUser();

        jumlah = textJumlah.getText().toString().trim();
        
        AlertDialog dialog = new AlertDialog.Builder(SayurDetailActivity.this).create();
        dialog.setTitle("Konfirmasi");

        dialog.setMessage("Apakah kamu yakin ingin membeli sayur ini?");
        dialog.setCancelable(false);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConfigRetrofit.getInstance().getApi().transaksi(pengguna.getId_pengguna(), id_sayur, id_pasar, username, nama, jumlah).enqueue(new Callback<ResponseInsertTransaksi>() {
                    @Override
                    public void onResponse(Call<ResponseInsertTransaksi> call, Response<ResponseInsertTransaksi> response) {
                        
                        if (response.isSuccessful()) {
                            String pesan = response.body().getPesan();
                            int data = response.body().getData();

                            if (data == 1) {
                                Toast.makeText(SayurDetailActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SayurDetailActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SayurDetailActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsertTransaksi> call, Throwable t) {

                    }
                });
                dialog.dismiss();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);

        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLUE);
    }
}