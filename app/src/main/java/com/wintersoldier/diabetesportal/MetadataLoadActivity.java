package com.wintersoldier.diabetesportal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MetadataLoadActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metadata_load);

        // set listener on load metadata button
        View metadataButton = this.findViewById(R.id.button_load_metadata_action);
        metadataButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_metadata_load, menu);
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

    @Override
    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.button_load_metadata_action:
                Log.i(this.getClass().getName(), "loading metadata");
                this.loadMetadata();
                break;
        }
    }

    /**
     * method to query REST service
     *
     */
    private void loadMetadata() {
        // local variables
        final TextView statusView = (TextView)this.findViewById(R.id.metadata_load_status);
        final TextView resultsView = (TextView)this.findViewById(R.id.metadata_load_results);
        String url = "http://www.google.com";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest;

        // log
        Log.i(this.getClass().getName(), "starting REST request to: " + url);

        // start the REST request call
        stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                    public void onResponse(String response) {
                        // display
                        statusView.setText("");
                        resultsView.setText(response.substring(0, 250));

                        // log
                        Log.i(this.getClass().getName(), "Successful Volley request");
                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // display error
                    statusView.setText(error.getMessage());
                    resultsView.setText("");

                    // log
                    Log.e(this.getClass().getName(), "Got Volley error: " + error.getMessage());
                }
        });

        // add the request to the queue
        requestQueue.add(stringRequest);
    }
}
