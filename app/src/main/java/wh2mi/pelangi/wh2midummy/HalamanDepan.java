package wh2mi.pelangi.wh2midummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Kotak Hitam on 11/30/2016.
 */

public class HalamanDepan extends AppCompatActivity {
    private Button btnMulai, btnEmergencyCall;
    private ImageView gambarLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_halamandepan);

        //Instansiasi Button
        btnEmergencyCall = (Button) findViewById(R.id.btnEmergencyCall);
        btnMulai = (Button) findViewById(R.id.btnMulai);
        gambarLogo = (ImageView) findViewById(R.id.gambarLogo);

        //IMAGE
        gambarLogo.setImageResource(R.mipmap.ic_launcher);

        //ONCLICK LISTENER
        btnEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_emergencyCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 118));
                startActivity(i_emergencyCall);
            }
        });

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_inputdatadiri = new Intent(HalamanDepan.this, InputDataDiri.class);
                startActivity(i_inputdatadiri);
            }
        });
    }
}

