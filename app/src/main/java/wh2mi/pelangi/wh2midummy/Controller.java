package wh2mi.pelangi.wh2midummy;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    Cursor c_Gejala;
    // ArrayList<String> tempGejala;
//    Map<String, String> tempGejala;
    DataBaseHelper dbHelper;


    //
    private ArrayList<ModelGejala> tempGejala;

    // MODEL
//    ModelGejala modelGejala;

    Controller(Context context) {
        dbHelper = new DataBaseHelper(context);


        // MODEL
        this.tempGejala = new ArrayList<ModelGejala>();
//        getAllGejala(context);

    }


    ///GEJALA
    public void getAllGejala(Context context) {
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
                Toast.makeText(context, "Nama gejala: " + c_Gejala.getString(2), Toast.LENGTH_SHORT).show();
            } while (c_Gejala.moveToNext());
        }
//        return tempGejala;
    }

    public int getDataGejalaSize() {
        return this.tempGejala.size();
    }

    public String[] getAllGejalaToString() {
        String[] temp = new String[getDataGejalaSize()];
        for (int i = 0; i < getDataGejalaSize(); i++) {
            temp[i] = this.tempGejala.get(i).getKetGejala();

        }
        return temp;
    }
}
//TAMBAHAN GEJALA
//    Controller controller = new Controller(getApplicationContext());
//    String[] GejalaBaru = controller.getAllGejalaToString();