package com.example.ogawa.mymemoapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] from = {
                MemoContract.Memos.COL_TITLE,
                MemoContract.Memos.COL_UPDATED
        };

        int[] to = {
                android.R.id.text1,
                android.R.id.text2
        };

        adapter = new SimpleCursorAdapter(
                  this,
                  android.R.layout.simple_list_item_2,
                  null,
                  from,
                  to,
                  0
        );

        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MemoContract.Memos._ID,
                MemoContract.Memos.COL_TITLE,
                MemoContract.Memos.COL_UPDATED
        };

        return new CursorLoader(
                this,
                MemoContentProvider.CONTENT_URI,
                projection,
                null,
                null,
                MemoContract.Memos.COL_UPDATED + " DESC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
