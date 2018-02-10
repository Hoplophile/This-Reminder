package com.example.piotr.thisreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
import java.util.Map;
import java.util.Set;

import static android.R.id.input;
import static android.R.id.shareText;

public class MainActivity extends AppCompatActivity {

    String titles[] = {"Nie ma opierdalania się!", "Do roboty!", "Weź się za siebie!", "Idź na wykład!", "trolololo XD"};
    String descriptions[] = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo. Lorem ipsum dolor sit amet, consectetur",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. ",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget suscipit diam. Integer vitae ante et quam viverra bibendum non non justo."};
    String reminders[] = {"13:03", "12:05", "9:30", "16:29", "01:00"};
    int notesQuantity = 0, i=0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadNotes();

        LinearLayout mainScrlLin = (LinearLayout)findViewById(R.id.mainScrlLin);

        try {
            for (int i = 0; i < notesQuantity; i++) {
                final NoteCell noteCell = new NoteCell(MainActivity.this,
                        titles[titles.length - i - 1], descriptions[titles.length - i - 1], reminders[titles.length - i - 1]);
                noteCell.setId(titles.length - i - 1);
                mainScrlLin.addView(noteCell);

                noteCell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = noteCell.getId();
                        openNote(id);
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
        } catch (ArrayIndexOutOfBoundsException e) {}

        Button add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("title", "");
                intent.putExtra("description", "");
                intent.putExtra("reminder", "");
                intent.putExtra("id", notesQuantity);
                startActivity(intent);
            }
        });
    }

    public void openNote(int i){
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra("title", titles[i]);
        intent.putExtra("description", descriptions[i]);
        intent.putExtra("reminder", reminders[i]);
        intent.putExtra("id", i);
        startActivity(intent);
    }

    public void loadNotes(){
        try {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            notesQuantity = sharedPreferences.getInt("notesQuantity", 0);
            for (i = 0; i < notesQuantity-1; i++) {
                titles[i] = sharedPreferences.getString(i + "title", "");
                descriptions[i] = sharedPreferences.getString(i + "description", "");
                reminders[i] = sharedPreferences.getString(i + "reminder", "");
            }
        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Couldn't load notes", Toast.LENGTH_SHORT).show();
        }
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();

        loadNotes();
    }
}
