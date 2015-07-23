package com.example.ogawa.mymemoapp;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class FormActivity extends AppCompatActivity {

    private long memoId;

    private EditText titleText;
    private EditText bodyText;
    private TextView updatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        titleText = (EditText) findViewById(R.id.titleText);
        bodyText = (EditText) findViewById(R.id.bodyText);
        updatedText = (TextView) findViewById(R.id.updatedText);

        Intent intent = getIntent();
        memoId = intent.getLongExtra(MainActivity.EXTRA_MYID, 0L);

        if(memoId == 0) {
            // new memo
        } else {
            // show memo
            Uri uri = ContentUris.withAppendedId(
                    MemoContentProvider.CONTENT_URI,
                    memoId
            );
            String[] projection = {
                    MemoContract.Memos.COL_TITLE,
                    MemoContract.Memos.COL_BODY,
                    MemoContract.Memos.COL_UPDATED
            };
            Cursor c = getContentResolver().query(
                    uri,
                    projection,
                    MemoContract.Memos._ID + " =?",
                    new String[] { Long.toString(memoId) },
                    null
            );
            c.moveToFirst();
            titleText.setText(
                    c.getString(c.getColumnIndex(MemoContract.Memos.COL_TITLE))
            );
            bodyText.setText(
                    c.getString(c.getColumnIndex(MemoContract.Memos.COL_BODY))
            );
            updatedText.setText(
                    "Updated: " +
                    c.getString(c.getColumnIndex(MemoContract.Memos.COL_UPDATED))
            );
            c.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
