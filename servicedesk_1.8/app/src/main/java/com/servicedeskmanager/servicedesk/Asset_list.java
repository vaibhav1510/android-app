package com.servicedeskmanager.servicedesk;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.adapter.AssetListAdapter;
import com.servicedeskmanager.servicedesk.model.AssetListModel;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDRequesterSearchResponce;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asset_list extends AppCompatActivity implements RestFulResult {

    AssetListAdapter assetAdapter;
    SDRequesterSearchResponce response;
    RecyclerView recyclerView;
    AssetListModel asset_obj;
    String searchData;
    Integer reqId;

    String reqName,reqPhone,reqMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_list);

        Intent intent = getIntent();
        searchData = intent.getStringExtra("Search Data");
        getAssetData(searchData);
    }

    public void getAssetData(String searchData){

        Map<String, Object> assetDataMap = new HashMap<String, Object>();
        String domainShared = SDUtil.getSession(this, "domain");
        String searchUrl = RestfulEndpoints.SearchRequester.get()+ searchData + "/";
        assetDataMap.put("baseurl", domainShared);
        assetDataMap.put("path", searchUrl);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "SearchRequester", true);
        restFulGet.execute(assetDataMap);
    }

   /* public void onLongPress(MotionEvent e) {

        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (actionMode != null) {
            return;
        }
        actionMode = startActionMode(RecyclerViewDemoActivity.this);
        int idx = recyclerView.getChildPosition(view);
        myToggleSelection(idx);
        super.onLongPress(e);
    }

    private void myToggleSelection(int id) {
        assetAdapter.toggleSelection(id);
        String title = getString(
                R.string.selected_count,
                assetAdapter.getSelectedItemCount());
        actionMode.setTitle(title);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.asset_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_asset_save:
                Intent intent=new Intent(Asset_list.this,Add_incident.class);
                intent.putExtra("Req_Name", response.getFull_name().toString());
                intent.putExtra("Req_Phone", response.getPhone().toString());
                intent.putExtra("Req_Email", response.getEmail().toString());
                intent.putExtra("Req_Id", response.getId());
                intent.putExtra("Req_Loc", response.getSupport_location());

                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onResfulResponse(String result, String responseFor) {
       Gson gson = new Gson();
       if (responseFor.equals("SearchRequester")) {
           response = gson.fromJson(result, SDRequesterSearchResponce.class);
           AssetListModel[] asset_obj = gson.fromJson(gson.toJson(response.getAsset_details()),
                   AssetListModel[].class);

           recyclerView = (RecyclerView) findViewById(R.id.recycler_asset_list);
           AssetListAdapter assetAdapter = new  AssetListAdapter(this, asset_obj, new  AssetListAdapter.OnItemClickListener() {
               @Override public void onItemClick(AssetListModel item) {
               }
           });
           RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
           recyclerView.setLayoutManager(mLayoutManager);
           recyclerView.setItemAnimator(new DefaultItemAnimator());
           recyclerView.setAdapter(assetAdapter);
       }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
