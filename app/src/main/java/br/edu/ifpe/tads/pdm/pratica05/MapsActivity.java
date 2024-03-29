package br.edu.ifpe.tads.pdm.pratica05;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static int FINE_LOCATION_REQUEST = 0;
    private boolean fine_location;
    private final static int REQUEST_CODE_1 = 1;
    double latitude;
    double longitude;
    double latitudeM;
    double longitudeM;
    LatLng latLng;
    public static boolean Filter;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    ArrayList<Outdoor> out = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        requestPermission();
        //Filter=true;

        Query drOutdoors = FirebaseDatabase.getInstance().getReference("outdoor");

        final Button button = findViewById(R.id.setfilter);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (!isFilter()){
//                    setFilterTrue();
//                }else if (isFilter()){
//                    setFilterFalse();
//                }
                if (isFilter()){
                    setFilterFalse();
                }else if (!isFilter()){
                    setFilterTrue();
                }
                //setFilterTrue();
                recreate();
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
            }
        });


        drOutdoors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    Outdoor outdoor =childSnapshot.getValue(Outdoor.class);
                    out.add(outdoor);

                    //Here you will get the string values what you want to fetch
                }

//                Outdoor outdoor =dataSnapshot.getValue(Outdoor.class);
//
//                String info = outdoor.getInfo();
//                MtituloOutdoor.setText(info);
//
//                String dono = outdoor.getOwner();
//                MdonoOutdoor.setText(dono);
//                //Here you will get the string values what you want to fetch
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean isFilter() {
        return Filter;
    }

    public void setFilterTrue() {
        this.Filter = true;
    }

    public void setFilterFalse() {
        this.Filter = false;
    }


    private void requestPermission() {

        int permissioncheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        boolean hasPermission = (permissioncheck == PackageManager.PERMISSION_GRANTED);

        if (hasPermission) return;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                FINE_LOCATION_REQUEST);

    }

//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        boolean granted = (grantResults.length > 0) &&
//                (grantResults[0] == PackageManager.PERMISSION_GRANTED);
//        fine_location = (requestCode == FINE_LOCATION_REQUEST) && granted;
//
//        findViewById(R.id.button_location).setEnabled(fine_location);
//    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng recife = new LatLng(-8.05, -34.9);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(recife));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng latLng = marker.getPosition();
                latitudeM = latLng.latitude;
                longitudeM = latLng.longitude;
                Outdoor outdoor =(Outdoor) marker.getTag();

                Intent in = new Intent(getApplicationContext(),PopInfoActivity.class);

                in.putExtra("info",outdoor.getInfo());
                in.putExtra("dono",outdoor.getOwner());
                startActivity(in);
                return false;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                String lat =  Double.toString(latitude);
                String lng =  Double.toString(longitude);

                Intent i = new Intent(getApplicationContext(),PopActivity.class);

                i.putExtra("Lat",lat);
                i.putExtra("Lng",lng);
                startActivity(i);
            }
        });

        DatabaseReference drOutdoor = FirebaseDatabase.
                getInstance().getReference("outdoor");
        drOutdoor.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Outdoor outdoor = dataSnapshot.getValue(Outdoor.class);


               // dataSnapshot.getKey()
                if (Filter == Boolean.parseBoolean(null)) {
                    if (!outdoor.isRented() && Filter == false) {
                        latLng = new LatLng(outdoor.getLatitude(), outdoor.getLongitude());

                        mMap.addMarker(new MarkerOptions().
                                position(latLng).
                                title("Adicionado em " + new Date()).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).setTag(outdoor);
                    }
                    else if (outdoor.isRented() && Filter == true) {
                        latLng = new LatLng(outdoor.getLatitude(), outdoor.getLongitude());

                        mMap.addMarker(new MarkerOptions().
                                position(latLng).
                                title("Adicionado em " + new Date()).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).setTag(outdoor);
                    }
                }else{
                    latLng = new LatLng(outdoor.getLatitude(), outdoor.getLongitude());

                    mMap.addMarker(new MarkerOptions().
                            position(latLng).
                            title("Adicionado em " + new Date()).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).setTag(outdoor);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




    public void currentLocation(View view) {
        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Toast.makeText(MapsActivity.this, "Localização atual: Latitude = " +
                            location.getLatitude() + " Longitude = " +
                            location.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}



