package wh2mi.pelangi.wh2midummy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class InputGejala extends Activity {

    private ListView listGejala;
    private Button btn_inputKondisi;

    //tambahan
    CustomAdapterGejala adapterGejala;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputgejala);

        //TAMBAHAN GEJALA
        controller = new Controller(InputGejala.this);


        //Instansiasi Button
        btn_inputKondisi = (Button) findViewById(R.id.btn_inputKondisi);

        //Intent untuk ke halaman input gejala
        btn_inputKondisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_inputKondisi = new Intent(InputGejala.this, InputKondisiLingkungan.class);
                startActivity(i_inputKondisi);
            }
        });

        //TAMBAHAN UNTUK ADAPTER
        //SUDAH BISA
        displayGejalaListView();
        checkButtonClick();

        //CONTOH
        //        controller.getAllGejala(InputGejala.this);
//        String[] GejalaBaru = controller.getAllGejalaToString(InputGejala.this);//ini ga perlu lagi

//        boolean cek = false;

//        if (GejalaBaru != null) {
//            cek = true;
//            Toast.makeText(getApplicationContext(), "Posisi " + cek, Toast.LENGTH_SHORT).show();
//        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        String[] Gejala = new String[]{
//                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
//                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi",
//                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
//                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi"
//        };
//
//        //Instansiai listView
//        listGejala = (ListView) findViewById(R.id.listGejala);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, GejalaBaru);
//
//        listGejala.setAdapter(adapter);
//
//        //Buat nge-Toast doang
//        listGejala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int itemPosition = position;
//
//                String itemValue = (String) listGejala.getItemAtPosition(position);
//
//                Toast.makeText(getApplicationContext(), "Posisi " + itemPosition + " value: " + itemValue, Toast.LENGTH_SHORT).show();
//            }
//        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }

    /**
     * Method untuk menampilkan gejala yang di fetch dari database.
     * Data yang diambil dari tabel gejala di taruh di array list, yang kemudian di berikan ke adapter
     */
    private void displayGejalaListView() {
        ArrayList<ModelGejala> modelGejalaArrayList = controller.getTempGejala();
        adapterGejala = new CustomAdapterGejala(this, R.layout.gejala_info, modelGejalaArrayList);
        ListView listView = (ListView) findViewById(R.id.listGejala);

        //Assign adapter ke listview
        listView.setAdapter(adapterGejala);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelGejala modelGejala = (ModelGejala) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), "Memilih: " + modelGejala.getKetGejala(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Kelas khusus pengganti adapter. Secara fungsi sama dengan adapter untuk listview, dan bisa digunakan dengan baik.
     * Cara menggunakannya adalah dengan mengirimkan context, layout dari listviewnya, serta arraylist dari model gejala.
     * data dari tabel gejala akan langsung di masukan ke konten dengan menggunakan kelas ViewHolder.
     */
    private class CustomAdapterGejala extends ArrayAdapter<ModelGejala> {

        private ArrayList<ModelGejala> gejalaList;

        public CustomAdapterGejala(Context context, int resource, ArrayList<ModelGejala> gejalaList) {
            super(context, resource, gejalaList);
            this.gejalaList = new ArrayList<ModelGejala>();
            this.gejalaList.addAll(gejalaList);
        }

        /***
         * kelas untuk mengatur hubungan layout dengan adapter. Atribut bisa disesuaikan
         */
        private class ViewHolder {
            TextView namaGejala;
            CheckBox checkboxGejala;
        }

        /**
         * Method untuk mendapatkan view
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gejala_info, null);

                holder = new ViewHolder();

                holder.namaGejala = (TextView) convertView.findViewById(R.id.namaGejala);
                holder.checkboxGejala = (CheckBox) convertView.findViewById(R.id.checkboxGejala);
                convertView.setTag(holder);

                //Untuk Checkbox
                holder.checkboxGejala.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox cbxGejala = (CheckBox) view;
                        ModelGejala modelGejala = (ModelGejala) cbxGejala.getTag();

                        //Test dengan toast
                        Toast.makeText(getApplicationContext(), "User memilih: " + cbxGejala.getText()
                                + " nilai: " + cbxGejala.isChecked(), Toast.LENGTH_SHORT).show();
                        modelGejala.setSelected(cbxGejala.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ModelGejala modelGejala = gejalaList.get(position);
//            holder.namaGejala.setText(modelGejala.getKetGejala());//lebih baik di ganti dengan ketGejala, soalnya nambah ribet kalo di tambah textview
            holder.namaGejala.setText("");
//            holder.checkboxGejala.setText("gejala"); // tidak perlu di pakai, yang digunakan hanya keterangan gejala
            holder.checkboxGejala.setText(modelGejala.getKetGejala());
            holder.checkboxGejala.setChecked(modelGejala.isSelected());
            holder.checkboxGejala.setTag(modelGejala);


            return convertView;
        }
    }

    /**
     * method untuk memastikan apa saja yang sudah dipilih oler user. Tinggal di panggil aja, tapi hanya khusus untuk kelas ini saja.
     */
    private void checkButtonClick() {
        Button btn_cekInputGejala = (Button) findViewById(R.id.btn_cekInputgejala);
        btn_cekInputGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer responText = new StringBuffer();
                responText.append("Gejala yang sudah dipilih adalah: \n");

                ArrayList<ModelGejala> gejalaList = adapterGejala.gejalaList;
                for (int i = 0; i < gejalaList.size(); i++) {
                    ModelGejala modelGejala = gejalaList.get(i);
                    if (modelGejala.isSelected()) {
                        responText.append("\n-> " + modelGejala.getKetGejala());
                    }

                }
                Toast.makeText(getApplicationContext(), responText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}