package com.mobile.mutia.sayuronline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.SayurDetailActivity;
import com.mobile.mutia.sayuronline.TransaksiActivity;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.Transaksi;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.ViewHolder> {

    Context context;
    List<Transaksi> transaksis;

    public AdapterTransaksi(Context context, List<Transaksi> transaksis) {
        this.context = context;
        this.transaksis = transaksis;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_transaksi, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Transaksi transaksi = transaksis.get(position);

        viewHolder.id_penjual.setText("Nama Penjual : " + transaksi.getNama_penjual());
        viewHolder.id_pembeli.setText("Nama Pembeli : " + transaksi.getNama());
        viewHolder.nama_sayur.setText("Nama Sayur : " + transaksi.getNama_sayur());
        viewHolder.jumlah_beli.setText("Jumlah Beli : " + transaksi.getJumlah_beli());
        viewHolder.total.setText("Total Bayar : " + transaksi.getTotal());
        viewHolder.keterangan.setText(transaksi.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return transaksis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textNamaPenjual)
        TextView id_penjual;
        @BindView(R.id.textNamaPembeli)
        TextView id_pembeli;
        @BindView(R.id.textNamaSayur)
        TextView nama_sayur;
        @BindView(R.id.textJumlah)
        TextView jumlah_beli;
        @BindView(R.id.textJumlahHarga)
        TextView total;
        @BindView(R.id.textKeterangan)
        TextView keterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            final Pengguna pengguna = SharedPrefManager.getInstance(context).getUser();

            if (firebaseUser != null
                    && SharedPrefManager.getInstance(context).isLoggedIn()) {
                if (pengguna.getStatus().equals("penjual")) {
                    id_penjual.setVisibility(View.GONE);
                }
                if (pengguna.getStatus().equals("pembeli")) {
                    id_pembeli.setVisibility(View.GONE);
                }
            }
        }
    }
}
