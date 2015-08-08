package com.wintersoldier.diabetesportal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wintersoldier.diabetesportal.service.JsonParserService;

import java.util.List;


public class SearchActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    // instance variables
    JsonParserService jsonService;


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
        List<String>  phenotypeList = this.getJsonService().getAllDistinctPhenotypeNames();

        // log
        Log.v(this.getClass().getName(), "got phenotype list of size: " + phenotypeList.size());

        // create the array
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phenotypeList);

        // set the adapter on the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // add this activity as listener
        spinner.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
            case R.id.search_phenotype_spinner:
                this.actOnSelectedPhenotype(position);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * retrieves the selected phenotype and populates the sampe group list based on the selection
     *
     * @param position
     */
    protected void actOnSelectedPhenotype(int position) {
        // local variables
        String selectedPhenotype;

        // get the selected phenotype
        selectedPhenotype = this.getJsonService().getAllDistinctPhenotypeNames().get(position);
        Log.i(this.getClass().getName(), "Got selected phenotype: " + selectedPhenotype);

        // retrieve the sample groups which contain this phenotype

        // populate the spinner of sampel groups

    }

    /**
     * internal way to get acces to the json service
     *
     * @return
     */
    protected JsonParserService getJsonService() {
        if (this.jsonService == null) {
            this.jsonService = JsonParserService.getService();
            this.jsonService.setContext(this);
        }

        return this.jsonService;
    }
}