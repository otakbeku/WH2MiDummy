package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/6/2016.
 */

public class ModelGejala {
    private String idGejala, FK_IdGejala, textGejala, ketGejala;

    public ModelGejala() {
    }

    public ModelGejala(String idGejala, String FK_IdGejala, String textGejala) {
        this.idGejala = idGejala;
        this.FK_IdGejala = FK_IdGejala;
        this.textGejala = textGejala;
    }

    public ModelGejala(String idGejala, String FK_IdGejala, String textGejala, String ketGejala) {

        this.idGejala = idGejala;
        this.FK_IdGejala = FK_IdGejala;
        this.textGejala = textGejala;
        this.ketGejala = ketGejala;
    }

    public String getIdGejala() {
        return idGejala;
    }

    public void setIdGejala(String idGejala) {
        this.idGejala = idGejala;
    }

    public String getFK_IdGejala() {
        return FK_IdGejala;
    }

    public void setFK_IdGejala(String FK_IdGejala) {
        this.FK_IdGejala = FK_IdGejala;
    }

    public String getTextGejala() {
        return textGejala;
    }

    public void setTextGejala(String textGejala) {
        this.textGejala = textGejala;
    }

    public String getKetGejala() {
        return ketGejala;
    }

    public void setKetGejala(String ketGejala) {
        this.ketGejala = ketGejala;
    }


}
