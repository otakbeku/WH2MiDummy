package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/9/2016.
 */

public class ModelPenyakit {

    private String idPenyakit, PenyebabPenyakit, namaPenyakit, deskripsiPenyakit;
    private boolean selected = false;

    ///////////////////////////////////////////////////////////////////////////////////////////
    //Constructor
    ///////////////////////////////////////////////////////////////////////////////////////////


    public ModelPenyakit(String idPenyakit, String penyebabPenyakit, String namaPenyakit, String deskripsiPenyakit) {
        this.idPenyakit = idPenyakit;
        PenyebabPenyakit = penyebabPenyakit;
        this.namaPenyakit = namaPenyakit;
        this.deskripsiPenyakit = deskripsiPenyakit;
    }

    public ModelPenyakit(String idPenyakit, String penyebabPenyakit, String namaPenyakit, String deskripsiPenyakit, boolean selected) {
        this.idPenyakit = idPenyakit;
        PenyebabPenyakit = penyebabPenyakit;
        this.namaPenyakit = namaPenyakit;
        this.deskripsiPenyakit = deskripsiPenyakit;
        this.selected = selected;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Getter & Setter
    ///////////////////////////////////////////////////////////////////////////////////////////

    public String getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(String idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getPenyebabPenyakit() {
        return PenyebabPenyakit;
    }

    public void setPenyebabPenyakit(String penyebabPenyakit) {
        PenyebabPenyakit = penyebabPenyakit;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getDeskripsiPenyakit() {
        return deskripsiPenyakit;
    }

    public void setDeskripsiPenyakit(String deskripsiPenyakit) {
        this.deskripsiPenyakit = deskripsiPenyakit;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
