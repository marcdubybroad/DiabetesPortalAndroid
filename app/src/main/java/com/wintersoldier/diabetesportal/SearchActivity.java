package com.wintersoldier.diabetesportal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wintersoldier.diabetesportal.service.JsonParserService;

import java.util.List;


public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // set the spinner
        this.displaySpinner();
    }

    /**
     * method to load up and display the phenotype name spinner
     * 
     */
    protected void displaySpinner() {
        // populate the spinner
        Spinner spinner = (Spinner) this.findViewById(R.id.search_phenotype_spinner);

        // get the list of phenotypes
        JsonParserService service = JsonParserService.getService();
        service.setContext(this);
        List<String>  phenotypeList = service.getAllDistinctPhenotypeNames();

        // log
        Log.v(this.getClass().getName(), "got phenotype list of size: " + phenotypeList.size());

        // create the array
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phenotypeList);

        // set the adapter on the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
