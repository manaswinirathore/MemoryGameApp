package com.example.memorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {
    private android.widget.Button StartGameAgain;
    private android.widget.Button view;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        DB = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        view = (android.widget.Button) findViewById(R.id.btnView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor res = DB.getdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(GameOverActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Name :").append(res.getString(0)).append("\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(GameOverActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        StartGameAgain = (android.widget.Button) findViewById(R.id.playAgain);
        StartGameAgain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                android.content.Intent mainIntent = new android.content.Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}