package com.mobile.mutia.sayuronline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.mutia.sayuronline.EditGambarActivity;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.SayurDetailActivity;
import com.mobile.mutia.sayuronline.model.Sayur;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSayur extends RecyclerView.Adapter<AdapterSayur.ViewHolder> {

    Context context;
    List<Sayur> sayurs;
    CardView cardView;

    public AdapterSayur(Context context, List<Sayur> sayurs) {
        this.context = context;
        this.sayurs = sayurs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_sayur, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Sayur sayur = sayurs.get(position);
        //final Chat chat = chats.get(position);

        final String urlGambar ="http://192.168.43.144:80/sayur/upload/sayur/" + sayurs.get(position).getGambar();
        Picasso.with(context)
                .load(urlGambar)
                .into(viewHolder.gambar);

        viewHolder.nama_sayur.setText(sayur.getNama_sayur());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(context,SayurDetailActivity.class);
                mIntent.putExtra("id_pengguna", sayur.getId_pengguna());
                mIntent.putExtra("id_sayur", sayur.getId_sayur());
                mIntent.putExtra("id_pasar", sayur.getId_pasar());
                mIntent.putExtra("gambar", urlGambar);
                mIntent.putExtra("nama_pasar", sayur.getNama_pasar());
                mIntent.putExtra("nama", sayur.getNama());
                mIntent.putExtra("username", sayur.getUsername());
                mIntent.putExtra("nama_sayur", sayur.getNama_sayur());
                mIntent.putExtra("stok", sayur.getStok());
                mIntent.putExtra("keterangan", sayur.getKeterangan());
                mIntent.putExtra("harga", sayur.getHarga());
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sayurs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView gambar;
        @BindView(R.id.cardViewSayur)
        CardView cardView;
        @BindView(R.id.textNamaSayur)
        TextView nama_sayur;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
