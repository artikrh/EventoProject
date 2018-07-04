package com.ick.eventoproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtLocation;
    TextView txtEmail;
    TextView txtGeo;
    Geocoder mGeocoder;
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPref sharedpref = new SharedPref(this);

        if(sharedpref.loadNightModeState()){
            this.setTheme(R.style.darktheme);
        }
        else{
            this.setTheme(R.style.AppTheme);
        }

        txtName = findViewById(R.id.tv_name);
        txtLocation = findViewById(R.id.txtLocation);
        txtEmail = findViewById(R.id.tv_email);
        txtGeo = findViewById(R.id.txtGeo);

        //Retreive data from databse to change User name and Email


        DatabaseReference info= FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference info1=info.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        info1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String emri= dataSnapshot.child("emri").getValue(String.class);
                String mbiemri= dataSnapshot.child("mbiemri").getValue(String.class);
                String email=dataSnapshot.child("email").getValue(String.class);
                txtName.setText(emri+" "+mbiemri);
                txtEmail.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        //locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //getLocation();

        GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(ProfileActivity.this);
        if (mGpsLocationTracker.canGetLocation())
        {
            Double latitude = mGpsLocationTracker.getLatitude();
            Double longitude = mGpsLocationTracker.getLongitude();
            txtGeo.setText(String.valueOf(latitude)+"/"+String.valueOf(longitude));

            try{
                if(getCityNameByCoordinates(latitude,longitude)!=null){
                    txtLocation.setText(getCityNameByCoordinates(latitude,longitude));
                } else {
                    txtLocation.setText("Prishtina");
                }
            } catch (Exception e){

            }
        }
        else
        {
            mGpsLocationTracker.showSettingsAlert();
            txtGeo.setText("");
        }

    }

    void getLocation(){

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                try{
                    String city = getCityNameByCoordinates(latti,longi);
                    txtLocation.setText("City: "+city);
                } catch (Exception e) {
                    txtLocation.setText("Location not found");
                }
                txtGeo.setText(String.valueOf(latti)+"/"+String.valueOf(longi));
            } else {
                txtGeo.setText("Location null");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
        mGeocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }
}