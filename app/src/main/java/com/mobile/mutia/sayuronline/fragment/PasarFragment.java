package com.mobile.mutia.sayuronline.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.mutia.sayuronline.InsertDataActivity;
import com.mobile.mutia.sayuronline.LoginActivity;
import com.mobile.mutia.sayuronline.MainActivity;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.Sayur1Activity;
import com.mobile.mutia.sayuronline.Sayur2Activity;
import com.mobile.mutia.sayuronline.Sayur3Activity;
import com.mobile.mutia.sayuronline.Sayur4Activity;
import com.mobile.mutia.sayuronline.Sayur5Activity;
import com.mobile.mutia.sayuronline.SayurDetailActivity;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class PasarFragment extends Fragment implements View.OnClickListener {


    public PasarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasar, container, false);
    }

    @SuppressLint("RestrictedApi")
    public  void onViewCreated(View view, Bundle savedInstanceState) {

        view.findViewById(R.id.relative1).setOnClickListener(this);
        view.findViewById(R.id.relative2).setOnClickListener(this);
        view.findViewById(R.id.relative3).setOnClickListener(this);
        view.findViewById(R.id.relative4).setOnClickListener(this);
        view.findViewById(R.id.relative5).setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        final Pengguna pengguna = SharedPrefManager.getInstance(getActivity()).getUser();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        if (firebaseUser != null
                && SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            if (pengguna.getStatus().equals("pembeli")) {
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                p.setAnchorId(View.NO_ID);
                fab.setLayoutParams(p);
                fab.setVisibility(View.GONE);
            }
        } else {
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            p.setAnchorId(View.NO_ID);
            fab.setLayoutParams(p);
            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (firebaseUser != null
                        && SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
                    Intent intent = new Intent(getActivity(), InsertDataActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative1:
                Intent intent1 = new Intent(getActivity(), Sayur1Activity.class);
                startActivity(intent1);
                break;
            case R.id.relative2:
                Intent intent2 = new Intent(getActivity(), Sayur2Activity.class);
                startActivity(intent2);
                break;
            case R.id.relative3:
                Intent intent3 = new Intent(getActivity(), Sayur3Activity.class);
                startActivity(intent3);
                break;
            case R.id.relative4:
                Intent intent4 = new Intent(getActivity(), Sayur4Activity.class);
                startActivity(intent4);
                break;
            case R.id.relative5:
                Intent intent5 = new Intent(getActivity(), Sayur5Activity.class);
                startActivity(intent5);
                break;
        }
    }

}
