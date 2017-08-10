package com.askerlab.catathutang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by root on 01/06/17.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context context;

    String [] name={"Reksa" , "Rangga" , "Wardhana"};
    String [] nominal = {"5000" , "20000", "15000"};
    String [] status={"Belum Lunas" , "Lunas" , "Lunas"};
    String [] timeAgo = {"01-06-2017" , "02-06-2017" , "03-06-2017"};

    LayoutInflater inflater;
    public RecyclerAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.content_item, parent, false);

        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.vcNama.setText(name[position]);
        holder.vcNama.setTag(holder);
        holder.vcNominal.setText(nominal[position]);
        holder.vcNominal.setTag(holder);
        holder.vcStatus.setText(status[position]);
        holder.vcStatus.setTag(holder);
        holder.vcTimeago.setText(timeAgo[position]);
        holder.vcTimeago.setTag(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(context, name[position] + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }
}
