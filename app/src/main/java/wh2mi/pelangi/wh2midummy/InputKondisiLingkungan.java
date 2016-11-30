package wh2mi.pelangi.wh2midummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class InputKondisiLingkungan extends Activity {
    private ListView listKondisi;
    private Button btn_Diagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputkondisilingkungan);

        //contoh
        String[] Gejala = new String[]{
                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi",
                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi"
        };


        ArrayAdapter<String> adapter_kondisi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Gejala);

        //instansiasi ListView
        listKondisi = (ListView) findViewById(R.id.listKondisi);
        listKondisi.setAdapter(adapter_kondisi);

        //Instansiasi Button
        btn_Diagnosa = (Button) findViewById(R.id.btn_Diagnosa);

        //Buat nge-Toast doang
        listKondisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listKondisi.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "Posisi " + itemPosition + " value: " + itemValue, Toast.LENGTH_SHORT).show();
            }
        });


        btn_Diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i_hasilDiagnosa = new Intent(InputKondisiLingkungan.this, HasilDiagnosa.class);
//                startActivity(i_hasilDiagnosa);

                Intent i_cardDiagnosa = new Intent(InputKondisiLingkungan.this, cardViewDiagnosa.class);
                startActivity(i_cardDiagnosa);
            }
        });

    }
}
