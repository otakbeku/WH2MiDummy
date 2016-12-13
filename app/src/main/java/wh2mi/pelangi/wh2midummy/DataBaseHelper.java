package wh2mi.pelangi.wh2midummy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kotak Hitam on 12/6/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "DBcek.db";
    private SQLiteDatabase myDB;
    private final Context context;


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    public void createDataBase() throws IOException {
        boolean dbExists = checkDatabse();
        if (dbExists) {

        } else {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabse() {
        SQLiteDatabase checkDB = null;
        try {
            String Path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(Path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void openDataBase() throws SQLiteException {
        String Path = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(Path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDB != null) {
            myDB.close();
            super.close();
        }
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] seletionArgs, String grouBy, String having, String orderBy) {
        return myDB.query(table, columns, selection, seletionArgs, grouBy, having, orderBy);
    }
}
