package com.askerlab.catathutang;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlab.catathutang.helper.ResourcesHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by root on 01/06/17.
 */

public class UbahCatatan extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    EditText txtNama , txtNominal , tglHutang , tglKembali , txtCatatan;
    TextView txtStatus , txtRupiah;
    private String intentNama ,intentDiUbah , intentStatus , intentTglHutang , intentTglKembali , intentCatatan , intentNominal;
    RadioButton rbLunas , rbBelum;
    RadioGroup rg;
    Button btnEdit;
    private int statusToInt , intentId;
    private RealmHelper helper;
    String rsStatus;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_catatan);
        ResourcesHelper rh = new ResourcesHelper();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        helper = new RealmHelper(UbahCatatan.this);

        intentId = getIntent().getIntExtra("id" , 0);
        intentNama = getIntent().getStringExtra("nama");
        intentStatus = getIntent().getStringExtra("status");
        intentStatus = getIntent().getStringExtra("status");
        intentNominal = getIntent().getStringExtra("nominal");
        intentTglHutang = getIntent().getStringExtra("tanggal_hutang");
        intentTglKembali = getIntent().getStringExtra("tanggal_kembali");
        intentCatatan = getIntent().getStringExtra("catatan");
        intentDiUbah = getIntent().getStringExtra("date_input");

        txtNama = (EditText) findViewById(R.id.txtNama);
        txtNominal = (EditText) findViewById(R.id.txtNominal);
        tglHutang = (EditText) findViewById(R.id.tglHutang);
        tglKembali = (EditText) findViewById(R.id.tglKembali);
        txtCatatan = (EditText) findViewById(R.id.txtCatatan);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtRupiah = (TextView) findViewById(R.id.txtRupiah);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        txtNama.setText(intentNama);
        txtNominal.setText(intentNominal);
        tglHutang.setText(intentTglHutang);
        tglKembali.setText(intentTglKembali);
        txtCatatan.setText(intentCatatan);
        txtStatus.setText("Terakhir diubah: " + intentDiUbah);
        txtRupiah.setText(rh.convertToRupiah(intentNominal));

        rg = new RadioGroup(UbahCatatan.this);
        rbLunas = (RadioButton) findViewById(R.id.rbSudah);
        rbBelum = (RadioButton) findViewById(R.id.rbBelum);

        if ( intentStatus.equals("Belum Lunas"))
        {
            rbBelum.setChecked(true);
        }
        else
        {
            rbLunas.setChecked(true);
        }

        /* Tanggal Hutang Action */
        tglHutang.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    showDateDialog(true);
                }
            }
        });
        tglHutang.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if ( event.getAction() == MotionEvent.ACTION_UP && getCurrentFocus() == null
                        )
                {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    showDateDialog(true);
                }
                return false;
            }
        });
        /* Tanggal Hutang Action */

        /* Tanggal Kembali Action */
        tglKembali.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    showDateDialog(false);
                }
            }
        });
        tglKembali.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if ( event.getAction() == MotionEvent.ACTION_UP && getCurrentFocus() == null)
                {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    showDateDialog(false);
                }
                return false;
            }
        });
        /* Tanggal Kembali Action */

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var_txtNama = txtNama.getText().toString().trim();
                String var_txtNominal = txtNominal.getText().toString().trim();
                String var_tglHutang = tglHutang.getText().toString().trim();
                String var_tglKembali = tglKembali.getText().toString().trim();
                String var_txtCatatan = txtCatatan.getText().toString().trim();

                if (var_txtNama.equals("") || var_txtNominal.equals("")
                        || var_tglHutang.equals("") || var_tglKembali.equals(""))
                {
                    Toast.makeText(UbahCatatan.this,
                            "Masih ada inputan yang kosong!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(rbBelum.isChecked())
                    {
                        rsStatus = "Belum Lunas";
                    }
                    else
                    {
                        rsStatus = "Sudah Lunas";
                    }

                    Date d = new Date();
                    CharSequence s  = DateFormat.format("dd-MM-yyyy HH:mm:ss", d.getTime());
                    helper.ubahCatatan(intentId , var_txtNama , var_txtNominal , var_tglHutang , var_tglKembali , var_txtCatatan , rsStatus , s.toString());
                    finish();
                }
            }
        });
    }

    private void showDateDialog(final Boolean isTxtTglHutang){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                if ( isTxtTglHutang)
                {
                    tglHutang.setText(dateFormatter.format(newDate.getTime()));
                }
                else
                {
                    tglKembali.setText(dateFormatter.format(newDate.getTime()));
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
