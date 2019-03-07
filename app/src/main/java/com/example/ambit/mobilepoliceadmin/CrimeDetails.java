package com.example.ambit.mobilepoliceadmin;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_details);

        this.setTitle("Crime Details");

        detailsCrimeType = findViewById(R.id.DetailCrimeType);
        DetailsCrimeDateAndTime = findViewById(R.id.DetailCrimeDateANdTime);
        DetailsCrimeLocation = findViewById(R.id.DetailCrimeLocationName);
        detailsWithnessPhone = findViewById(R.id.DetailWithnessPhone);
        detailsCrimeDetails = findViewById(R.id.DetailCrime);

        crimePicture = findViewById(R.id.DetailsCrimePicture);

        Bundle bundle = getIntent().getExtras();

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
}
