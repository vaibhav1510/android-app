package com.servicedeskmanager.servicedesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class  Edit_Incident extends AppCompatActivity {

    TextView incidentId, reqName, reqPhone, reqEmail;
    EditText description, symptom,resolution ;
    EditText rootCause;
    EditText troubleReason;
    EditText recoveryAction;

    String[] STATELIST = {"Open", "In progress","On Hold", "Resolve"};
    String[] STATUSLIST = {"New", "Assigned"};
    String[] CATEGORYLIST = {"Hardware", "Software"};
    String[] IMPACTSERVICELIST = {"Service1", "Service2","Service3"};
    String[] CLASSIFICATION = {"Internet", "abc","xyz"};
    String[] IMPACTLIST = {"Low", "Medium","High"};
    String[] URGENCYLIST = {"Low", "Medium","High"};
    String[] PRIORITYLIST = {"Low", "Medium","High"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__incident);
        getIntent();

        incidentId=(TextView)findViewById(R.id.incident_id);
        reqName=(TextView)findViewById(R.id.req_name);
        reqPhone=(TextView)findViewById(R.id.req_phon_no);
        reqEmail=(TextView)findViewById(R.id.req_email);

        description=(EditText)findViewById(R.id.edit_description);
        symptom=(EditText)findViewById(R.id.edit_symptom);
        resolution=(EditText)findViewById(R.id.edit_resolution);
        rootCause=(EditText)findViewById(R.id.edit_rootcause);
        troubleReason=(EditText)findViewById(R.id.edit_troublereson);
        recoveryAction=(EditText)findViewById(R.id.edit_recovery);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, STATELIST);
        MaterialBetterSpinner stateSpinner = (MaterialBetterSpinner) findViewById(R.id.edit_state);
        stateSpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, STATUSLIST);
        MaterialBetterSpinner statusSpinner = (MaterialBetterSpinner) findViewById(R.id.edit_status);
        statusSpinner.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CATEGORYLIST);
        MaterialBetterSpinner categorySpinner = (MaterialBetterSpinner) findViewById(R.id.edit_category);
        categorySpinner.setAdapter(arrayAdapter3);

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, IMPACTSERVICELIST);
        MaterialBetterSpinner impactserviceSpinner = (MaterialBetterSpinner) findViewById(R.id.edit_impactservice);
        impactserviceSpinner.setAdapter(arrayAdapter4);

        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CLASSIFICATION);
        MaterialBetterSpinner classificationSpinner = (MaterialBetterSpinner) findViewById(R.id.edit_classification);
        classificationSpinner.setAdapter(arrayAdapter5);

        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, IMPACTLIST);
        MaterialBetterSpinner impactSpinner = (MaterialBetterSpinner) findViewById(R.id.edit_impact);
        impactSpinner.setAdapter(arrayAdapter6);

        ArrayAdapter<String> arrayAdapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, URGENCYLIST);
        MaterialBetterSpinner urgencySpinner = (MaterialBetterSpinner) findViewById(R.id.edit_urgency);
        urgencySpinner.setAdapter(arrayAdapter7);

        ArrayAdapter<String> arrayAdapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PRIORITYLIST);
        MaterialBetterSpinner prioritySpinner = (MaterialBetterSpinner) findViewById(R.id.edit_priority);
        prioritySpinner.setAdapter(arrayAdapter8);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
