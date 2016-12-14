package wh2mi.pelangi.wh2midummy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class InputGejala extends Activity {

    private ListView listGejala;
    private Button btnInputKondisi;
    CustomAdapterGejala adapterGejala;
    Controller controller;

    ArrayList<ModelGejala> gejalaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_inputgejala);

        //INSTANSIASI
        gejalaSelected = new ArrayList<ModelGejala>();
        controller = new Controller(InputGejala.this);

        btnInputKondisi = (Button) findViewById(R.id.btn_inputKondisi);

        //Intent untuk ke halaman input kondisi
        btnInputKondisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ModelGejala> gejalaList = adapterGejala.gejalaList;
                for (int i = 0; i < gejalaList.size(); i++) {
                    ModelGejala modelGejala = gejalaList.get(i);
                    if (modelGejala.isSelected()) {
                        gejalaSelected.add(modelGejala);
                    }
                }

                Intent i_inputKondisi = new Intent(InputGejala.this, InputKondisiLingkungan.class);
                Bundle bundle = new Bundle();
                if (getSelectedGejala() != null) {
                    int jumlahGejala = getSelectedGejala().size();
                    bundle.putInt("jumlahGejala", jumlahGejala);
                    for (int i = 0; i < jumlahGejala; i++) {
                        bundle.putString("idGejala-" + i, getSelectedGejala().get(i).getFK_IdPenyakit());
                        Log.i("idGejala: ", getSelectedGejala().get(i).getFK_IdPenyakit());
                    }
                    if (bundle != null) {
                        i_inputKondisi.putExtras(bundle);
                        startActivity(i_inputKondisi);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Anda belum memilih gejala", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //TAMBAHAN UNTUK ADAPTER
        //SUDAH BISA
        displayGejalaListView();
        checkButtonClick();

    }

    /**
     * Method untuk menampilkan gejala yang di fetch dari database.
     * Data yang diambil dari tabel gejala di taruh di array list, yang kemudian di berikan ke adapter
     */
    private void displayGejalaListView() {
        ArrayList<ModelGejala> modelGejalaArrayList = controller.getTempGejala();
        adapterGejala = new CustomAdapterGejala(this, R.layout.adapter_gejala, modelGejalaArrayList);
        ListView listView = (ListView) findViewById(R.id.listGejala);

        //Assign adapter ke listview
        listView.setAdapter(adapterGejala);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("POSISI", Integer.toString(i));
//                ModelGejala modelGejala = (ModelGejala) adapterView.getItemAtPosition(i);
//                Toast.makeText(getApplicationContext(), "Memilih: " + modelGejala.getKetGejala(), Toast.LENGTH_SHORT).show();
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
                convertView = inflater.inflate(R.layout.adapter_gejala, null);

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
                        modelGejala.setSelected(cbxGejala.isChecked());

                        //Test dengan toast
//                        Toast.makeText(getApplicationContext(), "User memilih: " + cbxGejala.getText().toString()
//                                + " nilai: " + cbxGejala.isChecked(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ModelGejala modelGejala = gejalaList.get(position);
//            holder.namaGejala.setText(modelGejala.getKetGejala());//lebih baik di ganti dengan ketGejala, soalnya nambah ribet kalo di tambah textview
            holder.namaGejala.setText(modelGejala.getKetGejala());
//            holder.checkboxGejala.setText("gejala"); // tidak perlu di pakai, yang digunakan hanya keterangan gejala
            holder.checkboxGejala.setText("");
            holder.checkboxGejala.setChecked(modelGejala.isSelected());
            holder.checkboxGejala.setTag(modelGejala);


            return convertView;
        }
    }

    /**
     * method untuk memastikan apa saja yang sudah dipilih oler user. Tinggal di panggil aja,
     * tapi hanya khusus untuk kelas ini saja.
     */
    private void checkButtonClick() {
        Button btn_cekInputGejala = (Button) findViewById(R.id.btn_cekInputgejala);
        btn_cekInputGejala.setVisibility(View.GONE);
        btn_cekInputGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer responText = new StringBuffer();
                responText.append("Gejala yang sudah dipilih adalah: \n");

                ArrayList<ModelGejala> gejalaList = adapterGejala.gejalaList;
                for (int i = 0; i < gejalaList.size(); i++) {
                    ModelGejala modelGejala = gejalaList.get(i);
                    if (modelGejala.isSelected()) {
                        responText.append("\n-> " + modelGejala.getKetGejala() + " idPenyakit: " + modelGejala.getFK_IdPenyakit());
//                        gejalaSelected.get(i).setIdGejala(modelGejala.getIdGejala());
//                        gejalaSelected.add(modelGejala.getIdGejala());
                        gejalaSelected.add(modelGejala);
                    }

                }
                showMessage("Data yang dipilih", responText.toString());
//                Toast.makeText(getApplicationContext(), responText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<ModelGejala> getSelectedGejala() {
        return gejalaSelected;

    }

    public void showMessage(String judul, String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.show();
    }

    //UNTUK NGOSONGIN ARRAYLST
    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(getApplicationContext(), "onresume", Toast.LENGTH_SHORT).show();
        //UNTUK NGOSONGIN
        if (!gejalaSelected.isEmpty()) {
            gejalaSelected.clear();
        }
    }
}