package com.servicedeskmanager.servicedesk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.model.EditIncidentAssetModel;
import com.servicedeskmanager.servicedesk.model.EditIncidentModel;
import com.servicedeskmanager.servicedesk.model.EditIncidentRequest;
import com.servicedeskmanager.servicedesk.model.IncidentIdValueOptionModel;
import com.servicedeskmanager.servicedesk.model.IncidentOptionModel;
import com.servicedeskmanager.servicedesk.model.IncidentPriorityOptionModel;
import com.servicedeskmanager.servicedesk.model.IncidentStatusModel;
import com.servicedeskmanager.servicedesk.model.StringWithTag;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulPost;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDEditIncidentResponse;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  Edit_Incident extends AppCompatActivity implements RestFulResult,
        AdapterView.OnItemSelectedListener {

    TextView incidentId, priorityName, reqName, reqPhone, reqEmail, summary,
            impactservice, classification, assetId, inventoryId, criticallity, itemType;
    EditText description, symptom,resolution ;
    EditText rootCause;
    EditText troubleReason;
    EditText recoveryAction;
    EditIncidentModel inci_obj;
    IncidentOptionModel inci_option_obj;
    IncidentIdValueOptionModel[] inci_state_obj;
    IncidentIdValueOptionModel[] inci_urgency_obj;
    IncidentIdValueOptionModel[] inci_impact_obj;
    IncidentIdValueOptionModel[] inci_Category_obj;
    IncidentStatusModel[] inci_status_obj;
    IncidentPriorityOptionModel[] inci_priority_obj;
    EditIncidentAssetModel[] inci_asset_obj;
    String incident_displayID;
    Integer user_selected_state, user_selected_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_incident);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("incidentId");
        getIncidentData(id);
    }

    /****** Function to get data from server in ArrayList *************/
    public void getIncidentData(String id)
    {
        Map<String, Object> editIncidentMap = new HashMap<String, Object>();
        String domainShared = SDUtil.getSession(this, "domain");
        String editUrl = RestfulEndpoints.EditIncident.get()+ id + "/";
        editIncidentMap.put("baseurl", domainShared);
        editIncidentMap.put("path", editUrl);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "EditIncident", true);
        restFulGet.execute(editIncidentMap);
    }

    public void setValuetoUI(EditIncidentModel obj, EditIncidentAssetModel[] inci_asset_obj){
        // getting the UI components
        incidentId=(TextView)findViewById(R.id.incident_id);
        priorityName=(TextView)findViewById(R.id.priorityName);
        reqName=(TextView)findViewById(R.id.req_name);
        reqPhone=(TextView)findViewById(R.id.req_phon_no);
        reqEmail=(TextView)findViewById(R.id.req_email);
        description=(EditText)findViewById(R.id.edit_description);
        symptom=(EditText)findViewById(R.id.edit_symptom);
        resolution=(EditText)findViewById(R.id.edit_resolution);
        rootCause=(EditText)findViewById(R.id.edit_rootcause);
        troubleReason=(EditText)findViewById(R.id.edit_troublereson);
        recoveryAction=(EditText)findViewById(R.id.edit_recovery);
        summary = (TextView)findViewById(R.id.edit_summary);
        impactservice = (TextView)findViewById(R.id.edit_impactservice);
        classification = (TextView)findViewById(R.id.edit_classification);
        // asset data
        assetId = (TextView) findViewById(R.id.edit_item_displayId);
        inventoryId = (TextView) findViewById(R.id.edit_item_inventory_id);
        criticallity = (TextView) findViewById(R.id.edit_item_criticality);
        itemType = (TextView) findViewById(R.id.edit_item_type);
        Map<String, String> requesterObj = obj.getRequester();
        String RequesterName = requesterObj.get("firstName") + " " + requesterObj.get("lastName");
        String Phone = requesterObj.get("phone");
        String Email = requesterObj.get("email");
        // setting the value to UI element.
        incident_displayID = obj.getDisplay_id();
        incidentId.setText(incident_displayID);

        priorityName.setText(obj.getPriorityName());
        priorityName.setBackgroundColor(ContextCompat.getColor(this,
                SDUtil.getPriorityColor(obj.getPriorityName())));
        reqName.setText(RequesterName);
        reqPhone.setText(Phone);
        reqEmail.setText(Email);
        description.setText(obj.getDescription());
        symptom.setText(obj.getSymptom());
        resolution.setText(obj.getResolution());
        rootCause.setText(obj.getRootCause());
        troubleReason.setText(obj.getTroubleReason());
        recoveryAction.setText(obj.getRecoveryAction());
        summary.setText(obj.getSummary());
        impactservice.setText(obj.getServiceName());
        classification.setText(obj.getClassificationName());
        // binding the asset details
        if (inci_asset_obj != null){
            EditIncidentAssetModel incident_asset = inci_asset_obj[0];
            assetId.setText(incident_asset.getDisplay_id());
            inventoryId.setText(incident_asset.getInventory_id());
            criticallity.setText(incident_asset.getCriticality());
            itemType.setText(incident_asset.getItem_type());
        }
    }
    public void filterStatusOnState(Integer state){
        if (inci_status_obj != null){
            List<StringWithTag> statusList = new ArrayList<StringWithTag>();
            Integer statusPos=0;
            /* iterate the status list for get the CSE/Vendor user state id and build the status list*/
            for(int pos=0; pos<inci_status_obj.length; pos++){
                Integer key = inci_status_obj[pos].getId();
                String value = inci_status_obj[pos].getName();
                String state_id = inci_status_obj[pos].getState_id();
                Integer workflow_flag = inci_status_obj[pos].getWorkflow_flag();
                Integer sla_flag = inci_status_obj[pos].getSla_flag();
                if (Integer.parseInt(state_id) == state && ( workflow_flag == 3 || workflow_flag == 4
                        || sla_flag == 4 || sla_flag == 5)){
                    statusList.add(new StringWithTag(value, key));
                    if (inci_obj != null){
                        if ( key == inci_obj.getStatus()){
                            statusPos = statusList.size()-1;
                        }
                    }
                }
            }
            ArrayAdapter<StringWithTag> arrayStatusAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, statusList);
            Spinner statusSpinner = (Spinner) findViewById(R.id.edit_status);
            statusSpinner.setAdapter(arrayStatusAdapter);
            statusSpinner.setSelection(statusPos);
            statusSpinner.setOnItemSelectedListener(this);

        }
    }
    public void setValueForStateStatus(IncidentIdValueOptionModel[] state_opt,
                                       IncidentStatusModel[] status_opt, Integer selected_state_id,
                                       Integer selected_status_id){
        /* Create your ArrayList collection using StringWithTag instead of String. */
        List<StringWithTag> stateList = new ArrayList<StringWithTag>();
        List<StringWithTag> statusList = new ArrayList<StringWithTag>();
        List<String> stateCSEList = new ArrayList<String>();
        Integer statusPos=0, statePos=0;
        /* iterate the status list for get the CSE/Vendor user state id and build the status list*/
        for(int pos=0; pos<status_opt.length; pos++){
            Integer key = status_opt[pos].getId();
            String value = status_opt[pos].getName();
            String state_id = status_opt[pos].getState_id();
            Integer workflow_flag = status_opt[pos].getWorkflow_flag();
            Integer sla_flag = status_opt[pos].getSla_flag();
            if (workflow_flag == 3 || workflow_flag == 4 || sla_flag == 4 || sla_flag == 5){
                stateCSEList.add(state_id);
                statusList.add(new StringWithTag(value, key));
                if ( key == selected_status_id){
                    statusPos = statusList.size()-1;
                }
            }

        }

        /* Iterate through your original collection, in this case defined with an Integer key and String value. */
        for (int i=0; i< state_opt.length; i++) {
            Integer key = state_opt[i].getId();
            String value = state_opt[i].getName();
            if(stateCSEList.contains(key.toString())){
                /* Build the StringWithTag List using these keys and values. */
                stateList.add(new StringWithTag(value, key));
                if ( key == selected_state_id){
                    statePos = stateList.size()-1;
                }
            }

        }

        ArrayAdapter<StringWithTag> arrayStateAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, stateList);
        Spinner stateSpinner = (Spinner) findViewById(R.id.edit_state);
        stateSpinner.setAdapter(arrayStateAdapter);
        stateSpinner.setSelection(statePos);
        stateSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<StringWithTag> arrayStatusAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, statusList);
        Spinner statusSpinner = (Spinner) findViewById(R.id.edit_status);
        statusSpinner.setAdapter(arrayStatusAdapter);
        statusSpinner.setSelection(statusPos);
        statusSpinner.setOnItemSelectedListener(this);
    }

    public void setValueForUrgencyImpact(IncidentIdValueOptionModel[] urgency_opt,
                                         IncidentIdValueOptionModel[] impact_opt,
                                         IncidentPriorityOptionModel[] priority_opt,
                                         EditIncidentModel inci_obj
                                    ){
        /* Create your ArrayList collection using StringWithTag instead of String. */
        List<StringWithTag> urgencyList = new ArrayList<StringWithTag>();
        List<StringWithTag> impactList = new ArrayList<StringWithTag>();
        List<StringWithTag> priorityList =  new ArrayList<StringWithTag>();
        Integer urgencyPos=0, impactPos=0, priorityPos=0;
        for (int i=0; i< urgency_opt.length; i++) {
            Integer key = urgency_opt[i].getId();
            String value = urgency_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            urgencyList.add(new StringWithTag(value, key));
            if ( key == inci_obj.getUrgency()){
                urgencyPos = urgencyList.size()-1;
            }

        }
        ArrayAdapter<StringWithTag> arrayUrgencyAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, urgencyList);
        Spinner urgencySpinner = (Spinner) findViewById(R.id.edit_urgency);
        urgencySpinner.setAdapter(arrayUrgencyAdapter);
        urgencySpinner.setSelection(urgencyPos);
        urgencySpinner.setEnabled(false);
        for (int i=0; i< impact_opt.length; i++) {
            Integer key = impact_opt[i].getId();
            String value = impact_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            impactList.add(new StringWithTag(value, key));
            if ( key == inci_obj.getImpact()){
                impactPos = impactList.size()-1;
            }

        }
        ArrayAdapter<StringWithTag> arrayImpactAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, impactList);
        Spinner impactSpinner = (Spinner) findViewById(R.id.edit_impact);
        impactSpinner.setAdapter(arrayImpactAdapter);
        impactSpinner.setSelection(impactPos);
        impactSpinner.setEnabled(false);
        for (int i=0; i< priority_opt.length; i++) {
            Integer key = priority_opt[i].getPriority_id();
            String value = priority_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            priorityList.add(new StringWithTag(value, key));
            if ( key == inci_obj.getPriority()){
                priorityPos = priorityList.size()-1;
            }

        }
        ArrayAdapter<StringWithTag> arrayPriorityAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, priorityList);
        Spinner prioritySpinner = (Spinner) findViewById(R.id.edit_priority);
        prioritySpinner.setAdapter(arrayPriorityAdapter);
        prioritySpinner.setSelection(priorityPos);
        prioritySpinner.setEnabled(false);
    }

    public void setValueForCategory(IncidentIdValueOptionModel[] category_opt,
                                    Integer selected_category){
        if (category_opt != null){
            List<StringWithTag> categoryList = new ArrayList<StringWithTag>();
            Integer categoryPos=0;
            /* iterate the status list for get the CSE/Vendor user state id and build the status list*/
            for(int pos=0; pos<category_opt.length; pos++){
                Integer key = category_opt[pos].getId();
                String value = category_opt[pos].getName();
                categoryList.add(new StringWithTag(value, key));
                if ( key == selected_category){
                    categoryPos = categoryList.size()-1;
                }
            }
            ArrayAdapter<StringWithTag> arrayStatusAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
            Spinner categorySpinner = (Spinner) findViewById(R.id.edit_category);
            categorySpinner.setAdapter(arrayStatusAdapter);
            categorySpinner.setSelection(categoryPos);
            categorySpinner.setEnabled(false);

        }

    }

    public void saveIncident(){
        // getting the modifyed data
        String description_data = description.getText().toString();
        String symptom_data = symptom.getText().toString();
        String resolution_data =resolution.getText().toString();
        String rootCause_data = rootCause.getText().toString();
        String trouble_data = troubleReason.getText().toString();
        String recovery_data = recoveryAction.getText().toString();
        Map<String, String> requesterObj = inci_obj.getRequester();
        EditIncidentRequest inci_request_obj = new EditIncidentRequest();
        inci_request_obj.setId(inci_obj.getId());
        inci_request_obj.setDisplay_id(inci_obj.getDisplay_id());
        inci_request_obj.setRequester_id((int)Double.parseDouble(requesterObj.get("id")));
        inci_request_obj.setCategory(inci_obj.getCategory());
        inci_request_obj.setService(inci_obj.getService());
        inci_request_obj.setClassification(inci_obj.getClassification());
        inci_request_obj.setState(user_selected_state);
        inci_request_obj.setStatus(user_selected_status);
        inci_request_obj.setSummary(inci_obj.getSummary());
        inci_request_obj.setDescription(description_data);
        inci_request_obj.setUrgency(inci_obj.getUrgency());
        inci_request_obj.setImpact(inci_obj.getImpact());
        inci_request_obj.setPriority(inci_obj.getPriority());
        inci_request_obj.setGroup(inci_obj.getGroup());
        inci_request_obj.setLevel(inci_obj.getLevel());
        inci_request_obj.setLevel_seq(inci_obj.getLevel_seq());
        inci_request_obj.setCustomer_site_group(inci_obj.getCustomer_site_group());
        inci_request_obj.setAssignee(inci_obj.getAssignee());
        inci_request_obj.setIncident_source(inci_obj.getIncident_source());
        inci_request_obj.setQueue(inci_obj.getQueue());
        inci_request_obj.setWorkflow_config(inci_obj.getWorkflow_config());
        inci_request_obj.setServiceName(inci_obj.getServiceName());
        inci_request_obj.setClassificationName(inci_obj.getClassificationName());
        inci_request_obj.setResolutionType(inci_obj.getResolutionType());
        inci_request_obj.setImpactArea(inci_obj.getImpactArea());
        inci_request_obj.setCallback(inci_obj.getCallback());
        inci_request_obj.setSymptom(symptom_data);
        inci_request_obj.setResolution(resolution_data);
        inci_request_obj.setRootCause(rootCause_data);
        inci_request_obj.setTroubleReason(trouble_data);
        inci_request_obj.setRecoveryAction(recovery_data);
        inci_request_obj.setTeam_template(inci_obj.getTeam_template());
        inci_request_obj.setSla(inci_obj.getSla());
        inci_request_obj.setCseRequired(inci_obj.getCseRequired());
        inci_request_obj.setCse_required(inci_obj.getCse_required());
        inci_request_obj.setVendor(inci_obj.getVendor());
        inci_request_obj.setVendor_team(inci_obj.getVendor_team());
        inci_request_obj.setVendor_cse(inci_obj.getVendor_cse());
        inci_request_obj.setCse_assignee(inci_obj.getCse_assignee());
        Map<String, Object> incidentSaveMap = new HashMap<String, Object>();
        incidentSaveMap.put("baseurl", SDUtil.getSession(this, "domain"));
        incidentSaveMap.put("path", RestfulEndpoints.SaveIncident.get());
        incidentSaveMap.put("data", inci_request_obj);
        RestFulPost restFulPost = new RestFulPost(this, this.getApplication(), "Please Wait", "SaveIncident", true);
        restFulPost.execute(incidentSaveMap);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResfulResponse(String result, String responseFor) {
        Gson gson = new Gson();
        if (responseFor.equals("EditIncident")) {
            SDEditIncidentResponse response = gson.fromJson(result, SDEditIncidentResponse.class);
            inci_obj = gson.fromJson(gson.toJson(response.getIncident()),
                    EditIncidentModel.class);
            inci_asset_obj = gson.fromJson(gson.toJson(response.getAsset_details()),
                    EditIncidentAssetModel[].class);
            inci_option_obj = gson.fromJson(gson.toJson(response.getOption()),
                    IncidentOptionModel.class);
            inci_state_obj = gson.fromJson(gson.toJson(inci_option_obj.getState()),
                    IncidentIdValueOptionModel[].class);
            inci_status_obj = gson.fromJson(gson.toJson(inci_option_obj.getStatus()),
                    IncidentStatusModel[].class);
            inci_urgency_obj = gson.fromJson(gson.toJson(inci_option_obj.getUrgency()),
                    IncidentIdValueOptionModel[].class);
            inci_impact_obj = gson.fromJson(gson.toJson(inci_option_obj.getImpact()),
                    IncidentIdValueOptionModel[].class);
            inci_priority_obj = gson.fromJson(gson.toJson(inci_option_obj.getPriority()),
                    IncidentPriorityOptionModel[].class);
            inci_Category_obj = gson.fromJson(gson.toJson(inci_option_obj.getCategory()),
                    IncidentIdValueOptionModel[].class);
            setValueForStateStatus(inci_state_obj, inci_status_obj, inci_obj.getState(), inci_obj.getStatus());
            setValueForUrgencyImpact(inci_urgency_obj, inci_impact_obj, inci_priority_obj, inci_obj);
            setValueForCategory(inci_Category_obj, inci_obj.getCategory());
            setValuetoUI(inci_obj, inci_asset_obj);
        }else if (responseFor.equals("SaveIncident")) {

            if (result == null) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error! Unable to save.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Success! Incident Id "+incident_displayID+ " saved Successfully", Toast.LENGTH_SHORT);
                toast.show();
                SDUtil.callIntent(this, HomeActivity.class, Edit_Incident.this, "no");
            }
            
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_save:
                saveIncident();
                break;
        }
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        StringWithTag swt = (StringWithTag) parent.getItemAtPosition(pos);
        Integer key = (Integer) swt.tag;
        switch (parent.getId()) {
            case R.id.edit_state:
                user_selected_state = key;
                filterStatusOnState(key);
                break;
            case R.id.edit_status:
                user_selected_status = key;
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
