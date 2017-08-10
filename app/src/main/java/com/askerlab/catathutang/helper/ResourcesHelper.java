package com.askerlab.catathutang.helper;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by root on 02/06/17.
 */

public class ResourcesHelper {

    public String convertToRupiah(String nominal)
    {
        String Rupiah = "Rp";
        String hasil;
        NumberFormat rupiahFormat;

        if ( nominal.equals(""))
        {
            hasil = "0";
        }
        else
        {
            hasil = nominal;
        }

        rupiahFormat = NumberFormat.getInstance(Locale.US);
        String rupiah = rupiahFormat.format(Double.parseDouble(hasil));

        String result = Rupiah + "" + rupiah.replace(',' , '.');

        return result;
    }
}
