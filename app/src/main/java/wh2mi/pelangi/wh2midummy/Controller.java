package wh2mi.pelangi.wh2midummy;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    Cursor c_Gejala;
    Cursor c_KondisiLingkungan;


    DataBaseHelper dbHelper;


    private ArrayList<ModelGejala> tempGejala;
    private ArrayList<ModelKondisiLingkungan> tempKondisiLingkungan;


    Controller(Context context) {
        dbHelper = new DataBaseHelper(context);


        // MODEL
        this.tempGejala = new ArrayList<ModelGejala>();
        this.tempKondisiLingkungan = new ArrayList<ModelKondisiLingkungan>();


    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////                           CONTROLLER UNTUK MODEL                                              //////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method untuk mengambil seluru gejala pada database.
     * @param context Perlu menggunakan Context untuk mengetahui pada kelas mana yang menggunakannya
     */
    private void getAllGejala(Context context) {
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c_Gejala = dbHelper.query("tabel_gejala", null, null, null, null, null, null);
        if (c_Gejala.moveToFirst()) {
            do {
                this.tempGejala.add(new ModelGejala(c_Gejala.getString(0), c_Gejala.getString(1), c_Gejala.getString(2), c_Gejala.getString(3)));
                Toast.makeText(context, "Nama gejala: " + c_Gejala.getString(2), Toast.LENGTH_SHORT).show();//Untuk mengecek
            } while (c_Gejala.moveToNext());
        }
//        return tempGejala;
    }


    public int getDataGejalaSize() {
        return this.tempGejala.size();
    }

    public String[] getAllGejalaToString(Context context) {
        getAllGejala(context);
        String[] temp = new String[getDataGejalaSize()];
        for (int i = 0; i < getDataGejalaSize(); i++) {
            temp[i] = this.tempGejala.get(i).getKetGejala();

        }
        return temp;
    }

    public ArrayList<ModelGejala> getTempGejala() {
        return tempGejala;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////                           CONTROLLER UNTUK KONDISI LINGKUNGAN                                 //////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getAllKondisiLingkungan(Context context) {
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c_KondisiLingkungan = dbHelper.query("tabel_kondisilingkungan", null, null, null, null, null, null);
        if (c_KondisiLingkungan.moveToFirst()) {
            do {
                this.tempKondisiLingkungan.add(new ModelKondisiLingkungan(c_KondisiLingkungan.getString(0), c_KondisiLingkungan.getString(1), c_KondisiLingkungan.getString(2), c_KondisiLingkungan.getString(3)));
                Toast.makeText(context, "Nama KondisiLingkungan: " + c_KondisiLingkungan.getString(2), Toast.LENGTH_SHORT).show();//Untuk mengecek
            } while (c_KondisiLingkungan.moveToNext());
        }
//        return tempKondisiLingkungan;
    }


    public int getDataKondisiLingkunganSize() {
        return this.tempKondisiLingkungan.size();
    }

    public String[] getAllKondisiLingkunganToString(Context context) {
        getAllKondisiLingkungan(context);
        String[] temp = new String[getDataKondisiLingkunganSize()];
        for (int i = 0; i < getDataKondisiLingkunganSize(); i++) {
            temp[i] = this.tempKondisiLingkungan.get(i).getKetKondisiLingkungan();

        }
        return temp;
    }

    public ArrayList<ModelKondisiLingkungan> gettempKondisiLingkungan() {
        return tempKondisiLingkungan;
    }

    public ArrayList<ModelKondisiLingkungan> getTempKondisiLingkungan() {
        return tempKondisiLingkungan;
    }
}
