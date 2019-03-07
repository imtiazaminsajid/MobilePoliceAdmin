package com.example.ambit.mobilepoliceadmin;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CrimeDetails extends AppCompatActivity {

    double Lat, Lon;

    TextView detailsCrimeType, DetailsCrimeDateAndTime, DetailsCrimeLocation, detailsWithnessPhone, detailsCrimeDetails;
    CircleImageView crimePicture;

    private int CALL_PERMISSION_CODE = 2;

    String PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_details);

        detailsCrimeType = findViewById(R.id.DetailCrimeType);
        DetailsCrimeDateAndTime = findViewById(R.id.DetailCrimeDateANdTime);
        DetailsCrimeLocation = findViewById(R.id.DetailCrimeLocationName);
        detailsWithnessPhone = findViewById(R.id.DetailWithnessPhone);
        detailsCrimeDetails = findViewById(R.id.DetailCrime);

        crimePicture = findViewById(R.id.DetailsCrimePicture);

        Bundle bundle = getIntent().getExtras();

        PhoneNumber = bundle.getString("WithnessPhone");

        detailsCrimeType.setText(bundle.getString("CrimeType"));
        DetailsCrimeDateAndTime.setText(bundle.getString("CrimeDateAndTime"));
        DetailsCrimeLocation.setText(bundle.getString("CrimeLocation"));
        detailsWithnessPhone.setText(bundle.getString("WithnessPhone"));
        detailsCrimeDetails.setText(bundle.getString("CrimeDetails"));

        Lat = bundle.getDouble("CrimeLat");
        Lon = bundle.getDouble("CrimeLon");

        String imageUrl = bundle.getString("CrimeImage");

        Picasso.with(getApplicationContext()).
                load(imageUrl).
                placeholder(R.mipmap.ic_launcher_round).fit().
                centerCrop().into(crimePicture);



    }

    public void map(View view) {

        Intent intent = new Intent(CrimeDetails.this, MapsActivity.class);
        intent.putExtra("Lat", Lat);
        intent.putExtra("Lon", Lon);
        startActivity(intent);
    }

    public void phoneCall(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            String number = PhoneNumber;
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        } else {
            requestStoragePermission();
        }
    }
    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){

            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This Permission is Needed For Call")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(CrimeDetails.this, new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
        }
    }
}
