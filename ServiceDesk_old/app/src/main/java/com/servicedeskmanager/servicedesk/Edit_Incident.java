package com.servicedeskmanager.servicedesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.model.EditIncidentModel;
import com.servicedeskmanager.servicedesk.model.EditResponseData;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;
import com.servicedeskmanager.servicedesk.model.IncidentListResponseData;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDListPaginationResponse;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  Edit_Incident extends AppCompatActivity
        implements RestFulResult {

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

    EditIncidentModel editinci;

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

        getData();

        incidentId.setText(editinci.getDisplay_id());
        reqName.setText(editinci.getFirstName());
        reqPhone.setText(editinci.getPhone());
        reqEmail.setText(editinci.getEmail());

       // stateSpinner.setText(editinci.getState());



        description.setText(editinci.getDescription());
        symptom.setText(editinci.getSymptom());
        resolution.setText(editinci.getResolution());
        rootCause.setText(editinci.getRootCause());
        troubleReason.setText(editinci.getTroubleReason());
        recoveryAction.setText(editinci.getRecoveryAction());

    }

    public void getData() {
        Map<String, Object> editMap=new HashMap<String,Object>();
        Map<String, String> queryParam=new HashMap<String,String>();

        //queryParam.put("page", "1");
        //queryParam.put("items_per_page", "25");
        //queryParam.put("sort", "-creation_time");
        editMap.put("baseurl", RestfulEndpoints.Base.get());
        editMap.put("path", RestfulEndpoints.AllIncident.get());
        editMap.put("id", RestfulEndpoints.SingleIncident.get());
        editMap.put("queryparam", queryParam);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "SingleIncident", true);
        restFulGet.execute(editMap);
    }
    @Override
    public void onResfulResponse(String result, String responseFor) {
        Gson gson = new Gson();
        if (responseFor.equals("SingleIncident")) {

            SDListPaginationResponse response = gson.fromJson(result, SDListPaginationResponse.class);
            EditResponseData edit_resp = gson.fromJson(gson.toJson(response.getResults()),
                    EditResponseData.class);

            editinci = edit_resp.getEditIncident();
            Log.i("Size", edit_resp.getEditIncident().getCategory_name());
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
