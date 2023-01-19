package com.vidyalankar.yogaspace.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vidyalankar.yogaspace.R;
import com.vidyalankar.yogaspace.activities.BreathNowActivity;
import com.vidyalankar.yogaspace.activities.LoginActivity;
import com.vidyalankar.yogaspace.activities.YogaActivity;
import com.vidyalankar.yogaspace.model.YogaInstruction;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class YogaAdapter extends RecyclerView.Adapter<YogaAdapter.ViewHolder> {

    Context context;
    ArrayList<YogaInstruction> list;

    public YogaAdapter(Context context, ArrayList<YogaInstruction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.yoga_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        YogaInstruction yogaInstruction = list.get(position);

        Glide.with(context)
                .load(yogaInstruction.getImage())
                .placeholder(R.drawable.yoga_diagonal)
                .centerCrop()
                .into(holder.img);

        holder.name.setText(yogaInstruction.getName());
        holder.time.setText("01:00 MIN");
        holder.repeat.setText("Repeat 2 time");
        holder.img.setImageResource(R.drawable.nutrition_diagonal);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, YogaActivity.class);
                intent.putExtra("name", yogaInstruction.getName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, repeat, time;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.yoga_name);
            repeat = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
            img = itemView.findViewById(R.id.yoga);

        }
    }

}
