
package com.ajiashi.controlpanelstudent.post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiashi.controlpanelstudent.MainActivity;
import com.ajiashi.controlpanelstudent.R;
import com.ajiashi.controlpanelstudent.mode.News;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class POSTNEWS extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail , inputSrc;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postnews);

        // Displaying toolbar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        txtDetails = (TextView) findViewById(R.id.txt_user);
        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputSrc = (EditText) findViewById(R.id.src);
        btnSave = (Button) findViewById(R.id.btn_save);

        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = inputName.getText().toString();
                String text = inputEmail.getText().toString();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String src = inputSrc.getText().toString();
                txtDetails.setText("جار النشر");
                if (title.trim().isEmpty()){
                    Toast.makeText(POSTNEWS.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
//                }else if (text.trim().isEmpty()){
//                    Toast.makeText(POSTNEWS.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
//                }else if (src.trim().isEmpty()){
//                    Toast.makeText(POSTNEWS.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
                }else {
                    createUser(title, text,currentDate,src);
                }

            }
        });

        toggleButton();
    }

    // Changing button text
    private void toggleButton() {
        btnSave.setText("Save");
    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String title, String text , String time , String src) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        News news = new News(title, text,time ,src , userId);

        db.collection("news")
                .add(news)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(POSTNEWS.this,"تم النشر",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(POSTNEWS.this,"فشل النشر",Toast.LENGTH_LONG).show();
                    }
                });

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        txtDetails.setText("تم النشر");
        // clear edit text
        inputEmail.setText("");
        inputName.setText("");
        inputSrc.setText("");
        userId = null;
        toggleButton();
    }

}