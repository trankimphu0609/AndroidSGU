package com.trankimphu.lab04_uiclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ToDoManagerActivity extends ListActivity {

    ToDoListAdapter adapter;

    private static final int ADD_TODO_ITEM_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new TodoListAdapter for this ListActivity's ListView
        adapter = new ToDoListAdapter(getApplicationContext());

        // Put divider between ToDoItems and FooterView
        getListView().setFooterDividersEnabled(true);

        // Inflate footerView for footer_view.xml file
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View footerView = inflater.inflate(R.layout.footer_view, null);

        // Add footerView to ListView
        getListView().addFooterView(footerView);

        // Attach Listener to FooterView
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addToDoActivity = new Intent(getApplicationContext(), AddToDoActivity.class);
                startActivityForResult(addToDoActivity, ADD_TODO_ITEM_REQUEST);
            }
        });

        // Attach the adapter to this ListActivity's ListView
        getListView().setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Lab04_UIClasses","Entered onActivityResult()");

        // Check result code and request code
        // if user submitted a new ToDoItem
        if (requestCode == ADD_TODO_ITEM_REQUEST && resultCode == RESULT_OK) {

            // Create a new ToDoItem from the data Intent
            ToDoItem item = new ToDoItem(data);
            // and then add it to the adapter
            adapter.add(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}