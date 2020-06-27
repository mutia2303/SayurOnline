package com.mobile.mutia.sayuronline;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.mutia.sayuronline.fragment.ChatFragment;
import com.mobile.mutia.sayuronline.fragment.PasarFragment;
import com.mobile.mutia.sayuronline.fragment.ProfileFragment;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sayur Online");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PasarFragment()).commit();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            switch (menuItem.getItemId()) {
                case R.id.itemHome:
                    selectedFragment = new PasarFragment();
                    break;

                case R.id.itemChat:
                    selectedFragment = new ChatFragment();
                    break;

                case R.id.itemProfile:
                    selectedFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    private void logout() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null
                && SharedPrefManager.getInstance(MainActivity.this).isLoggedIn()) {

            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            dialog.setTitle("Konfirmasi");

            dialog.setMessage("Apakah kamu ingin keluar dari aplikasi ini?");
            dialog.setCancelable(false);

            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth.getInstance().signOut();

                    SharedPrefManager.getInstance(MainActivity.this).clear();

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    dialog.dismiss();


                }
            });

            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
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

        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }

    private void user() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null
                && SharedPrefManager.getInstance(MainActivity.this).isLoggedIn()) {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.user:
//                Intent intent = new Intent(this, UserActivity.class);
//                startActivity(intent);
                user();
                break;

            case R.id.logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}