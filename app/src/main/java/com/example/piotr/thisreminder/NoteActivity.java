package com.example.piotr.thisreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by piotr on 09.10.2017.
 */

public class NoteActivity extends AppCompatActivity {

    String title;
    String description;
    String reminder;
    int id;
    TextView title_tv;
    TextView description_tv;
    TextView reminder_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        title_tv = (TextView)findViewById(R.id.note_title);
        description_tv = (TextView)findViewById(R.id.note_description);
        reminder_tv = (TextView)findViewById(R.id.note_reminder);

        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        reminder = intent.getStringExtra("reminder");
        id = intent.getIntExtra("id", -1);
        title_tv.setText(title);
        description_tv.setText(description);
        reminder_tv.setText(reminder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(NoteActivity.this, EditNoteActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            intent.putExtra("reminder", reminder);
            intent.putExtra("id", id);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
