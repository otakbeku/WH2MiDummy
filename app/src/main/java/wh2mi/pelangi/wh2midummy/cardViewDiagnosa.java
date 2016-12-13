package wh2mi.pelangi.wh2midummy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Kotak Hitam on 12/1/2016.
 */

public class cardViewDiagnosa extends ActionBarActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static String LOG_TAG = "CardViewDiagnosa";
    private Bundle bundle;
    private ArrayList<String> idGejalaSelected, textKondisi;
    private ArrayList<ModelPenyakit> modelPenyakitArrayList;
    private Controller controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerdiagnosa);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerDiagnosa);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterDiagnosa(getDataset());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        );

        idGejalaSelected = new ArrayList<String>();
        textKondisi = new ArrayList<String>();


        ///BUNDLE
        bundle = getIntent().getExtras();
        if (bundle != null) {
            //GEJALA
            int jumlahGejala = bundle.getInt("jumlahGejala");
            Log.i("jumlahgejaCD ", Integer.toString(jumlahGejala));

            for (int i = 0; i < jumlahGejala; i++) {
                idGejalaSelected.add(i, bundle.getString("idGejala" + i));
                Log.i("gejalaSelectedCD: ", idGejalaSelected.get(i).toString());
            }
            //KONDISI PENYAKIT
            int jumlahKondisi = bundle.getInt("jumlahKondisi");
            Log.i("jumlahKondisiCD", Integer.toString(jumlahKondisi));
            for (int i = 0; i < jumlahKondisi; i++) {
                textKondisi.add(i, bundle.getString("idKondisi" + i));
                Log.i("kondisiSelectedCD: ", textKondisi.get(i).toString());
            }
        }
        controller = new Controller(cardViewDiagnosa.this, idGejalaSelected);
        modelPenyakitArrayList = controller.getTempPenyakit();

    }


    @Override
    protected void onResume() {
        super.onResume();
//        modelPenyakitArrayList = controller.getTempPenyakit();
        ((AdapterDiagnosa) adapter).setOnItemClickListener(new AdapterDiagnosa.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, "menekan " + position);
//                showMessage("Hasil Diagnosa anda", "Posisi: " + position);
//                if(modelPenyakitArrayList.get(position).isSelected()) {
//                showMessage("Hasil Diagnosa anda", "Isi: " + modelPenyakitArrayList.get(position).getDeskripsiPenyakit());
//                }
//    kalo mau nambah intent bisa disini


                ///// testing
                Bundle bundle = new Bundle();
                ArrayList<String> gejalaByIdPenyakit = new ArrayList<String>();
                controller = new Controller(cardViewDiagnosa.this, modelPenyakitArrayList.get(position).getIdPenyakit());
                gejalaByIdPenyakit = controller.getGejalaByIdPenyakit();

                bundle.putString("namaPenyakit", modelPenyakitArrayList.get(position).getNamaPenyakit());
                bundle.putString("IdPenyakitInfo", modelPenyakitArrayList.get(position).getIdPenyakit());
                bundle.putInt("jumlahGejalaInfo", gejalaByIdPenyakit.size());
                for (int i = 0; i < gejalaByIdPenyakit.size(); i++) {
                    bundle.putString("gejalake" + i, gejalaByIdPenyakit.get(i));
                }
                bundle.putString("deskripsiPenyakit", modelPenyakitArrayList.get(position).getDeskripsiPenyakit());

                Intent i_infoPenyakit = new Intent(cardViewDiagnosa.this, InfoPenyakit.class);
                i_infoPenyakit.putExtras(bundle);
                startActivity(i_infoPenyakit);


            }
        });
    }

    public ArrayList<ModelObject> setArray() {
        ///BUNDLE
        idGejalaSelected = new ArrayList<String>();
        textKondisi = new ArrayList<String>();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            //GEJALA
            int jumlahGejala = bundle.getInt("jumlahGejala");
            Log.i("jumlahgejaCD ", Integer.toString(jumlahGejala));

            for (int i = 0; i < jumlahGejala; i++) {
                idGejalaSelected.add(i, bundle.getString("idGejala" + i));
                Log.i("gejalaSelectedCD: ", idGejalaSelected.get(i).toString());
            }
            //KONDISI PENYAKIT
            int jumlahKondisi = bundle.getInt("jumlahKondisi");
            Log.i("jumlahKondisiCD", Integer.toString(jumlahKondisi));
            for (int i = 0; i < jumlahKondisi; i++) {
                textKondisi.add(i, bundle.getString("idKondisi" + i));
                Log.i("kondisiSelectedCD: ", textKondisi.get(i).toString());
            }
        }
        controller = new Controller(cardViewDiagnosa.this, idGejalaSelected);
        modelPenyakitArrayList = controller.getTempPenyakit();
        ArrayList<ModelPenyakit> temp = getModelPenyakitArrayList();
        ArrayList result = new ArrayList<ModelObject>();

//
//        String[] Dataset_diagnosa = new String[]{
//                "kanker1", "kanker2", "kanker3", "kanker4"
//        };
//        String[] Dataset_desk = new String[]{
//                "kanker1: kurang makan", "kanker2: kurang olahraga", "kanker3: kurang kasih sayang", "kanker4: jomblo"
//        };

//        for (int i = 0; i < Dataset_desk.length; i++) {
//            ModelObject obj = new ModelObject(Dataset_diagnosa[i], Dataset_desk[i]);
//            result.add(i, obj);
//        }

        ////////////////////////////////////////////////////
        //BUAT INSTANSIASI BARU NANTI DISINI
        ////////////////////////////////////////////////////
//        ArrayList<String> judul = new ArrayList<>();
//        ArrayList<String> deskripsi = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {
            ModelObject obj = new ModelObject(temp.get(i).getNamaPenyakit(),
                    temp.get(i).getDeskripsiPenyakit());
            result.add(i, obj);
        }


        return result;
    }

    private ArrayList<ModelObject> getDataset() {
        ArrayList result = setArray();
        //buat nyoba
//        for (int index = 0; index < 20; index++) {
//            ModelObject object = new ModelObject("teks satu " + index, "teks dua " + index);
//            result.add(index, object);
//        }
        return result;
    }

    public void showMessage(String judul, String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.show();
    }


    ////////////////////////////////////////////////////
    ///////UNTUK PENYAKIT
    ////////////////////////////////////////////////////
    private ArrayList<ModelPenyakit> getModelPenyakitArrayList() {
        return modelPenyakitArrayList;
    }


}
