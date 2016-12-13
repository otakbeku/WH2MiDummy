package wh2mi.pelangi.wh2midummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class InfoPenyakit extends AppCompatActivity {
    Button btnSaranPencegahan, btnSaranPP;
    TextView txtNamaPenyakit, txtListGejala, txtListKondisi, txtDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_penyakit);

        //Instansiai konten
        btnSaranPencegahan = (Button) findViewById(R.id.btnSaranPencegahan);
        btnSaranPP = (Button) findViewById(R.id.btnSaranPP);

        txtNamaPenyakit = (TextView) findViewById(R.id.txtNamaPenyakit);
        txtListGejala = (TextView) findViewById(R.id.txtListGejala);
        txtListKondisi = (TextView) findViewById(R.id.txtListKondisi);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);


        ///GET BUNDLE
        Bundle bundle = getIntent().getExtras();
        txtNamaPenyakit.setText(bundle.getString("namaPenyakit"));
        StringBuffer buffer = new StringBuffer();
        buffer.append("Gejala yang terjadi adalah: ");
        int jumlahGejala = bundle.getInt("jumlahGejalaInfo");
        Log.i("jumlahGejalaInfo", Integer.toString(jumlahGejala));
        for (int i = 0; i < jumlahGejala; i++) {
            buffer.append("\n" + (i + 1) + ". " + bundle.getString("gejalake" + i));
        }
        txtListGejala.setText(buffer.toString());
        txtDeskripsi.setText(bundle.getString("deskripsiPenyakit"));
    }
}
