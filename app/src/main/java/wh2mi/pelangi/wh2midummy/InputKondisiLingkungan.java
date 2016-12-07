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

import java.util.ArrayList;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class InputKondisiLingkungan extends Activity {
    private ListView listKondisi;
    private Button btn_Diagnosa;

    CustomAdapterKondisiLingkungan adapterKondisiLingkungan;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputkondisilingkungan);


        //Intent untuk ke halaman diagnosa
        btn_Diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i_hasilDiagnosa = new Intent(InputKondisiLingkungan.this, HasilDiagnosa.class);
//                startActivity(i_hasilDiagnosa);

                Intent i_cardDiagnosa = new Intent(InputKondisiLingkungan.this, cardViewDiagnosa.class);
                startActivity(i_cardDiagnosa);
            }
        });

//        //CONTOH
//        String[] Gejala = new String[]{
//                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
//                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi",
//                "Pusing", "muntah", "memar", "gatal", "sakit sendi", "Pusing", "muntah", "memar",
//                "gatal", "sakit sendi", "Pusing", "muntah", "memar", "gatal", "sakit sendi"
//        };
//
//
//        ArrayAdapter<String> adapter_kondisi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Gejala);
//
//        //instansiasi ListView
//        listKondisi = (ListView) findViewById(R.id.listKondisi);
//        listKondisi.setAdapter(adapter_kondisi);
//
//        //Instansiasi Button
//        btn_Diagnosa = (Button) findViewById(R.id.btn_Diagnosa);
//
//        //Buat nge-Toast doang
//        listKondisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int itemPosition = position;
//
//                String itemValue = (String) listKondisi.getItemAtPosition(position);
//
//                Toast.makeText(getApplicationContext(), "Posisi " + itemPosition + " value: " + itemValue, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * Method untuk menampilkan KondisiLingkungan yang di fetch dari database.
     * Data yang diambil dari tabel KondisiLingkungan di taruh di array list, yang kemudian di berikan ke adapter
     */
    private void displayKondisiLingkunganListView() {
        ArrayList<ModelKondisiLingkungan> modelKondisiLingkunganArrayList = controller.getTempKondisiLingkungan();
        adapterKondisiLingkungan = new CustomAdapterKondisiLingkungan(this, R.layout.kondisilingkungan_info, modelKondisiLingkunganArrayList);
        ListView listView = (ListView) findViewById(R.id.listKondisiLingkungan);

        //Assign adapter ke listview
        listView.setAdapter(adapterKondisiLingkungan);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelKondisiLingkungan modelKondisiLingkungan = (ModelKondisiLingkungan) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), "Memilih: " + modelKondisiLingkungan.getKetKondisiLingkungan(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Kelas khusus pengganti adapter. Secara fungsi sama dengan adapter untuk listview, dan bisa digunakan dengan baik.
     * Cara menggunakannya adalah dengan mengirimkan context, layout dari listviewnya, serta arraylist dari model KondisiLingkungan.
     * data dari tabel KondisiLingkungan akan langsung di masukan ke konten dengan menggunakan kelas ViewHolder.
     */
    private class CustomAdapterKondisiLingkungan extends ArrayAdapter<ModelKondisiLingkungan> {

        private ArrayList<ModelKondisiLingkungan> KondisiLingkunganList;

        public CustomAdapterKondisiLingkungan(Context context, int resource, ArrayList<ModelKondisiLingkungan> KondisiLingkunganList) {
            super(context, resource, KondisiLingkunganList);
            this.KondisiLingkunganList = new ArrayList<ModelKondisiLingkungan>();
            this.KondisiLingkunganList.addAll(KondisiLingkunganList);
        }

        /***
         * kelas untuk mengatur hubungan layout dengan adapter. Atribut bisa disesuaikan
         */
        private class ViewHolder {
            TextView namaKondisiLingkungan;
            CheckBox checkboxKondisiLingkungan;
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
                convertView = inflater.inflate(R.layout.kondisilingkungan_info, null);

                holder = new ViewHolder();

                holder.namaKondisiLingkungan = (TextView) convertView.findViewById(R.id.namaKondisiLingkungan);
                holder.checkboxKondisiLingkungan = (CheckBox) convertView.findViewById(R.id.checkboxKondisiLingkungan);
                convertView.setTag(holder);

                //Untuk Checkbox
                holder.checkboxKondisiLingkungan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox cbxKondisiLingkungan = (CheckBox) view;
                        ModelKondisiLingkungan modelKondisiLingkungan = (ModelKondisiLingkungan) cbxKondisiLingkungan.getTag();

                        //Test dengan toast
                        Toast.makeText(getApplicationContext(), "User memilih: " + cbxKondisiLingkungan.getText()
                                + " nilai: " + cbxKondisiLingkungan.isChecked(), Toast.LENGTH_SHORT).show();
                        modelKondisiLingkungan.setSelected(cbxKondisiLingkungan.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ModelKondisiLingkungan modelKondisiLingkungan = KondisiLingkunganList.get(position);
//            holder.namaKondisiLingkungan.setText(modelKondisiLingkungan.getKetKondisiLingkungan());//lebih baik di ganti dengan ketKondisiLingkungan, soalnya nambah ribet kalo di tambah textview
            holder.namaKondisiLingkungan.setText("");
//            holder.checkboxKondisiLingkungan.setText("KondisiLingkungan"); // tidak perlu di pakai, yang digunakan hanya keterangan KondisiLingkungan
            holder.checkboxKondisiLingkungan.setText(modelKondisiLingkungan.getKetKondisiLingkungan());
            holder.checkboxKondisiLingkungan.setChecked(modelKondisiLingkungan.isSelected());
            holder.checkboxKondisiLingkungan.setTag(modelKondisiLingkungan);


            return convertView;
        }
    }

    /**
     * method untuk memastikan apa saja yang sudah dipilih oler user. Tinggal di panggil aja, tapi hanya khusus untuk kelas ini saja.
     */
    private void checkButtonClick() {
        Button btn_cekInputKondisiLingkungan = (Button) findViewById(R.id.btn_cekInputKondisiLingkungan);
        btn_cekInputKondisiLingkungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer responText = new StringBuffer();
                responText.append("KondisiLingkungan yang sudah dipilih adalah: \n");

                ArrayList<ModelKondisiLingkungan> KondisiLingkunganList = adapterKondisiLingkungan.KondisiLingkunganList;
                for (int i = 0; i < KondisiLingkunganList.size(); i++) {
                    ModelKondisiLingkungan modelKondisiLingkungan = KondisiLingkunganList.get(i);
                    if (modelKondisiLingkungan.isSelected()) {
                        responText.append("\n-> " + modelKondisiLingkungan.getKetKondisiLingkungan());
                    }

                }
                Toast.makeText(getApplicationContext(), responText, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
