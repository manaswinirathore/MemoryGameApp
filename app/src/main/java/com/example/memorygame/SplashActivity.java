package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    EditText name;
    android.widget.Button insert;
    private android.widget.Button playgame;
    MediaPlayer ourSound;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        name = findViewById(R.id.name);
        insert = findViewById(R.id.btnInsert);
        DB = new DBHelper(this);
        insert = (android.widget.Button) findViewById(R.id.btnInsert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT);
                if(checkinsertdata==true)
                    Toast.makeText(SplashActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SplashActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        ourSound = MediaPlayer.create(this,R.raw.deli);
        ourSound.start();
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    //Thread.sleep(5000);
                    playgame = (android.widget.Button) findViewById(R.id.play);
                    playgame.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            android.content.Intent mainIntent = new android.content.Intent(SplashActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                    });
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
               /* finally
                {
                    Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                } */
            }
        };
        thread.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}