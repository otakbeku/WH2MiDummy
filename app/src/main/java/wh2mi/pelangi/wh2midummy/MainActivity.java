package wh2mi.pelangi.wh2midummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button btn_masuk, btn_EmergencyCall;
    private EditText inputUmur;
    private RadioGroup rg_jenisKelamin;
    private RadioButton rbLaki, rbPerempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //kosongin dulu

//        //RadioGroup dan RadioButton
//        rg_jenisKelamin = (RadioGroup) findViewById(R.id.rg_jenisKelamin);
//        rbLaki = (RadioButton) findViewById(R.id.rbLaki);
//        rbPerempuan = (RadioButton) findViewById(R.id.rbPerempuan);
//
//        //Untuk button
//        btn_masuk = (Button) findViewById(R.id.btn_masuk);
//        btn_EmergencyCall = (Button) findViewById(R.id.btn_EmergencyCall);
//
//        rg_jenisKelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (rbLaki.isChecked()) {
//                    //sekarang toast dulu
//                    Toast.makeText(MainActivity.this, "Anda memilih " + rbLaki.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//                if (rbPerempuan.isChecked()) {
//                    //sekarang toast dulu
//                    Toast.makeText(MainActivity.this, "Anda memilih " + rbPerempuan.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//        btn_masuk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i_InputGejala = new Intent(MainActivity.this, InputGejala.class);
//                startActivity(i_InputGejala);
//            }
//        });
    }
}
