package com.askerlab.catathutang;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by root on 30/05/17.
 */

public class TambahCatatan extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private RealmHelper realmHelper;
    private EditText txtNama , txtCatatan , txtNominal , txtTglHutang , txtTglKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_catatan);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        realmHelper = new RealmHelper(TambahCatatan.this);

        txtNama = (EditText) findViewById(R.id.txtNama);
        txtNominal = (EditText) findViewById(R.id.txtNominal);
        txtTglHutang = (EditText) findViewById(R.id.tglHutang);
        txtTglKembali = (EditText) findViewById(R.id.tglKembali);
        txtCatatan = (EditText) findViewById(R.id.txtCatatan);

        /* Tanggal Hutang Action */
        txtTglHutang.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    showDateDialog(true);
                }
            }
        });
        txtTglHutang.setOnTouchListener(new View.OnTouchListener(){
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
        txtTglKembali.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    showDateDialog(false);
                }
            }
        });
        txtTglKembali.setOnTouchListener(new View.OnTouchListener(){
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

        /* Button Tambah */
        Button btnTambah = (Button) findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String var_txtNama = txtNama.getText().toString().trim();
                String var_txtNominal = txtNominal.getText().toString().trim();
                String var_tglHutang = txtTglHutang.getText().toString().trim();
                String var_tglKembali = txtTglKembali.getText().toString().trim();
                String var_txtCatatan = txtCatatan.getText().toString().trim();

                if (var_txtNama.equals("") || var_txtNominal.equals("")
                        || var_tglHutang.equals("") || var_tglKembali.equals(""))
                {
                    Toast.makeText(TambahCatatan.this,
                            "Masih ada inputan yang kosong!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String rsCatatan = null;

                    if ( var_txtCatatan.equals("") )
                    {
                        rsCatatan = "";
                    }
                    else
                    {
                        rsCatatan = var_txtCatatan;
                    }
                    Date d = new Date();
                    CharSequence s  = DateFormat.format("dd-MM-yyyy HH:mm:ss", d.getTime());
                    realmHelper.addCatatan(var_txtNama , var_txtNominal , var_tglHutang , var_tglKembali , rsCatatan , s.toString());
                    clearForm();
                    finish();
                }
            }
        });
        /* button Tambah */
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
                    txtTglHutang.setText(dateFormatter.format(newDate.getTime()));
                }
                else
                {
                    txtTglKembali.setText(dateFormatter.format(newDate.getTime()));
                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void clearForm()
    {
        txtNama.setText("");
        txtNominal.setText("");
        txtTglHutang.setText("");
        txtTglKembali.setText("");
        txtCatatan.setText("");
    }
}
