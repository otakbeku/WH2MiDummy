package wh2mi.pelangi.wh2midummy;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Controller {
    Cursor c_Gejala;
    Cursor c_KondisiLingkungan;
    Cursor c_Penyakit;
    Cursor c_SaranPencegahan;
    Cursor c_SaranPertama;

    private ArrayList<ModelGejala> tempGejala;
    private ArrayList<ModelKondisiLingkungan> tempKondisiLingkungan;
    private ArrayList<ModelPenyakit> tempPenyakit;
//    private ArrayList<ModelSaranPencegahan> tempSaranPencegahan;

    private ArrayList<String> saranPencegahanByIdPenyakit;
    private ArrayList<String> saranPertamaByIdPenyakit;
    private ArrayList<String> gejalaByIdPenyakit;
    private ArrayList<String> kondisiByIdPenyakit;

    DataBaseHelper dbHelper;

    //UNTUK DIHALAMAN DIAGNOSA
    public Controller(Context context, String idPenyakit) {
        dbHelper = new DataBaseHelper(context);

        gejalaByIdPenyakit = getGejalaByIdPenyakit(idPenyakit);
        saranPencegahanByIdPenyakit = getSaranPencegahanByIdPenyakit(idPenyakit);
        saranPertamaByIdPenyakit = getSaranPertamaByIdPenyakit(idPenyakit);
        kondisiByIdPenyakit = getKondisiByIdPenyakit(idPenyakit);

    }

    //UNTUK GEJALA
    Controller(Context context) {
        dbHelper = new DataBaseHelper(context);
        // MODEL
        this.tempGejala = new ArrayList<ModelGejala>();
        getAllGejala(context);
    }

    //UNTUK PENYAKIT & KONDISI LINGKUNGAN
    Controller(Context context, ArrayList<String> c_FK_idGejala) {
        dbHelper = new DataBaseHelper(context);
        // MODEL
        this.tempKondisiLingkungan = new ArrayList<ModelKondisiLingkungan>();
        this.tempPenyakit = new ArrayList<ModelPenyakit>();
        getAllKondisiLingkungan(context, c_FK_idGejala);
        getAllPenyakit(context, c_FK_idGejala);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////                           CONTROLLER UNTUK MODEL                                              //////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method untuk mengambil seluru gejala pada database.
     *
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
//                Toast.makeText(context, "Nama gejala: " + c_Gejala.getString(2), Toast.LENGTH_SHORT).show();//Untuk mengecek
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

    /**
     * Method untuk mengambil seluru kondisi lingkungan pada database.
     *
     * @param context Perlu menggunakan Context untuk mengetahui pada kelas mana yang menggunakannya
     */
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
                Toast.makeText(context, "Nama KondisiLingkungan: " + c_KondisiLingkungan.getString(3), Toast.LENGTH_SHORT).show();//Untuk mengecek
            } while (c_KondisiLingkungan.moveToNext());
        }
