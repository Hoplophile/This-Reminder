package com.example.piotr.thisreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String titles[] = {"Nie ma opierdalania się!", "Do roboty!", "Weź się za siebie!", "Idź na wykład!", "trolololo XD"};
    String descriptions[] = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo. Lorem ipsum dolor sit amet, consectetur",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo."};
    String reminders[] = {"13:03", "12:05", "9:30", "16:29", "01:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout mainScrlLin = (LinearLayout)findViewById(R.id.mainScrlLin);
        for (int i=0;i<titles.length;i++){
            final NoteCell noteCell = new NoteCell(MainActivity.this,
                    titles[titles.length-i-1], descriptions[titles.length-i-1], reminders[titles.length-i-1]);
            noteCell.setId(i);
            mainScrlLin.addView(noteCell);

            noteCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "klik", Toast.LENGTH_SHORT).show();
                    int i = noteCell.getId();
                    openNote(i);
                }
            });
            noteCell.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(MainActivity.this, "KLIK", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        Button add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void openNote(int i){
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra("title", titles[i]);
        intent.putExtra("description", descriptions[i]);
        intent.putExtra("reminder", reminders[i]);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
