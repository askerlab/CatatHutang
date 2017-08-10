package com.askerlab.catathutang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlab.catathutang.R;
import com.askerlab.catathutang.helper.ResourcesHelper;
import com.askerlab.catathutang.model.HutangModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by root on 01/06/17.
 */

public class AdapterHutang extends RecyclerView.Adapter<AdapterHutang.ViewHolder> {

    private final Context context;
    private ArrayList<HutangModel> hm;
    LayoutInflater inflater;

    public AdapterHutang(ArrayList<HutangModel> hm , Context context) {
        this.hm = hm;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public AdapterHutang.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, null);
        View view = inflater.inflate(R.layout.content_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterHutang.ViewHolder holder, final int position) {
        ResourcesHelper rh = new ResourcesHelper();

        holder.vcNama.setText(hm.get(position).getNama());
        holder.vcNominal.setText(rh.convertToRupiah(hm.get(position).getNominal()));
        holder.vcStatus.setText(hm.get(position).getStatus());
        holder.vcTempo.setText(hm.get(position).getTanggal_kembali());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vcNama, vcStatus , vcNominal , vcTempo;


        public ViewHolder(View itemView) {
            super(itemView);
            vcNama = (TextView) itemView.findViewById(R.id.cvNama);
            vcStatus = (TextView) itemView.findViewById(R.id.cvStatus);
            vcNominal = (TextView) itemView.findViewById(R.id.cvNominal);
            vcTempo = (TextView) itemView.findViewById(R.id.cvTempo);
        }
    }

    @Override
    public int getItemCount() {
        return hm.size();
    }
}