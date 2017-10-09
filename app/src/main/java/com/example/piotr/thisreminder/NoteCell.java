package com.example.piotr.thisreminder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by piotr on 09.10.2017.
 */
public class NoteCell extends LinearLayout {
    private TextView note_title, note_description , note_reminder;

    public NoteCell(Context context, String title, String description, String reminder){
        super(context);
        View.inflate(context, R.layout.note_cell, this);

        note_title = (TextView)findViewById(R.id.note_title);
        note_description = (TextView)findViewById(R.id.note_description);
        //note_reminder = ...                                                                       //TODO

        note_title.setText(title);
        note_description.setText(description);
        //note_reminder.setText(reminder);
    }
}
