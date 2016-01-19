package com.example.justin.alarmclockv2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newWindowbtn = (Button)findViewById(R.id.previousBtn);
        newWindowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecordsPage.class));
            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView messageView = (TextView) findViewById(R.id.messageEditText);
                TextClock clock = (TextClock) findViewById(R.id.clock);

                String message = messageView.getText().toString();
                String currentTime = clock.getText().toString();

                add(message, currentTime);

                messageView.setText("");

            }
        });

        final EditText editText = (EditText) findViewById(R.id.messageEditText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        helper = new MySQLiteOpenHelper(MainActivity.this,"Messages.db",null,1);

    }

    public void add(String message,String time){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("content",message);
        values.put("time", time);
        db.insert("messageTable", null, values);
    }
}
