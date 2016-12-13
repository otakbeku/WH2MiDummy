package wh2mi.pelangi.wh2midummy;

/**
 * Created by Kotak Hitam on 12/6/2016.
 */

public class ModelKondisiLingkungan {
    private String idKondisiLingkungan, FK_idGejala, ketKondisiLingkungan, TextKondisiLingkungan;
    private boolean selected = false;


    ///////////////////////////////////////////////////////////////////////////////////////////
    //Constructor
    ///////////////////////////////////////////////////////////////////////////////////////////

    public ModelKondisiLingkungan(String idKondisiLingkungan, String FK_idGejala, String ketKondisiLingkungan) {
        this.idKondisiLingkungan = idKondisiLingkungan;
        this.FK_idGejala = FK_idGejala;
        this.ketKondisiLingkungan = ketKondisiLingkungan;
    }

    public ModelKondisiLingkungan(String idKondisiLingkungan, String FK_idGejala, String ketKondisiLingkungan, String textKondisiLingkungan) {
        this.TextKondisiLingkungan = textKondisiLingkungan;
        this.idKondisiLingkungan = idKondisiLingkungan;
        this.FK_idGejala = FK_idGejala;
        this.ketKondisiLingkungan = ketKondisiLingkungan;
    }


    public ModelKondisiLingkungan(String idKondisiLingkungan, String ketKondisiLingkungan, String FK_idGejala, boolean selected) {
        this.idKondisiLingkungan = idKondisiLingkungan;
        this.ketKondisiLingkungan = ketKondisiLingkungan;
        this.FK_idGejala = FK_idGejala;
        this.selected = selected;
    }

    public ModelKondisiLingkungan(String idKondisiLingkungan, String FK_idGejala, String ketKondisiLingkungan, String textKondisiLingkungan, boolean selected) {
        this.idKondisiLingkungan = idKondisiLingkungan;
        this.FK_idGejala = FK_idGejala;
        this.ketKondisiLingkungan = ketKondisiLingkungan;
        TextKondisiLingkungan = textKondisiLingkungan;
        this.selected = selected;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Getter & Setter
    ///////////////////////////////////////////////////////////////////////////////////////////


    public String getIdKondisiLingkungan() {
        return idKondisiLingkungan;
    }

    public void setIdKondisiLingkungan(String idKondisiLingkungan) {
        this.idKondisiLingkungan = idKondisiLingkungan;
    }

    public String getFK_idGejala() {
        return FK_idGejala;
    }

    public void setFK_idGejala(String FK_idGejala) {
        this.FK_idGejala = FK_idGejala;
    }

    public String getKetKondisiLingkungan() {
        return ketKondisiLingkungan;
    }

    public void setKetKondisiLingkungan(String ketKondisiLingkungan) {
        this.ketKondisiLingkungan = ketKondisiLingkungan;
    }

    public String getTextKondisiLingkungan() {
        return TextKondisiLingkungan;
    }

    public void setTextKondisiLingkungan(String textKondisiLingkungan) {
        TextKondisiLingkungan = textKondisiLingkungan;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
