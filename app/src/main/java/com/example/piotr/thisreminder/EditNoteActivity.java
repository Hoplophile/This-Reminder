package com.example.piotr.thisreminder;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by piotr on 10.10.2017.
 */

public class EditNoteActivity extends AppCompatActivity {

    String title;
    String description;
    String reminder;
    int id;
    EditText edit_title;
    EditText edit_description;
    EditText edit_reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        edit_title = (EditText) findViewById(R.id.edit_note_title);
        edit_description = (EditText) findViewById(R.id.edit_note_description);
        edit_reminder = (EditText)findViewById(R.id.edit_note_reminder);

        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        reminder = intent.getStringExtra("reminder");
        id = intent.getIntExtra("id", -1);
        edit_title.setText(title);
        edit_description.setText(description);
        edit_reminder.setText(reminder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.action_abort:
                finish();
                return true;
            case R.id.action_edit:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
            }


    }

    void saveNote(){
        finish();
    }
}
