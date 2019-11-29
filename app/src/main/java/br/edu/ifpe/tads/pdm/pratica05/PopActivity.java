package br.edu.ifpe.tads.pdm.pratica05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Date;

public class PopActivity extends Activity {


    Button btn_close;
    Button btn_add;
    String donoOutdoor;
    String tituloOutdoor;
    EditText MdonoOutdoor;
    EditText MtituloOutdoor;
    String lat;
    String Lng;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);


        Intent i = getIntent();
        lat =  i.getStringExtra("Lat");
        Lng =  i.getStringExtra("Lng");

        latitude = Double.parseDouble(lat);
        longitude = Double.parseDouble(Lng);


        btn_close = findViewById(R.id.btn_close);
        btn_add = findViewById(R.id.btn_add);
        MdonoOutdoor = findViewById(R.id.dono_outdoor);
        MtituloOutdoor = findViewById(R.id.titulo_outdoor);


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final double lat = latitude;
                final double lng = longitude;
                donoOutdoor = MdonoOutdoor.getText().toString();
                tituloOutdoor = MtituloOutdoor.getText().toString();


                Outdoor outdoor = new Outdoor(lat,lng,donoOutdoor,tituloOutdoor);
                DatabaseReference drOutdoors = FirebaseDatabase.getInstance().getReference("outdoor");
                drOutdoors.push().setValue(outdoor);
                finish();

            }
        });

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
}
