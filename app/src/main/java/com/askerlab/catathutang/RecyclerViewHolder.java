package com.askerlab.catathutang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView vcNama,vcStatus , vcNominal , vcTimeago;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        vcNama  = (TextView) itemView.findViewById(R.id.cvNama);
        vcStatus = (TextView) itemView.findViewById(R.id.cvStatus);
        vcNominal = (TextView) itemView.findViewById(R.id.cvNominal);
        vcTimeago = (TextView) itemView.findViewById(R.id.cvTempo);
    }

}