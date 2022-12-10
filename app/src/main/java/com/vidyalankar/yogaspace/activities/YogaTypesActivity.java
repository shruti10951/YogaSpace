package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.adapter.YogaAdapter;
import com.vidyalankar.yogaspace.model.YogaInstruction;

import java.util.ArrayList;

public class YogaTypesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<YogaInstruction> list;

    LinearLayout linearLayout;
    ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_types);

        recyclerView = findViewById(R.id.yoga_recycler_view);
        list = new ArrayList<>();

        linearLayout = findViewById(R.id.linear_yoga_types);
        shimmerFrameLayout = findViewById(R.id.shimmer);

        shimmerFrameLayout.startShimmer();

        YogaAdapter yogaAdapter = new YogaAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(yogaAdapter);

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Yoga Space")
                .child("Yoga")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            YogaInstruction yogaInstruction = dataSnapshot.getValue(YogaInstruction.class);
                            list.add(yogaInstruction);
                        }
                        yogaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}