//        return tempKondisiLingkungan;
    }

    private void getAllKondisiLingkungan(Context context, ArrayList<String> c_FK_idGejala) {
        ArrayList<ModelKondisiLingkungan> tempModel = new ArrayList<ModelKondisiLingkungan>();
        ModelKondisiLingkungan temp;
        List<String> FK_idGejalaList = new ArrayList<String>(new LinkedHashSet<String>(c_FK_idGejala));
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

        ////TAMBAH TESTING
        for (int i = 0; i < FK_idGejalaList.size(); i++) {
            Log.i("jumlahFKCOn", Integer.toString(FK_idGejalaList.size()));
            c_KondisiLingkungan = dbHelper.query("tabel_kondisilingkungan", null, null, null, null, null, null);
            if (c_KondisiLingkungan.moveToFirst()) {
                do {
                    temp = new ModelKondisiLingkungan(c_KondisiLingkungan.getString(0), c_KondisiLingkungan.getString(1), c_KondisiLingkungan.getString(2), c_KondisiLingkungan.getString(3));
                    if (temp.getFK_idPenyakit().equals(FK_idGejalaList.get(i))) {
                        Log.i("IdGejalaCon", FK_idGejalaList.get(i));
//                        if(i>0 && this.tempKondisiLingkungan.)
                        this.tempKondisiLingkungan.add(temp);
                    }
//                    this.tempKondisiLingkungan.add(new ModelKondisiLingkungan(c_KondisiLingkungan.getString(0), c_KondisiLingkungan.getString(1), c_KondisiLingkungan.getString(2), c_KondisiLingkungan.getString(3)));
//                    Toast.makeText(context, "Nama KondisiLingkungan: " + c_KondisiLingkungan.getString(3), Toast.LENGTH_SHORT).show();//Untuk mengecek
                } while (c_KondisiLingkungan.moveToNext());
            }
        }

        //////////////


//        this.tempKondisiLingkungan = tempModel;
//        return tempKondisiLingkungan;
    }


    public int getDataKondisiLingkunganSize() {
        return this.tempKondisiLingkungan.size();
    }

    public String[] getAllKondisiLingkunganToString(Context context) {
        getAllKondisiLingkungan(context);
        String[] temp = new String[getDataKondisiLingkunganSize()];
        for (int i = 0; i < getDataKondisiLingkunganSize(); i++) {
            temp[i] = this.tempKondisiLingkungan.get(i).getTextKondisiLingkungan();

        }
        return temp;
    }

    public ArrayList<ModelKondisiLingkungan> getTempKondisiLingkungan() {
        return tempKondisiLingkungan;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////                           CONTROLLER UNTUK PENYAKIT                                           //////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method untuk mengambil seluru kondisi lingkungan pada database.
     *
     * @param context Perlu menggunakan Context untuk mengetahui pada kelas mana yang menggunakannya
     */
    private void getAllPenyakit(Context context) {
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
        c_Penyakit = dbHelper.query("tabel_Penyakit", null, null, null, null, null, null);
        if (c_Penyakit.moveToFirst()) {
            do {
                this.tempPenyakit.add(new ModelPenyakit(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3)));
                Toast.makeText(context, "Nama Penyakit: " + c_Penyakit.getString(3), Toast.LENGTH_SHORT).show();//Untuk mengecek
            } while (c_Penyakit.moveToNext());
        }
//        return tempPenyakit;
    }

    private void getAllPenyakit(Context context, ArrayList<String> c_FK_idGejala) {
        ArrayList<ModelPenyakit> tempModel = new ArrayList<ModelPenyakit>();
        ModelPenyakit temp;
        List<String> FK_idGejalaList = new ArrayList<String>(new LinkedHashSet<String>(c_FK_idGejala));
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

//        c_Penyakit = dbHelper.query("tabel_Penyakit", null, null, null, null, null, null);
//        if (c_Penyakit.moveToFirst()) {
//            do {
//                this.tempPenyakit.add(new ModelPenyakit(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3)));
////                Toast.makeText(context, "Nama Penyakit: " + c_Penyakit.getString(2), Toast.LENGTH_SHORT).show();//Untuk mengecek
//            } while (c_Penyakit.moveToNext());
//        }

//        for (int i = 0; i < tempPenyakit.size(); i++) {
//            Log.i("index", Integer.toString(i));
//            if (this.tempPenyakit.get(i).getFK_idGejala().equals(c_FK_idGejala.get(i))) {
//                tempModel.add(this.tempPenyakit.get(i));
//                Toast.makeText(context, "Nama Penyakit: " + tempModel.get(i).toString(), Toast.LENGTH_SHORT).show();
//            }
//    }
        ////TAMBAH TESTING
        for (int i = 0; i < FK_idGejalaList.size(); i++) {
            Log.i("jumlahFK", Integer.toString(FK_idGejalaList.size()));
            c_Penyakit = dbHelper.query("tabel_penyakit", null, null, null, null, null, null);
            if (c_Penyakit.moveToFirst()) {
                do {
                    temp = new ModelPenyakit(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3));
                    if (temp.getIdPenyakit().equals(FK_idGejalaList.get(i))) {
//                        if(i>0 && this.tempPenyakit.)
                        this.tempPenyakit.add(temp);
                    }
//                    this.tempPenyakit.add(new ModelPenyakit(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3)));
//                    Toast.makeText(context, "Nama Penyakit: " + c_Penyakit.getString(3), Toast.LENGTH_SHORT).show();//Untuk mengecek
                } while (c_Penyakit.moveToNext());
            }
        }

        //////////////


