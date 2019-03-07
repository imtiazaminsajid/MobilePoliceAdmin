package com.example.ambit.mobilepoliceadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.ambit.mobilepoliceadmin.Adaptar.Details;
import com.example.ambit.mobilepoliceadmin.Model.AllDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Details details;
    AllDetails allDetails;
    List<AllDetails> allDetailsList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allDetailsList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Reports");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AllDetails allDetails = dataSnapshot1.getValue(AllDetails.class);
                    Collections.reverse(allDetailsList);
                    allDetailsList.add(allDetails);
                    Collections.reverse(allDetailsList);

                }

                details = new Details(getApplicationContext(), allDetailsList);
                recyclerView.setAdapter(details);

                details.setOnItemClickListener(new Details.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String crimeType = allDetailsList.get(position).getCrimeType();
                        String crimeDateAndTime = allDetailsList.get(position).getTimeAndDate();
                        String withnessPhone = allDetailsList.get(position).getPhoneNumber();
                        String crimeLocation = allDetailsList.get(position).getLocationName();
                        String crimeDetails = allDetailsList.get(position).getComplain();
                        String crimeImage =  allDetailsList.get(position).getImageUrl();
                        double crimeLat =  allDetailsList.get(position).getLat();
                        double crimeLon =  allDetailsList.get(position).getLon();

                        Toast.makeText(MainActivity.this, "How are U"+crimeType, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, CrimeDetails.class);

                        intent.putExtra("CrimeType", crimeType);
                        intent.putExtra("CrimeDateAndTime", crimeDateAndTime);
                        intent.putExtra("CrimeLocation", crimeLocation);
                        intent.putExtra("WithnessPhone", withnessPhone);
                        intent.putExtra("CrimeDetails", crimeDetails);
                        intent.putExtra("CrimeImage", crimeImage);
                        intent.putExtra("CrimeLat", crimeLat);
                        intent.putExtra("CrimeLon", crimeLon);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
