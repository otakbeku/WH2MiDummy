package wh2mi.pelangi.wh2midummy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoPenyakit extends AppCompatActivity {
    Button btnSaranPencegahan, btnSaranPP, btnSaranRujukan;
    TextView txtNamaPenyakit, txtListGejala, txtListKondisi, txtDeskripsi;
    String idPenyakit, saranRujukan;
    Controller controller;
    ArrayList<String> saranPertamaByIdPenyakit, saranPencegahanByIdPenyakit;
    StringBuffer bufferGejala,
            bufferKondisi, bufferSaranPencegahanByIdPenyakit,
            bufferSaranPertamaByIdPenyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info_penyakit);

        //Instansiai konten
        btnSaranPencegahan = (Button) findViewById(R.id.btnSaranPencegahan);
        btnSaranPP = (Button) findViewById(R.id.btnSaranPP);
        btnSaranRujukan = (Button) findViewById(R.id.btnSaranRujukan);

        txtNamaPenyakit = (TextView) findViewById(R.id.txtNamaPenyakit);
        txtListGejala = (TextView) findViewById(R.id.txtListGejala);
        txtListKondisi = (TextView) findViewById(R.id.txtListKondisi);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);


        ///GET BUNDLE
        Bundle bundle = getIntent().getExtras();
        idPenyakit = bundle.getString("IdPenyakitInfo");
        txtNamaPenyakit.setText(bundle.getString("namaPenyakit"));

        controller = new Controller(this, idPenyakit);
        saranPertamaByIdPenyakit = controller.getSaranPertamaByIdPenyakit();
        saranPencegahanByIdPenyakit = controller.getSaranPencegahanByIdPenyakit();

        saranRujukan = controller.getRujukanByIdPenyakit(idPenyakit);

        //STRINGBUFFER
        bufferGejala = new StringBuffer();
        bufferKondisi = new StringBuffer();
        bufferSaranPencegahanByIdPenyakit = new StringBuffer();
        bufferSaranPertamaByIdPenyakit = new StringBuffer();

        bufferGejala.append("Gejala yang terjadi adalah: ");
        bufferKondisi.append("Kondisi yang mungkin mempengaruhi: ");
        int jumlahGejala = bundle.getInt("jumlahGejalaInfo");
        int jumlahKondisi = bundle.getInt("jumlahKondisiInfo");
        Log.i("jumlahGejalaInfo", Integer.toString(jumlahGejala));
        for (int i = 0; i < jumlahGejala; i++) {
            bufferGejala.append("\n" + (i + 1) + ". " + bundle.getString("gejalake" + i));
        }
        for (int i = 0; i < jumlahKondisi; i++) {
            bufferKondisi.append("\n" + (i + 1) + ". " + bundle.getString("kondisike" + i));
        }
        for (int i = 0; i < saranPencegahanByIdPenyakit.size(); i++) {
            bufferSaranPencegahanByIdPenyakit.append(saranPencegahanByIdPenyakit.get(i));
        }
        for (int i = 0; i < saranPertamaByIdPenyakit.size(); i++) {
            bufferSaranPertamaByIdPenyakit.append(saranPertamaByIdPenyakit.get(i));
        }
        txtListGejala.setText(bufferGejala.toString());
        txtListKondisi.setText(bufferKondisi.toString());
        txtDeskripsi.setText(bundle.getString("deskripsiPenyakit"));

        //BUTTON
        btnSaranPencegahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Saran Pencegahan", bufferSaranPencegahanByIdPenyakit.toString());
            }
        });

        btnSaranPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Saran Penanganan Pertama", bufferSaranPertamaByIdPenyakit.toString());
            }
        });

        btnSaranRujukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("Saran Meminta Rujukan", "Berdasarkan kondisi gejala Anda, maka saran meminta rujukan Anda adalah: "+saranRujukan);
            }
        });
    }

    public void showMessage(String judul, String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //CODENYA DISINI NANTI
            }
        });
        builder.show();
    }


}
