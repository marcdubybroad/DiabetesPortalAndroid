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

import com.wintersoldier.diabetesportal.bean.SampleGroup;
import com.wintersoldier.diabetesportal.service.JsonParserService;
import com.wintersoldier.diabetesportal.util.PortalConstants;
import com.wintersoldier.diabetesportal.util.PortalException;

import java.util.ArrayList;
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
        List<String> phenotypeList = new ArrayList<String>();

        // get the list of phenotypes
        try {
            phenotypeList = this.getJsonService().getAllDistinctPhenotypeNames();

        } catch (PortalException exception) {
            Log.e(this.getClass().getName(), "Got exception getting phenotype names: " + exception.getMessage());
        }

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
        String selectedPhenotype = null;
        List<SampleGroup> sampleGroupList = new ArrayList<SampleGroup>();
        List<String> groupNameList = new ArrayList<String>();

        // get the selected phenotype
        try {
            selectedPhenotype = this.getJsonService().getAllDistinctPhenotypeNames().get(position);

        } catch (PortalException exception) {
            Log.e(this.getClass().getName(), "Got exception getting phenotype names: " + exception.getMessage());
        }

        Log.i(this.getClass().getName(), "Got selected phenotype: " + selectedPhenotype);

        // retrieve the sample groups which contain this phenotype
        try {
            sampleGroupList = this.getJsonService().getSamplesGroupsForPhenotype(selectedPhenotype, PortalConstants.DATASET_VERSION_2_KEY);

        } catch (PortalException exception) {
            Log.e(this.getClass().getName(), "Got exception getting phenotype names: " + exception.getMessage());
        }

        // get the string list
        for (SampleGroup tempGroup: sampleGroupList) {
            groupNameList.add(tempGroup.getName());
        }

        // populate the spinner of sample groups
        Spinner sampleGroupSpinner = (Spinner)this.findViewById(R.id.search_sample_group_spinner);

        // create the array
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sampleGroupList);

        // set the adapter on the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sampleGroupSpinner.setAdapter(adapter);

        // add this activity as listener
        sampleGroupSpinner.setOnItemSelectedListener(this);

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
