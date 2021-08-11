package com.ajiashi.controlpanelstudent.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiashi.controlpanelstudent.R;
import com.ajiashi.controlpanelstudent.mode.News;
import com.ajiashi.controlpanelstudent.post.POSTNEWS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<News> list;
    DatabaseReference database;

    public MyAdapter(Context context, ArrayList<News> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        database = FirebaseDatabase.getInstance().getReference("news");
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        News news = list.get(position);
        holder.firstName.setText(news.getTitle());
        holder.lastName.setText(news.getTitle());
        holder.age.setText(news.getTitle());
        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, POSTNEWS.class);
                context.startActivity(intent);
            }
        });

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(news.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, lastName, age;
        ImageView editImageView ,deleteImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.show_title_news);
            lastName = itemView.findViewById(R.id.show_text_news);
            age = itemView.findViewById(R.id.show_time_news);
            editImageView = itemView.findViewById(R.id.editIcon);
            deleteImageView = itemView.findViewById(R.id.deleteIcon);


        }
    }

    private void showConfirmationDialog(final String studentId , final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete this student?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        removeAt(position,studentId);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void removeAt(int position , String studentId) {
        list.remove(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("users")
                .document(studentId);
        reference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context,"تم الحذف",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"فشل الحذف",Toast.LENGTH_LONG).show();
                }
            }
        });
        list.clear();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());

    }
}