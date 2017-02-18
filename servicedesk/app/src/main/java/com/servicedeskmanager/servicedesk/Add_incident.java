package com.servicedeskmanager.servicedesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;


public class Add_incident extends AppCompatActivity {

    String[] CATEGORYLIST = {"Hardware", "Software"};
    String[] IMPECTLIST = {"Low", "Medium","High"};
    String[] URGANCYLIST = {"Low", "Medium","High"};

    EditText search,summary,description;
    TextView req_name, req_phon_no, req_mail;

    JSONObject jObj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_incident);
        getIntent();

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CATEGORYLIST);
        MaterialBetterSpinner categorySpinner = (MaterialBetterSpinner) findViewById(R.id.Category_spinner);
        categorySpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, IMPECTLIST);
        MaterialBetterSpinner impactSpinner = (MaterialBetterSpinner) findViewById(R.id.impact_spinner);
        impactSpinner.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, URGANCYLIST);
        MaterialBetterSpinner urgencySpinner = (MaterialBetterSpinner) findViewById(R.id.Urgency_spinner);
        urgencySpinner.setAdapter(arrayAdapter3);

        search=(EditText) findViewById(R.id.add_search);

        req_name=(TextView)findViewById(R.id.req_name);
        req_phon_no=(TextView)findViewById(R.id.req_phon_no);
        req_mail=(TextView) findViewById(R.id.req_mail);

        summary=(EditText)findViewById(R.id.summary);
        description=(EditText)findViewById(R.id.description);

        try {
            jObj.put("search", search.getText());
            jObj.put("requester name",req_name.getText());
            jObj.put("requester name",req_phon_no.getText());
            jObj.put("requester phon no",req_mail.getText());
            jObj.put("Category",categorySpinner.getText());
            jObj.put("Impact",impactSpinner.getText());
            jObj.put("Urgency",urgencySpinner.getText());
            jObj.put("Summary",summary.getText());
            jObj.put("Description",description.getText());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(jObj);
    }

    @Override
    public void onBackPressed(){
    }
}
