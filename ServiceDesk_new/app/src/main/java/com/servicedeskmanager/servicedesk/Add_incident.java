package com.servicedeskmanager.servicedesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Add_incident extends AppCompatActivity {

    String[] CATEGORYLIST = {"Hardware", "Software"};
    String[] IMPECTLIST = {"Low", "Medium","High"};
    String[] URGENCYLIST = {"Low", "Medium","High"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_incident);
        getIntent();

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CATEGORYLIST);
        MaterialBetterSpinner categorySpinner = (MaterialBetterSpinner) findViewById(R.id.Category_spinner);
        categorySpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, IMPECTLIST);
        MaterialBetterSpinner impectSpinner = (MaterialBetterSpinner) findViewById(R.id.impect_spinner);
        impectSpinner.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, URGENCYLIST);
        MaterialBetterSpinner urgencySpinner = (MaterialBetterSpinner) findViewById(R.id.Urgency_spinner);
        urgencySpinner.setAdapter(arrayAdapter3);
    }
    @Override
    public void onBackPressed() {
                super.onBackPressed();
    }
}
