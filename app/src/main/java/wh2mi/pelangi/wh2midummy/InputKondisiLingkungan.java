package wh2mi.pelangi.wh2midummy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
    //    ArrayList<ModelGejala> modelGejala;
    ArrayList<ModelKondisiLingkungan> kondisiSelected;
    ArrayList<String> idGejalaSelected;
    //    int jumlahGejala;
//    int jumlahGejala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputkondisilingkungan);

        idGejalaSelected = new ArrayList<String>();
        kondisiSelected = new ArrayList<ModelKondisiLingkungan>();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int jumlahGejala = bundle.getInt("jumlahGejala");
            Log.i("jumlahgejaKL ", Integer.toString(jumlahGejala));

            for (int i = 0; i < jumlahGejala; i++) {
                idGejalaSelected.add(i, bundle.getString("idGejala-" + i));
//                Toast.makeText(getApplicationContext(), "Anda  memilih gejala " + idGejalaSelected.get(i).toString(), Toast.LENGTH_SHORT).show();
                Log.i("gejalaSelected: ", idGejalaSelected.get(i).toString());
            }
        }


        controller = new Controller(InputKondisiLingkungan.this, idGejalaSelected);

        ////////////////////////////////////////////////////
        /////INTENT & PASSING DATA
        ////////////////////////////////////////////////////
        btn_Diagnosa = (Button) findViewById(R.id.btn_Diagnosa);
        //Intent untuk ke halaman diagnosa
        btn_Diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ModelKondisiLingkungan> kondisiLingkunganList = adapterKondisiLingkungan.KondisiLingkunganList;
                for (int i = 0; i < kondisiLingkunganList.size(); i++) {
                    ModelKondisiLingkungan modelKondisiLingkungan = kondisiLingkunganList.get(i);
                    if (modelKondisiLingkungan.isSelected()) {
                        kondisiSelected.add(modelKondisiLingkungan);
                    }
                }

                Intent i_cardDiagnosa = new Intent(InputKondisiLingkungan.this, cardViewDiagnosa.class);
                Bundle bundle = new Bundle();
                if (getKondisiSelected() != null) {
                    int jumlahKondisi = getKondisiSelected().size();
                    bundle.putInt("jumlahKondisi", jumlahKondisi);
                    bundle.putInt("jumlahGejala", idGejalaSelected.size());

                    //KONDISI
                    for (int i = 0; i < jumlahKondisi; i++) {
                        bundle.putString("idKondisi" + i, getKondisiSelected().get(i).getTextKondisiLingkungan());
                    }

                    //GEJALA
                    for (int i = 0; i < idGejalaSelected.size(); i++) {
                        bundle.putString("idGejala" + i, idGejalaSelected.get(i));
                    }

                    if (bundle != null) {
                        i_cardDiagnosa.putExtras(bundle);
                        startActivity(i_cardDiagnosa);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Anda belum memilih gejala", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////////////////////////////////////////////////////

        //JANGAN LUPAAA
        displayKondisiLingkunganListView();
        checkButtonClick();
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
                Toast.makeText(getApplicationContext(), "Memilih: " + modelKondisiLingkungan.getTextKondisiLingkungan(), Toast.LENGTH_SHORT).show();
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
//                        Toast.makeText(getApplicationContext(), "User memilih: " + cbxKondisiLingkungan.getText().toString()
//                                + " nilai: " + cbxKondisiLingkungan.isChecked(), Toast.LENGTH_SHORT).show();
                        modelKondisiLingkungan.setSelected(cbxKondisiLingkungan.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ModelKondisiLingkungan modelKondisiLingkungan = KondisiLingkunganList.get(position);
//            holder.namaKondisiLingkungan.setText(modelKondisiLingkungan.getKetKondisiLingkungan());//lebih baik di ganti dengan ketKondisiLingkungan, soalnya nambah ribet kalo di tambah textview
            holder.namaKondisiLingkungan.setText(modelKondisiLingkungan.getTextKondisiLingkungan());
            holder.checkboxKondisiLingkungan.setText("KondisiLingkungan"); // tidak perlu di pakai, yang digunakan hanya keterangan KondisiLingkungan
//            holder.checkboxKondisiLingkungan.setText("");
            holder.checkboxKondisiLingkungan.setChecked(modelKondisiLingkungan.isSelected());
            holder.checkboxKondisiLingkungan.setTag(modelKondisiLingkungan);

//            Toast.makeText(getApplicationContext(), "kondisi: " + modelKondisiLingkungan.getKetKondisiLingkungan(), Toast.LENGTH_LONG).show();

            Log.i("kondisiKL", modelKondisiLingkungan.getTextKondisiLingkungan());

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
                        responText.append("\n-> " + modelKondisiLingkungan.getTextKondisiLingkungan());
                        kondisiSelected.add(modelKondisiLingkungan);
                    }

                }
//                Toast.makeText(getApplicationContext(), responText, Toast.LENGTH_SHORT).show();
                showMessage("Data yang ditampilkan", responText.toString());
            }
        });
    }

    public ArrayList<ModelKondisiLingkungan> getKondisiSelected() {
        return kondisiSelected;
    }


    public void showMessage(String judul, String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!kondisiSelected.isEmpty()) {
            kondisiSelected.clear();
        }
    }
}
