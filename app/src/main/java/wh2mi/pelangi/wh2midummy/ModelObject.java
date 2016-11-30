package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class ModelObject {
    private String namaPenyakit;
    private String deksripsiPenyakit;

    public ModelObject(String namaPenyakit, String deksripsiPenyakit) {
        this.namaPenyakit = namaPenyakit;
        this.deksripsiPenyakit = deksripsiPenyakit;
    }


    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getDeksripsiPenyakit() {
        return deksripsiPenyakit;
    }

    public void setDeksripsiPenyakit(String deksripsiPenyakit) {
        this.deksripsiPenyakit = deksripsiPenyakit;
    }
}
