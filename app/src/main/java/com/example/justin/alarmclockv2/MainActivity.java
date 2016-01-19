package com.example.justin.alarmclockv2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Gravity;
import android.widget.Button;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
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
                startActivity(new Intent(MainActivity.this, myPreviousRecords.class));
            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView messageView = (TextView)findViewById(R.id.messageTextView);
                TextClock clock = (TextClock)findViewById(R.id.clock);

                String message = messageView.getText().toString();
                String currentTime = clock.getText().toString();

                add(message,currentTime);

                messageView.setText("");

            }
        });

        helper = new MySQLiteOpenHelper(MainActivity.this,"Messages.db",null,1);

    }

    public void add(String message,String time){
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("content",message);
        values.put("time", time);
        db.insert("messageTable",null,values);
    }


}
