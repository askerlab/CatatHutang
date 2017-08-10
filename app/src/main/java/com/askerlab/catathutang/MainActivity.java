package com.askerlab.catathutang;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.askerlab.catathutang.adapter.AdapterHutang;
import com.askerlab.catathutang.model.HutangModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RealmHelper helper;
    private ArrayList<HutangModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new RealmHelper(MainActivity.this);
        data = new ArrayList<>();
        final Context context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.rvHutang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i = new Intent(context, UbahCatatan.class);
                        i.putExtra("id" , data.get(position).getId());
                        i.putExtra("nama" , data.get(position).getNama());
                        i.putExtra("status" , data.get(position).getStatus());
                        i.putExtra("nominal" , data.get(position).getNominal());
                        i.putExtra("tanggal_hutang" , data.get(position).getTanggal_hutang());
                        i.putExtra("tanggal_kembali" , data.get(position).getTanggal_kembali());
                        i.putExtra("catatan" , data.get(position).getCatatan());
                        i.putExtra("date_input" , data.get(position).getDate_input());
                        context.startActivity(i);
                        overridePendingTransition(0 , R.anim.fade_activity);
                    }

                    @Override public void onLongItemClick(View view, final int position) {
                        String s = "Hutang ini <b>" + data.get(position).getStatus() +"</b>. Apakah ingin dihapus?";
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
                        builder.setTitle("Hapus Catatan!");
                        builder.setMessage(Html.fromHtml(s));
                        builder.setPositiveButton("Ya, hapus", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                helper.hapusCatatan(data.get(position).getId());
                                onResume();
                            }});

                        builder.setNegativeButton("Batal", null);
                        builder.show();
                    }
                })
        );

        setRecyclerView();

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TambahCatatan.class);
                startActivity(i);
                overridePendingTransition(0 , R.anim.fade_activity);
            }
        });
    }

    public void setRecyclerView()
    {
        try {
            data = helper.viewAllCatatan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdapterHutang adapter = new AdapterHutang(data , MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        try {
            data = helper.viewAllCatatan();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setRecyclerView();
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
}
