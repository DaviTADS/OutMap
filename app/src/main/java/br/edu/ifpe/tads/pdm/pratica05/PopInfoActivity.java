package br.edu.ifpe.tads.pdm.pratica05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PopInfoActivity extends Activity {

    Button btn_close;
    public TextView MtituloOutdoor;
    public TextView MdonoOutdoor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_info);

        inicializarComponentes();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent in = getIntent();
         String info =  in.getStringExtra("info");
         String dono =  in.getStringExtra("dono");

          MtituloOutdoor.setText(info);
          MdonoOutdoor.setText(dono);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 100;

        getWindow().setAttributes(params);
    }

    private void inicializarComponentes() {

        MtituloOutdoor = findViewById(R.id.titulo_outdoor_show);
        MdonoOutdoor = findViewById(R.id.dono_outdoor_show);
        btn_close = findViewById(R.id.btn_close);

    }


}
