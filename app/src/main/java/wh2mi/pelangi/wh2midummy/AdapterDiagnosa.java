package wh2mi.pelangi.wh2midummy;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */
public class AdapterDiagnosa extends RecyclerView.Adapter<AdapterDiagnosa.ViewHolder> {

    private static String LOG_TAG = "AdapterDiagnosa";
    private ArrayList<ModelObject> dataset;
    private static MyClickListener myClickListener;
    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView namaPenyakit;
        TextView deksripsiPenyakit;

        public ViewHolder(View v) {
            super(v);
            namaPenyakit = (TextView) v.findViewById(R.id.teksdiagnosa);
            deksripsiPenyakit = (TextView) v.findViewById(R.id.teksdek);
            Log.i(LOG_TAG, "Menambah listener");
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AdapterDiagnosa(ArrayList<ModelObject> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewdiagnosa, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //bisa untuk ngubah background
        holder.namaPenyakit.setText(dataset.get(position).getNamaPenyakit());
        holder.deksripsiPenyakit.setText(dataset.get(position).getDeksripsiPenyakit());

    }

    public void addItem(ModelObject dataObj, int index) {
        dataset.add(index, dataObj);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
