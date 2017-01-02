package com.example.akil.recyclerswipable;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> listItem = new ArrayList<>();
    Adapter adapter;
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new Adapter(MainActivity.this, listItem);
        addItem();

        mRecycler = (RecyclerView) findViewById(R.id.mRecycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecycler.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simplecallbac = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper.Callback callback =
                new itemTouchHelper(adapter);

        ItemTouchHelper touchHelper= new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecycler);

        /*ItemTouchHelper helper = new ItemTouchHelper(simplecallbac);
        helper.attachToRecyclerView(mRecycler);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                final EditText edit = new EditText(MainActivity.this);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert
                        .setTitle("Add Data")
                        .setView(edit)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.addItem(edit.getText().toString());
                            }
                        })
                        .create().show();
                mRecycler.scrollToPosition(adapter.getItemCount());
                break;
        }
        return true;
    }

    public void addItem(){
        listItem.add("ini item ke 1");
        listItem.add("ini item ke 2");
        listItem.add("ini item ke 3");
        listItem.add("ini item ke 4");
        listItem.add("ini item ke 5");
        listItem.add("ini item ke 6");
        listItem.add("ini item ke 7");
        listItem.add("ini item ke 8");
        listItem.add("ini item ke 9");
        listItem.add("ini item ke 10");
    }
}
