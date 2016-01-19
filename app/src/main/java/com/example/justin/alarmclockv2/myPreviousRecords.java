package com.example.justin.alarmclockv2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;


public class myPreviousRecords extends AppCompatActivity {

    TableLayout table_layout;
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_previous_records);
        table_layout = (TableLayout)findViewById(R.id.tableLayout);
        helper = new MySQLiteOpenHelper(myPreviousRecords.this,"Messages.db",null,1);
        buildTable();

        Button rmBtn = (Button)findViewById(R.id.removeBtn);
        rmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllEntries();
                buildTable();
            }
        });
    }

    private void buildTable() {

        db = helper.getReadableDatabase();
        Cursor c = helper.readEntry(db);

        int rows = c.getCount();
        int columns = c.getColumnCount();

        c.moveToFirst();

        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < columns; j++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setTextColor(Color.WHITE);
                tv.setText(c.getString(j));
                row.addView(tv);
            }
            c.moveToNext();
            table_layout.addView(row);
        }
        helper.close();
    }

    public void deleteAllEntries(){
        db = helper.getWritableDatabase();
        db.delete("messageTable",null,null);
    }
}
