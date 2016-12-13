package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/12/2016.
 */

public class ModelSaranPencegahan {
    private String idSaranPencegahan;
    private String FK_idPenyakit;
    private String TextPencegahan;

    public ModelSaranPencegahan() {
    }

    public ModelSaranPencegahan(String idSaranPencegahan, String FK_IdPenyakit, String textPencegahan) {
        this.idSaranPencegahan = idSaranPencegahan;
        this.FK_idPenyakit = FK_IdPenyakit;
        TextPencegahan = textPencegahan;
    }

    public String getTextPencegahan() {
        return TextPencegahan;
    }

    public void setTextPencegahan(String textPencegahan) {
        TextPencegahan = textPencegahan;
    }

    public String getIdSaranPencegahan() {
        return idSaranPencegahan;
    }

    public void setIdSaranPencegahan(String idSaranPencegahan) {
        this.idSaranPencegahan = idSaranPencegahan;
    }

    public String getFK_idPenyakit() {
        return FK_idPenyakit;
    }

    public void setFK_idPenyakit(String FK_IdPenyakit) {
        this.FK_idPenyakit = FK_IdPenyakit;
    }


}