//        this.tempPenyakit = tempModel;
//        return tempPenyakit;
    }


    public int getDataPenyakitSize() {
        return this.tempPenyakit.size();
    }

    public String[] getAllPenyakitToString(Context context) {
        getAllPenyakit(context);
        String[] temp = new String[getDataPenyakitSize()];
        for (int i = 0; i < getDataPenyakitSize(); i++) {
            temp[i] = this.tempPenyakit.get(i).getNamaPenyakit();

        }
        return temp;
    }

    public ArrayList<ModelPenyakit> getTempPenyakit() {
        return tempPenyakit;
    }


    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////


    public ArrayList<String> getGejalaByIdPenyakit(String idPenyakit) {
        ModelGejala temp;
        ArrayList<String> gejalaByIdPenyakit = new ArrayList<>();
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

        c_Penyakit = dbHelper.query("tabel_gejala", null, null, null, null, null, null);
        if (c_Penyakit.moveToFirst()) {
            do {
                temp = new ModelGejala(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3));
                if (temp.getFK_IdPenyakit().equals(idPenyakit)) {
                    gejalaByIdPenyakit.add(temp.getKetGejala());
                }
            } while (c_Penyakit.moveToNext());
        }
        return gejalaByIdPenyakit;
    }

    private ArrayList<String> getSaranPencegahanByIdPenyakit(String idPenyakit) {
        ModelSaranPencegahan temp;
        ArrayList<String> saranPencegahanByIdPenyakit = new ArrayList<>();
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

        c_Penyakit = dbHelper.query("tabel_saranpencegahan", null, null, null, null, null, null);
        if (c_Penyakit.moveToFirst()) {
            do {
                temp = new ModelSaranPencegahan(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2));
                if (temp.getFK_idPenyakit().equals(idPenyakit)) {
                    saranPencegahanByIdPenyakit.add(temp.getTextPencegahan());
                }
            } while (c_Penyakit.moveToNext());
        }
        return saranPencegahanByIdPenyakit;
    }

    private ArrayList<String> getSaranPertamaByIdPenyakit(String idPenyakit) {
        ModelSaranPertama temp;
        ArrayList<String> saranPertamaByIdPenyakit = new ArrayList<>();
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
        c_Penyakit = dbHelper.query("tabel_saranpenangananpertama", null, null, null, null, null, null);
        if (c_Penyakit.moveToFirst()) {
            do {
                temp = new ModelSaranPertama(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3));
                if (temp.getFK_idPenyakit().equals(idPenyakit)) {
                    saranPertamaByIdPenyakit.add(temp.getText_SaranPenangananPertama());
                }
            } while (c_Penyakit.moveToNext());
        }

        return saranPertamaByIdPenyakit;
    }

    //
//
    private ArrayList<String> getKondisiByIdPenyakit(String idPenyakit) {
        ModelKondisiLingkungan temp;
        ArrayList<String> kondisiByIdPenyakit = new ArrayList<>();
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
        c_Penyakit = dbHelper.query("tabel_kondisilingkungan", null, null, null, null, null, null);
        if (c_Penyakit.moveToFirst()) {
            do {
                temp = new ModelKondisiLingkungan(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3));
                if (temp.getFK_idPenyakit().equals(idPenyakit)) {
//                        if(i>0 && this.tempPenyakit.)
                    kondisiByIdPenyakit.add(temp.getTextKondisiLingkungan());
                }
//                    this.tempPenyakit.add(new ModelPenyakit(c_Penyakit.getString(0), c_Penyakit.getString(1), c_Penyakit.getString(2), c_Penyakit.getString(3)));
//                    Toast.makeText(context, "Nama Penyakit: " + c_Penyakit.getString(3), Toast.LENGTH_SHORT).show();//Untuk mengecek
            } while (c_Penyakit.moveToNext());
        }

        return kondisiByIdPenyakit;
    }


    public ArrayList<String> getSaranPencegahanByIdPenyakit() {
        return saranPencegahanByIdPenyakit;
    }

    public ArrayList<String> getKondisiByIdPenyakit() {
        return kondisiByIdPenyakit;
    }

    public ArrayList<String> getSaranPertamaByIdPenyakit() {
        return saranPertamaByIdPenyakit;
    }

    public ArrayList<String> getGejalaByIdPenyakit() {
        return gejalaByIdPenyakit;
    }
}
