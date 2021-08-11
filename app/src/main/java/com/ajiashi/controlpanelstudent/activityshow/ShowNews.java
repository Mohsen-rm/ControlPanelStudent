package com.ajiashi.controlpanelstudent.activityshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajiashi.controlpanelstudent.R;
import com.ajiashi.controlpanelstudent.userlist;

public class ShowNews extends AppCompatActivity {

    Button recylerviewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        recylerviewbtn = findViewById(R.id.recyclerviewbtn);
        recylerviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ShowNews.this, userlist.class);
                startActivity(i);
                finish();


            }
        });

    }
}