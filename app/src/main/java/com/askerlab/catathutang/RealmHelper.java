package com.askerlab.catathutang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.askerlab.catathutang.helper.Hutang;
import com.askerlab.catathutang.model.HutangModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by root on 01/06/17.
 */

public class RealmHelper {

    protected Realm realm;
    private RealmResults<Hutang> realmResult;
    public Context context;
    private static final String TAG = "RealmHelper";
    LayoutInflater inflater;

    public RealmHelper(Context context) {
        realm = Realm.getDefaultInstance();
        this.context = context;
        inflater= LayoutInflater.from(context);
    }

    public void addCatatan(String nama , String nominal, String tanggal_hutang, String tanggal_kembali , String catatan , String dateInput)
    {
        Hutang ch = new Hutang();
        ch.setId((int) (System.currentTimeMillis() / 1000));
        ch.setNama(nama);
        ch.setNominal(nominal);
        ch.setTanggal_hutang(tanggal_hutang);
        ch.setTanggal_kembali(tanggal_kembali);
        ch.setCatatan(catatan);
        ch.setStatus("Belum Lunas");
        ch.setDate_input(dateInput);

        realm.beginTransaction();
        realm.copyToRealm(ch);
        realm.commitTransaction();

        showToast("Catatan berhasil disimpan!" , false);
    }

    public void hapusCatatan(int id)
    {
        RealmResults<Hutang> dataDesults = realm.where(Hutang.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataDesults.remove(0);
        dataDesults.removeLast();
        dataDesults.clear();
        realm.commitTransaction();

        showToast("Catatan berhasil dihapus!", false);

        if (dataDesults.size() == 0)
        {
            showToast("Catatan hutang sudah kosong!" , true);
        }
    }

    public ArrayList<HutangModel> viewAllCatatan()
    {
        ArrayList<HutangModel> data = new ArrayList<>();

        realmResult = realm.where(Hutang.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            showLog("Size : " + realmResult.size());
            for (int i = 0; i < realmResult.size(); i++) {
                String nama , status , nominal, tanggal_kembali , catatan , tanggal_hutang , date_input;

                Integer id = realmResult.get(i).getId();
                nama = realmResult.get(i).getNama();
                status = realmResult.get(i).getStatus();
                nominal = realmResult.get(i).getNominal();
                tanggal_kembali = realmResult.get(i).getTanggal_kembali();
                catatan = realmResult.get(i).getCatatan();
                tanggal_hutang = realmResult.get(i).getTanggal_hutang();
                date_input = realmResult.get(i).getDate_input();
                data.add(new HutangModel(id,nama,status,catatan,nominal,tanggal_hutang,tanggal_kembali,date_input));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }

    public void ubahCatatan(int id, String nama , String nominal , String tglHutang , String tglKembali , String catatan , String status , String dateInput) {
        realm.beginTransaction();
        Hutang hutang = realm.where(Hutang.class).equalTo("id", id).findFirst();
        hutang.setNama(nama);
        hutang.setNominal(nominal);
        hutang.setTanggal_hutang(tglHutang);
        hutang.setTanggal_kembali(tglKembali);
        hutang.setCatatan(catatan);
        hutang.setStatus(status);
        hutang.setDate_input(dateInput);
        realm.commitTransaction();

        showToast("Catatan berhasil diubah!" , false);
    }

    private void showLog(String s) {
        Log.d(TAG, s);
    }

    private void showToast(String s , Boolean isLong) {
        Integer duration = null;
        if ( isLong)
        {
            duration = Toast.LENGTH_LONG;
        }
        else
        {
            duration = Toast.LENGTH_SHORT;
        }

        Toast.makeText(context, s, duration).show();
    }
}
