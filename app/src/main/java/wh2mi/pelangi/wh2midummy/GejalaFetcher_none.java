package wh2mi.pelangi.wh2midummy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kotak Hitam on 12/1/2016.
 */

public class GejalaFetcher_none extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dummyDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH = "/data/data/wh2mi.pelangi.whi2midmmy/database";

    private static final String tabelPenyakit = "tabel_gejala";
    private static final String sqlSelectAll = "SELECT * FROM " + DATABASE_NAME;
    private static final String dropTable = "DROP TABLE IF EXISTS";

    private final Context contextDB;

    private SQLiteDatabase DB_WH2Mi;
    private DatabaseHelper mDbHelper;
    private Cursor c;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public GejalaFetcher_none(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.contextDB = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

//    public void createDatabase() throws IOException {
//        boolean dbExist = checkDatabase();
//        if (dbExist) {
//
//        } else {
//            this.getReadableDatabase();
//            try {
//                copyDatabase();
//            } catch (IOException e) {
//                throw new Error("Gagal mengcopy database");
//            }
//        }
//    }

//    private void copyDatabase() throws IOException {
//        InputStream input = contextDB.getAssets().open(DATABASE_NAME);
//
//        String outFileName = DB_PATH + DATABASE_NAME;
//
//        OutputStream output = new FileOutputStream(outFileName);
//
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = input.read(buffer)) > 0) {
//            output.write(buffer, 0, length);
//        }
//
//        output.flush();
//        output.close();
//        input.close();
//    }

//    private boolean checkDatabase() {
//        SQLiteDatabase checkDB = null;
//        try {
//            String dbPATH = DB_PATH + DATABASE_NAME;
//            checkDB = SQLiteDatabase.openDatabase(dbPATH, null, SQLiteDatabase.OPEN_READONLY);
//        } catch (SQLiteException e) {
//            Log.e("Tidak membaca SQL/db : ", e.toString());
//        }
//        if (checkDB != null) {
//            checkDB.close();
//        }
//
//        return checkDB != null ? true : false;
//    }

    /**
     * Method untuk mengupgrade database
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(GejalaFetcher_none.class.getName(), "Upgrading database from " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("");
        onCreate(db);
    }

//    public void openDatabase() {
//        String path = DB_PATH + DATABASE_NAME;
//        DB_WH2Mi = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//
//    }
}
