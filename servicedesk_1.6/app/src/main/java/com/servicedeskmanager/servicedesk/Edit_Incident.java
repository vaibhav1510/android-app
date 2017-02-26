package com.servicedeskmanager.servicedesk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.model.EditIncidentModel;
import com.servicedeskmanager.servicedesk.model.LoginRequest;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulPost;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDUtil;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class  Edit_Incident extends AppCompatActivity implements RestFulResult {

    TextView incidentId, reqName, reqPhone, reqEmail;
    EditText description, symptom,resolution ;
    EditText rootCause;
    EditText troubleReason;
    EditText recoveryAction;

    MaterialBetterSpinner state;

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

        String inciId;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                inciId= null;
            } else {
                inciId= extras.getString("Incident_id");
            }
        } else {
            inciId= (String) savedInstanceState.getSerializable("Incident_Id");
        }

        if(inciId==null){
            Log.i("Inc_id", inciId);
            return;
        }

        incidentId=(TextView)findViewById(R.id.incident_id);
        incidentId.setText(inciId);
        prePopulateData(inciId);

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



        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditIncidentModel model = new EditIncidentModel();
                model.setCategory_name("cat name");
                model.setCategory_name("cat name");
                model.setCategory_name("cat name");
                model.setCategory_name("cat name");
                model.setCategory_name("cat name");

                sendData(model);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void prePopulateData(String inciId){
        String domain = SDUtil.getSession(this,"domain");
        Map<String, Object> fetchIncident = new HashMap<String, Object>();
        fetchIncident.put("baseurl", RestfulEndpoints.Base.get());
//        fetchIncident.put("baseurl", domain);
        fetchIncident.put("path", RestfulEndpoints.Incident.get()+ inciId);

        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "Incident", true);
        restFulGet.execute(fetchIncident);
    }


    private void sendData(EditIncidentModel model ){
        String domain = SDUtil.getSession(this,"domain");
        Map<String, Object> fetchIncident = new HashMap<String, Object>();
        fetchIncident.put("baseurl", RestfulEndpoints.Base.get());
//        fetchIncident.put("baseurl", domain);
        fetchIncident.put("path", RestfulEndpoints.Incident.get()+ model.getId());
        fetchIncident.put("data", model);

        RestFulPost restFulGet = new RestFulPost(this, this.getApplication(), "Please Wait", "Incident", true);
        restFulGet.execute(fetchIncident);
    }

    @Override
    public void onResfulResponse(String result, String responseFor, ResponseHandler handler) {
        Gson gson = new Gson();
        EditIncidentModel response = gson.fromJson(result, EditIncidentModel.class);

        incidentId.setText(response.getDisplay_id());
        reqName.setText(response.getFirstName());
        reqPhone.setText(response.getPhone());
        reqEmail.setText(response.getEmail());
        description.setText(response.getDescription());
        symptom.setText(response.getSymptom());
        resolution.setText(response.getResolution());
        rootCause.setText(response.getRootCause());
        troubleReason.setText(response.getTroubleReason());
        recoveryAction.setText(response.getRecoveryAction());
        state.setText(response.getState());

    }
}
