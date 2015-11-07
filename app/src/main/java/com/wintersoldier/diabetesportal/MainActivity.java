package com.wintersoldier.diabetesportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * main class to drive the gene search application
 *
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add the button listeners
        View searchButton = this.findViewById(R.id.button_main_search);
        searchButton.setOnClickListener(this);

        searchButton = this.findViewById(R.id.button_load_metadata);
        searchButton.setOnClickListener(this);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * listen to button clicks
     *
     * @param view
     */
    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.button_main_search:
                Log.i(this.getClass().getName(), "loading search activity");
                intent = new Intent(this, SearchActivity.class);
                this.startActivity(intent);
                break;
            case R.id.button_load_metadata:
                Log.i(this.getClass().getName(), "loading metadata load activity");
                intent = new Intent(this, MetadataLoadActivity.class);
                this.startActivity(intent);

                break;
        }
    }
}
