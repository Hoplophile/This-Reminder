package com.example.piotr.thisreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        SharedPreferences sharedPreferences = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {
                return null;
            }

            @Nullable
            @Override
            public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };


        LinearLayout mainScrlLin = (LinearLayout)findViewById(R.id.mainScrlLin);
        for (int i=0;i<titles.length;i++){
            final NoteCell noteCell = new NoteCell(MainActivity.this,
                    titles[titles.length-i-1], descriptions[titles.length-i-1], reminders[titles.length-i-1]);
            noteCell.setId(titles.length-i-1);
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

        Button add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("title", "");
                intent.putExtra("description", "");
                intent.putExtra("reminder", "");
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
}
