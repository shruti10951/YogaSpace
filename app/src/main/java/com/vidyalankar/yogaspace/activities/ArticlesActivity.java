package com.vidyalankar.yogaspace.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.model.ArticlesModel;

public class ArticlesActivity extends AppCompatActivity {

    Intent intent;
    TextView wellnessTxt, firstTxt, secondTxt, thirdTxt;
    TextView firstPara1Txt, firstPara2Txt, firstPara3Txt;
    TextView secondPara1Txt, secondPara2Txt, secondPara3Txt;
    TextView thirdPara1Txt, thirdPara2Txt, thirdPara3Txt;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        wellnessTxt = findViewById(R.id.wellness_txt);
        firstTxt = findViewById(R.id.articles_first);
        secondTxt = findViewById(R.id.articles_second);
        thirdTxt = findViewById(R.id.articles_third);

        firstPara1Txt = findViewById(R.id.first_para1);
        firstPara2Txt = findViewById(R.id.first_para2);
        firstPara3Txt = findViewById(R.id.first_para3);

        secondPara1Txt = findViewById(R.id.second_para1);
        secondPara2Txt = findViewById(R.id.second_para2);
        secondPara3Txt = findViewById(R.id.second_para3);

        thirdPara1Txt = findViewById(R.id.third_para1);
        thirdPara2Txt = findViewById(R.id.third_para2);
        thirdPara3Txt = findViewById(R.id.third_para3);

        imageView = findViewById(R.id.wellness_img);

        intent = getIntent();
        String name = intent.getStringExtra("Name");
        wellnessTxt.setText(name);

        switch (name) {
            case "Sleep":
                imageView.setImageResource(R.drawable.sleep_diagonal);
                break;
            case "Mindfulness":
                imageView.setImageResource(R.drawable.mindfulness_diagonal);
                break;
            case "Stress":
                imageView.setImageResource(R.drawable.stress_diagonal);
                break;
            case "Nutrition":
                imageView.setImageResource(R.drawable.nutrition_diagonal);
                break;
        }

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Articles")
                .child(name)
                .child("first")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArticlesModel articlesModel = snapshot.getValue(ArticlesModel.class);
                        String title = articlesModel.getTitle();
                        String para1 = articlesModel.getPara1();
                        String para2 = articlesModel.getPara2();
                        String para3 = articlesModel.getPara3();

                        firstTxt.setText(title);
                        firstPara1Txt.setText(para1);
                        firstPara2Txt.setText(para2);
                        firstPara3Txt.setText(para3);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Articles")
                .child(name)
                .child("second")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArticlesModel articlesModel = snapshot.getValue(ArticlesModel.class);
                        String title = articlesModel.getTitle();
                        String para1 = articlesModel.getPara1();
                        String para2 = articlesModel.getPara2();
                        String para3 = articlesModel.getPara3();

                        secondTxt.setText(title);
                        secondPara1Txt.setText(para1);
                        secondPara2Txt.setText(para2);
                        secondPara3Txt.setText(para3);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Articles")
                .child(name)
                .child("third")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArticlesModel articlesModel = snapshot.getValue(ArticlesModel.class);
                        String title = articlesModel.getTitle();
                        String para1 = articlesModel.getPara1();
                        String para2 = articlesModel.getPara2();
                        String para3 = articlesModel.getPara3();

                        thirdTxt.setText(title);
                        thirdPara1Txt.setText(para1);
                        thirdPara2Txt.setText(para2);
                        thirdPara3Txt.setText(para3);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}