package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/1/2016.
 */

public class DBCREATOR_none {

    //Untuk SQLite

    //INISIALISASI KOLOM TABEL
    //GEJALA
    private static final String TABLE_GEJALA = "tabel_gejala";
    private static final String GEJALA_ID = "idGejala";
    private static final String FK_IdPenyakit = "FK_IdPenyakit";
    private static final String GEJALA_TEXT = "textGejala";
    private static final String GEJALA_KETERANGAN = "ketGejala";


    //PENYAKIT
    private static final String TABLE_PENYAKIT = "tabel_penyakit";
    private static final String PENYAKIT_ID = "idPenyakit";
    private static final String PENYAKIT_PENYEBAB = "PenyebabPenyakit";
    private static final String PENYAKIT_NAMA = "namaPenyakit";
    private static final String PENYAKIT_DESKRIPSI = "deskripsiPenyakit";

    //KONDISI LINGKUNGAN
    private static final String TABLE_KONDISILINGKUNGAN = "tabel_kondisilingkungan";
    private static final String KONDISI_ID = "idKondisiLingkungant";
    private static final String KONDISI_KETERANGAN = "ketKondisi";
    private static final String KONDISI_TEXT = "TextKondisiLingkungan";
    private static final String FK_idGejala = "FK_idGejala";

    //SARAN PENANGANAN PERTAMA
    private static final String TABLE_PENANGANANPERTAMA = "tabel_penangananpertama";
    private static final String SARANPP_ID = "idSaranPenangananPertama";
    private static final String SARANPP_TEXT = "Text_SaranPenanganPertama";
    private static final String FK_idKondisiLingkungan = "FK_idKondisiLingkungan";
    private static final String FK_idPenyakitSPP = "FK_idPenyakit";

    //SARAN PENCEGAHAN
    private static final String TABLE_PENCEGAHAN = "tabel_pencegahan";
    private static final String SARANP_ID = "idSaranPencegahan";
    private static final String SARANP_TEXT = "TextPencegahan";
    private static final String FK_idPenyakitSP = "FK_idPenyakit";

    //QUERY CREATE TABLE
    //UNTUK MEMBUAT DATABASE, YANG PERTAMA KALI DI CREATE ADALAH
    //PENYAKIT->GEJALA->KONDISI LINGKUNGAN->SARAN PENANGANAN PERTAMA->SARAN PENCEGAHAN


    //PENYAKIT
    private static final String TABLE_CREATE_PENYAKIT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PENYAKIT + " ("
            + GEJALA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GEJALA_KETERANGAN + " TEXT NOT NULL,"
            + GEJALA_TEXT + "TEXT,"
            + FK_IdPenyakit + "INTEGER,"
            + "FOREIGN KEY (" + FK_IdPenyakit;

    //GEJALA
    private static final String TABLE_CREATE_GEJALA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GEJALA + " ("
            + GEJALA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GEJALA_KETERANGAN + " TEXT NOT NULL,"
            + GEJALA_TEXT + "TEXT,"
            + FK_IdPenyakit + "INTEGER,"
            + "FOREIGN KEY (" + FK_IdPenyakit;

}
