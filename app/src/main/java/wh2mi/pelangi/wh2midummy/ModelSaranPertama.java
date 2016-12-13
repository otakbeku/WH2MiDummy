package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/12/2016.
 */

public class ModelSaranPertama {
    private String idSaranPenangananPertama, FK_idPenyakit, FK_idKondisiLingkungan, Text_SaranPenangananPertama;

    public ModelSaranPertama() {
    }

    public ModelSaranPertama(String idSaranPenangananPertama, String FK_idPenyakit, String FK_idKondisiLingkungan, String text_SaranPenangananPertama) {
        this.idSaranPenangananPertama = idSaranPenangananPertama;
        this.FK_idPenyakit = FK_idPenyakit;
        this.FK_idKondisiLingkungan = FK_idKondisiLingkungan;
        Text_SaranPenangananPertama = text_SaranPenangananPertama;
    }

    public String getIdSaranPenangananPertama() {
        return idSaranPenangananPertama;
    }

    public void setIdSaranPenangananPertama(String idSaranPenangananPertama) {
        this.idSaranPenangananPertama = idSaranPenangananPertama;
    }

    public String getFK_idPenyakit() {
        return FK_idPenyakit;
    }

    public void setFK_idPenyakit(String FK_idPenyakit) {
        this.FK_idPenyakit = FK_idPenyakit;
    }

    public String getFK_idKondisiLingkungan() {
        return FK_idKondisiLingkungan;
    }

    public void setFK_idKondisiLingkungan(String FK_idKondisiLingkungan) {
        this.FK_idKondisiLingkungan = FK_idKondisiLingkungan;
    }

    public String getText_SaranPenangananPertama() {
        return Text_SaranPenangananPertama;
    }

    public void setText_SaranPenangananPertama(String text_SaranPenangananPertama) {
        Text_SaranPenangananPertama = text_SaranPenangananPertama;
    }
}
