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

public class InputGejala extends Activity {

    private ListView listGejala;
    private Button btn_inputKondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputgejala);

        //contoh
        String[] Gejala = new String[]{
                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi",
                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi"
        };

        //Instansiasi Button
        btn_inputKondisi = (Button) findViewById(R.id.btn_inputKondisi);

        btn_inputKondisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_inputKondisi = new Intent(InputGejala.this,InputKondisiLingkungan.class);
                startActivity(i_inputKondisi);
            }
        });

        //Instansiai listView
        listGejala = (ListView) findViewById(R.id.listGejala);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Gejala);

        listGejala.setAdapter(adapter);

        //Buat nge-Toast doang
        listGejala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listGejala.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "Posisi " + itemPosition + " value: " + itemValue, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
