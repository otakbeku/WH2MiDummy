package wh2mi.pelangi.wh2midummy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
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
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((AdapterDiagnosa) adapter).setOnItemClickListener(new AdapterDiagnosa.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, "menekan " + position);
//    kalo mau nambah intent bisa disini
            }
        });
    }
    public ArrayList<ModelObject> setArray() {
        ArrayList result = new ArrayList<ModelObject>();
        String[] Dataset_diagnosa = new String[]{
                "kanker1", "kanker2", "kanker3", "kanker4"
        };
        String[] Dataset_desk = new String[]{
                "kanker1: kurang makan", "kanker2: kurang olahraga", "kanker3: kurang kasih sayang", "kanker4: jomblo"
        };

        for (int i = 0; i < Dataset_desk.length; i++) {
            ModelObject obj = new ModelObject(Dataset_diagnosa[i], Dataset_desk[i]);
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
}
