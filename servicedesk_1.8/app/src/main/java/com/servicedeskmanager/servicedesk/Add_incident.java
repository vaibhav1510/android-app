package com.servicedeskmanager.servicedesk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.adapter.SelectedAssetListAdapter;
import com.servicedeskmanager.servicedesk.model.AddIncidentRequest;
import com.servicedeskmanager.servicedesk.model.AssetListModel;
import com.servicedeskmanager.servicedesk.model.IncidentIdValueOptionModel;
import com.servicedeskmanager.servicedesk.model.IncidentPriorityMatrix;
import com.servicedeskmanager.servicedesk.model.IncidentPriorityOptionModel;
import com.servicedeskmanager.servicedesk.model.IncidentStatusModel;
import com.servicedeskmanager.servicedesk.model.ServiceDataModel;
import com.servicedeskmanager.servicedesk.model.StringWithTag;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulPost;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDOptionAddIncident;
import com.servicedeskmanager.servicedesk.util.SDServiceResponse;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add_incident extends AppCompatActivity implements RestFulResult,
        AdapterView.OnItemSelectedListener {

    IncidentIdValueOptionModel[] inci_state_obj;
    IncidentIdValueOptionModel[] inci_urgency_obj;
    IncidentIdValueOptionModel[] inci_impact_obj;
    IncidentIdValueOptionModel[] inci_Category_obj;
    IncidentStatusModel[] inci_status_obj;
    IncidentPriorityOptionModel[] inci_priority_obj;
    Object[] serviceList, priority_matrix;
    SDServiceResponse serviceResponse;
    Integer selected_category, selected_impact_service, selected_state, selected_status,
            selected_urgency, selected_impact, selected_priority, requester_id, req_loc;
    Integer default_urgency, default_impact, queue, workflow_config;
    String sla, selected_impact_service_name;
    Boolean loading = false, workflowLoading = false;
    String req_name,req_phone,req_email;
    TextView reqname, reqPhone, reqMail;
    Spinner prioritySpinner, urgencySpinner, impactSpinner;
    ImageButton searchBtn;
    EditText searchData;
    List<StringWithTag> urgencyList;
    List<StringWithTag> impactList;
    List<StringWithTag> priorityList;
    RecyclerView recyclerView;
   // AssetListModel[] selectAsset;
    public ArrayList<AssetListModel> selectAsset = new ArrayList<AssetListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_incident);
        urgencySpinner = (Spinner) findViewById(R.id.add_urgency);
        impactSpinner = (Spinner) findViewById(R.id.add_impact);
        prioritySpinner = (Spinner) findViewById(R.id.add_priority);
        reqname=(TextView)findViewById(R.id.req_name);
        reqPhone=(TextView)findViewById(R.id.req_phon_no);
        reqMail=(TextView)findViewById(R.id.req_email);
        searchBtn = (ImageButton) findViewById(R.id.reqSearchImageBtn);
        searchData=(EditText)findViewById(R.id.searchData);
        // getting the data from activity
        setListData();

        Intent intent = getIntent();
        req_name = intent.getStringExtra("Req_Name");
        req_phone = intent.getStringExtra("Req_Phone");
        req_email = intent.getStringExtra("Req_Email");
        requester_id = intent.getIntExtra("Req_Id", -1);
        req_loc = intent.getIntExtra("Req_Loc", -1);

        if (requester_id != -1) {
            reqname.setText(req_name);
            reqPhone.setText(req_phone);
            reqMail.setText(req_email);
            getIncidentOption();

        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchReqIntent=new Intent(Add_incident.this, Asset_list.class);
                searchReqIntent.putExtra("Search Data",searchData.getText().toString());
                startActivity(searchReqIntent);
            }
        });

       recyclerView = (RecyclerView) findViewById(R.id.recycler_asset_list);
        SelectedAssetListAdapter assetAdapter = new  SelectedAssetListAdapter(this, selectAsset, new  SelectedAssetListAdapter.OnItemClickListener() {
            @Override public void onItemClick(AssetListModel item) {
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(assetAdapter);
    }

    /* function used for get the dropdown values from server from server */
    public void getIncidentOption(){
        Map<String, Object> addOptionMap = new HashMap<String, Object>();
        String domainShared = SDUtil.getSession(this, "domain");
        String editUrl = RestfulEndpoints.AddIncidentOption.get();
        addOptionMap.put("baseurl", domainShared);
        addOptionMap.put("path", editUrl);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "AddIncidentOption", true);
        restFulGet.execute(addOptionMap);

    }
    /*Function used for bind the category*/
    public void bindCategoryOption(IncidentIdValueOptionModel[] category_opt){
        List<StringWithTag> categoryList = new ArrayList<StringWithTag>();
        if (category_opt != null){
            Integer categoryPos=0;
            /* iterate the status list for get the CSE/Vendor user state id and build the status list*/
            for(int pos=0; pos<category_opt.length; pos++){
                Integer key = category_opt[pos].getId();
                String value = category_opt[pos].getName();
                categoryList.add(new StringWithTag(value, key));
                /*if ( key == selected_category){
                    categoryPos = categoryList.size()-1;
                }*/
            }
            ArrayAdapter<StringWithTag> arrayStatusAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
            Spinner categorySpinner = (Spinner) findViewById(R.id.add_category);
            categorySpinner.setAdapter(arrayStatusAdapter);
            categorySpinner.setOnItemSelectedListener(this);
        }
    }

    /*Function for get the service data*/
    public void bindServiceData(Object[] service_opt){
        List<StringWithTag> stateList = new ArrayList<StringWithTag>();
        Gson gson = new Gson();
        if (service_opt != null){
             /* Create your ArrayList collection using StringWithTag instead of String. */
            for (int i=0; i< service_opt.length; i++) {
                ServiceDataModel serviceRow = gson.fromJson(gson.toJson(service_opt[i]),
                        ServiceDataModel.class);
                Integer key = serviceRow.getId();
                String value = serviceRow.getName();
                String node_type = serviceRow.getNode_type();
                if (node_type.equals("service")) {
                     /* Build the StringWithTag List using these keys and values. */
                    stateList.add(new StringWithTag(value, key));
                }
            }
        }
        ArrayAdapter<StringWithTag> arrayImpactServiceAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, stateList);
        Spinner impactServiceSpinner = (Spinner) findViewById(R.id.add_impact_service);
        impactServiceSpinner.setAdapter(arrayImpactServiceAdapter);
        impactServiceSpinner.setOnItemSelectedListener(this);
    }
    /* Function for bind the Urgency Impact priority*/
    public void bindUrgencyImpactPriority(IncidentIdValueOptionModel[] urgency_opt,
                                          IncidentIdValueOptionModel[] impact_opt,
                                          IncidentPriorityOptionModel[] priority_opt){
        urgencyList = new ArrayList<StringWithTag>();
        impactList = new ArrayList<StringWithTag>();
        priorityList = new ArrayList<StringWithTag>();
        Integer urgencyPos=0, impactPos=0, priorityPos=0;
        for (int i=0; i< urgency_opt.length; i++) {
            Integer key = urgency_opt[i].getId();
            String value = urgency_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            urgencyList.add(new StringWithTag(value, key));
            if ( key == default_urgency){
                urgencyPos = urgencyList.size()-1;
            }

        }
        ArrayAdapter<StringWithTag> arrayUrgencyAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, urgencyList);
        urgencySpinner.setAdapter(arrayUrgencyAdapter);
        urgencySpinner.setSelection(urgencyPos);
        for (int i=0; i< impact_opt.length; i++) {
            Integer key = impact_opt[i].getId();
            String value = impact_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            impactList.add(new StringWithTag(value, key));
            if ( key == default_impact){
                impactPos = impactList.size()-1;
            }

        }
        ArrayAdapter<StringWithTag> arrayImpactAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, impactList);
        impactSpinner.setAdapter(arrayImpactAdapter);
        impactSpinner.setSelection(impactPos);
        Integer priority_id = getPriorityBaseMatrix(default_impact, default_urgency);
        for (int i=0; i< priority_opt.length; i++) {
            Integer key = priority_opt[i].getPriority_id();
            String value = priority_opt[i].getName();
            /* Build the StringWithTag List using these keys and values. */
            priorityList.add(new StringWithTag(value, key));
            if(key == priority_id){
                priorityPos = priorityList.size()-1;
            }
        }
        ArrayAdapter<StringWithTag> arrayPriorityAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, priorityList);
        prioritySpinner.setAdapter(arrayPriorityAdapter);
        prioritySpinner.setSelection(priorityPos);

    }
    /* Function used for the get priority based on define the matrix */
    public Integer getPriorityBaseMatrix(Integer impact, Integer urgency){
        Integer priority_id = -1;
        Gson gson = new Gson();
        if(priority_matrix != null){
            for(int i=0; i<priority_matrix.length; i++){
                IncidentPriorityMatrix row_obj = gson.fromJson(gson.toJson(priority_matrix[i]),
                        IncidentPriorityMatrix.class);
                if((row_obj.getImpact_id() == impact) && (row_obj.getUrgency_id() == urgency)){
                    priority_id = row_obj.getPriority_id();
                    break;
                }
            }

        }
        return priority_id;
    }
    /* function used for find the position of urgency id, impact id and priority id and
    select the value on dropdown*/
    public void modifySelectionUrgencyImpactPriority(Integer impact, Integer urgency, Integer priority){
        Integer urgencyPos=0, impactPos=0, priorityPos=0;
        if(urgencyList != null){
            for(int i=0; i<urgencyList.size(); i++){
                Integer key = ((Integer)urgencyList.get(i).getTag()).intValue();
                if (key == urgency){
                    urgencyPos = i;
                    break;
                }
            }
        }
        if(impactList != null){
            for(int i=0; i<impactList.size(); i++){
                Integer key = ((Integer)impactList.get(i).getTag()).intValue();
                if (key == impact){
                    impactPos = i;
                    break;
                }
            }
        }
        if(priorityList != null){
            for(int i=0; i<priorityList.size(); i++){
                Integer key = ((Integer)priorityList.get(i).getTag()).intValue();
                if (key == priority){
                    priorityPos = i;
                    break;
                }
            }
        }
        urgencySpinner.setSelection(urgencyPos);
        impactSpinner.setSelection(impactPos);
        prioritySpinner.setSelection(priorityPos);
    }
    /* Function for bind the state and status*/
    public void bindStateStatusOption(IncidentIdValueOptionModel[] state_opt,
                                      IncidentStatusModel[] status_opt){
        /* Create your ArrayList collection using StringWithTag instead of String. */
        List<StringWithTag> stateList = new ArrayList<StringWithTag>();
        List<StringWithTag> statusList = new ArrayList<StringWithTag>();
        List<String> checkStateList = new ArrayList<String>();
        Integer statusPos=0, statePos=0;
        /* iterate the status list for get the CSE/Vendor user state id and build the status list*/
        for(int pos=0; pos<status_opt.length; pos++){
            Integer key = status_opt[pos].getId();
            String value = status_opt[pos].getName();
            String state_id = status_opt[pos].getState_id();
            Integer workflow_flag = status_opt[pos].getWorkflow_flag();
            Integer sla_flag = status_opt[pos].getSla_flag();
            if (workflow_flag == 1){
                checkStateList.add(state_id);
                statusList.add(new StringWithTag(value, key));
                statusPos = statusList.size()-1;
            }

        }

        /* Iterate through your original collection, in this case defined with an Integer key and String value. */
        for (int i=0; i< state_opt.length; i++) {
            Integer key = state_opt[i].getId();
            String value = state_opt[i].getName();
            if(checkStateList.contains(key.toString())){
                /* Build the StringWithTag List using these keys and values. */
                stateList.add(new StringWithTag(value, key));
            }
        }

        ArrayAdapter<StringWithTag> arrayStateAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, stateList);
        Spinner stateSpinner = (Spinner) findViewById(R.id.add_state);
        stateSpinner.setAdapter(arrayStateAdapter);
        stateSpinner.setSelection(statePos);
        stateSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<StringWithTag> arrayStatusAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_dropdown_item, statusList);
        Spinner statusSpinner = (Spinner) findViewById(R.id.add_status);
        statusSpinner.setAdapter(arrayStatusAdapter);
        statusSpinner.setSelection(statusPos);
        statusSpinner.setOnItemSelectedListener(this);
    }

    /* Function for get the data based on selected category ID */
    public void getServiceData(Integer category_id){
        if (category_id != 0 && !loading){
            loading = true;
            Map<String, Object> serviceMap = new HashMap<String, Object>();
            String domainShared = SDUtil.getSession(this, "domain");
            String editUrl = RestfulEndpoints.GetImpactServiceList.get()+ category_id+ "/";
            serviceMap.put("baseurl", domainShared);
            serviceMap.put("path", editUrl);
            RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "GetImpactServiceList", true);
            restFulGet.execute(serviceMap);

        }
    }

    /*Function for get the workflow data for selected service*/
    public void getWorkflowData(Integer service_id){
        if (service_id != 0 && !workflowLoading){
            workflowLoading = true;
            Map<String, Object> workflowMap = new HashMap<String, Object>();
            String domainShared = SDUtil.getSession(this, "domain");
            String editUrl = RestfulEndpoints.GetServiceBaseConfig.get()+ service_id+ "/" + selected_category +"/";
            workflowMap.put("baseurl", domainShared);
            workflowMap.put("path", editUrl);
            RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "GetServiceBaseConfig", true);
            restFulGet.execute(workflowMap);
        }
    }

    /* Function for Save the incident */
    public void saveIncident(){
        // getting the modifyed data
        EditText summary = (EditText)findViewById(R.id.add_summary);
        EditText description = (EditText)findViewById(R.id.add_description);
        String description_data = description.getText().toString();
        String summary_data = summary.getText().toString();
        AddIncidentRequest inci_request_obj = new AddIncidentRequest();
        inci_request_obj.setCategory(selected_category);
        inci_request_obj.setState(selected_state);
        inci_request_obj.setStatus(selected_status);
        inci_request_obj.setImpact(selected_impact);
        inci_request_obj.setUrgency(selected_urgency);
        inci_request_obj.setPriority(selected_priority);
        inci_request_obj.setQueue_id(queue);
        inci_request_obj.setWorkflow_id(workflow_config);
        inci_request_obj.setService(selected_impact_service);
        inci_request_obj.setServiceName(selected_impact_service_name);
        inci_request_obj.setRequester(req_email);
        inci_request_obj.setRequester_id(requester_id);
        inci_request_obj.setRequester_support_loc(req_loc);
        inci_request_obj.setSla(Integer.parseInt(sla));
        inci_request_obj.setSummary(summary_data);
        inci_request_obj.setDescription(description_data);
        Map<String, Object> incidentSaveMap = new HashMap<String, Object>();
        incidentSaveMap.put("baseurl", SDUtil.getSession(this, "domain"));
        incidentSaveMap.put("path", RestfulEndpoints.SaveIncident.get());
        incidentSaveMap.put("data", inci_request_obj);
        RestFulPost restFulPost = new RestFulPost(this, this.getApplication(), "Please Wait", "SaveIncident", true);
        restFulPost.execute(incidentSaveMap);
    }

    public void setListData()
    {
        AssetListModel  itemAsset = new AssetListModel();

        itemAsset.setDisplay_id("ITEM0002");
        itemAsset.setItem_type("Firewall");
        itemAsset.setInventory_id("10");
        itemAsset.setCriticality("4-Low");

        selectAsset.add(itemAsset);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_inci_save:
                saveIncident();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResfulResponse(String result, String responseFor) {
        Gson gson = new Gson();
        if(responseFor.equals("AddIncidentOption")) {
            if (result != null){
                SDOptionAddIncident response = gson.fromJson(result, SDOptionAddIncident.class);
                inci_state_obj = gson.fromJson(gson.toJson(response.getState()),
                        IncidentIdValueOptionModel[].class);
                inci_status_obj = gson.fromJson(gson.toJson(response.getStatus()),
                        IncidentStatusModel[].class);
                inci_urgency_obj = gson.fromJson(gson.toJson(response.getUrgency()),
                        IncidentIdValueOptionModel[].class);
                inci_impact_obj = gson.fromJson(gson.toJson(response.getImpact()),
                        IncidentIdValueOptionModel[].class);
                inci_priority_obj = gson.fromJson(gson.toJson(response.getPriority()),
                        IncidentPriorityOptionModel[].class);
                inci_Category_obj = gson.fromJson(gson.toJson(response.getCategory()),
                        IncidentIdValueOptionModel[].class);
                bindStateStatusOption(inci_state_obj, inci_status_obj);
                default_urgency = response.getDefault_urgency();
                default_impact = response.getDefault_impact();
                priority_matrix = response.getMatrix();
                queue = response.getQueue();
                workflow_config = response.getWorkflow_config();
                sla = response.getSla();
                bindCategoryOption(inci_Category_obj);
                bindUrgencyImpactPriority(inci_urgency_obj, inci_impact_obj, inci_priority_obj);

            }
        }else if(responseFor.equals("GetImpactServiceList")){
            serviceList = gson.fromJson(result, Object[].class);
            bindServiceData(serviceList);
            loading = false;
        }else if(responseFor.equals("GetServiceBaseConfig")){
            serviceResponse = gson.fromJson(result, SDServiceResponse.class);
            default_urgency = serviceResponse.getDefault_urgency();
            default_impact = serviceResponse.getDefault_impact();
            priority_matrix = serviceResponse.getMatrix();
            queue = serviceResponse.getQueue();
            workflow_config = serviceResponse.getWorkflow_config();
            sla = serviceResponse.getSla();
            Integer priority_id = getPriorityBaseMatrix(default_impact,default_urgency);
            modifySelectionUrgencyImpactPriority(default_impact, default_urgency, priority_id);
            workflowLoading = false;
        }else if (responseFor.equals("SaveIncident")) {
            if (result == null) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error! Unable to save.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Success! saved Successfully", Toast.LENGTH_SHORT);
                toast.show();
                SDUtil.callIntent(this, HomeActivity.class, Add_incident.this, "no");
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        StringWithTag swt = (StringWithTag) parent.getItemAtPosition(pos);
        Integer key = (Integer) swt.tag;
        switch (parent.getId()) {
            case R.id.add_category:
                selected_category = key;
                getServiceData(key);
                break;
            case R.id.add_impact_service:
                selected_impact_service = key;
                selected_impact_service_name = swt.string;
                getWorkflowData(key);
                break;
            case R.id.add_state:
                selected_state = key;
                break;
            case R.id.add_status:
                selected_status = key;
                break;
            case R.id.add_urgency:
                selected_urgency = key;
                modifySelectionUrgencyImpactPriority(selected_impact, selected_urgency, selected_priority);
                break;
            case R.id.add_impact:
                selected_impact = key;
                modifySelectionUrgencyImpactPriority(selected_impact, selected_urgency, selected_priority);
                break;
            case R.id.add_priority:
                selected_priority = key;
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
