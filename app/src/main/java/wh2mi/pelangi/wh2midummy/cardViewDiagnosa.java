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
        setContentView(R.layout.hasildiagnosa);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerDiagnosa);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterDiagnosa(getDataset());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((AdapterDiagnosa) adapter).setOnItemClickListener(new AdapterDiagnosa.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, "menekan " + position);
            }
        });
    }

    private ArrayList<ModelObject> getDataset() {
        ArrayList result = new ArrayList<ModelObject>();
        for (int index = 0; index < 20; index++) {
            ModelObject object = new ModelObject("teks satu " + index, "teks dua " + index);
            result.add(index, object);
        }
        return result;
    }
}
