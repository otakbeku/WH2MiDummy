package wh2mi.pelangi.wh2midummy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InputDataDiri extends AppCompatActivity {

    private Button btnMasuk;
    private EditText inputUmur;
    private RadioGroup rg_jenisKelamin;
    private RadioButton rbLaki, rbPerempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_inputdatadiri);

        //Instansiasi Kontan
        rg_jenisKelamin = (RadioGroup) findViewById(R.id.rg_jenisKelamin);
        rbLaki = (RadioButton) findViewById(R.id.rbLaki);
        rbPerempuan = (RadioButton) findViewById(R.id.rbPerempuan);

        btnMasuk = (Button) findViewById(R.id.btnMasuk);

        inputUmur = (EditText) findViewById(R.id.InputUmur);

        rg_jenisKelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbLaki.isChecked()) {
                    //sekarang toast dulu
//                    Toast.makeText(getApplicationContext(), "Anda memilih " + rbLaki.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                if (rbPerempuan.isChecked()) {
//                    sekarang toast dulu
//                    Toast.makeText(getApplicationContext(), "Anda memilih " + rbPerempuan.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputUmur.getText().toString().matches("") && (rbLaki.isChecked() || rbPerempuan.isChecked())) {
                    if (Integer.parseInt(inputUmur.getText().toString()) > 15) {
                        Intent i_InputGejala = new Intent(InputDataDiri.this, InputGejala.class);
                        startActivity(i_InputGejala);
                    } else {
                        Toast.makeText(getApplicationContext(), "Anda Terlalu muda", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Anda mengisi dahulu sebelum bisa melanjutkan", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
