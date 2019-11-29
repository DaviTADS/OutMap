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

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PopInfoActivity extends Activity {

    Button btn_close;
    public TextView MtituloOutdoor;
    public TextView MdonoOutdoor;

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;

    String lat;
    String Lng;
    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_info);

        inicializarComponentes();
        //inicializarFirebase();


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent in = getIntent();
         lat =  in.getStringExtra("Lat");
         Lng =  in.getStringExtra("Lng");

          latitude = Double.parseDouble(lat);
          longitude = Double.parseDouble(Lng);

        Query drOutdoors = FirebaseDatabase.getInstance().getReference("outdoor").equalTo(latitude);


         drOutdoors.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                         Outdoor outdoor =dataSnapshot.getValue(Outdoor.class);

                         String info = outdoor.getInfo();
                         MtituloOutdoor.setText(info);

                         String dono = outdoor.getOwner();
                         MdonoOutdoor.setText(dono);
                         //Here you will get the string values what you want to fetch
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

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
        params.y = 0;

        getWindow().setAttributes(params);
    }

//    private void inicializarFirebase() {
//
//        FirebaseApp.initializeApp(PopInfoActivity.this);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//    }

    private void inicializarComponentes() {

        MtituloOutdoor = findViewById(R.id.titulo_outdoor_show);
        MdonoOutdoor = findViewById(R.id.dono_outdoor_show);
        btn_close = findViewById(R.id.btn_close);

    }


}
