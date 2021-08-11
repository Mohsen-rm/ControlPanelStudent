package com.ajiashi.controlpanelstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajiashi.controlpanelstudent.activityshow.ShowNotifications;
import com.ajiashi.controlpanelstudent.post.POSTNEWS;
import com.ajiashi.controlpanelstudent.post.POSTNotifications;
import com.ajiashi.controlpanelstudent.post.PostResults;

public class MainActivity extends AppCompatActivity {

    Button goPost , goNotf , goShowNews , goShowNotF ,goAddResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Displaying toolbar icon
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        goPost = (Button)findViewById(R.id.go_post);
        goNotf = (Button)findViewById(R.id.go_notf);
        goShowNews = (Button)findViewById(R.id.go_show_news);
        goShowNotF = (Button)findViewById(R.id.go_show_netf);
        goAddResult = (Button)findViewById(R.id.go_post_results);

        goPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, POSTNEWS.class);
                startActivity(intent);
            }
        });

        goNotf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, POSTNotifications.class);
                startActivity(intent);
            }
        });

        goShowNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,userlist.class);
                startActivity(intent);
            }
        });

        goShowNotF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowNotifications.class);
                startActivity(intent);
            }
        });

        goAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostResults.class);
                startActivity(intent);
            }
        });

    }

}