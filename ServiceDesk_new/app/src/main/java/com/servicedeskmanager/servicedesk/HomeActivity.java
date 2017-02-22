package com.servicedeskmanager.servicedesk;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.adapter.IncidentListAdapter;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;
import com.servicedeskmanager.servicedesk.model.IncidentListResponseData;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDListPaginationResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RestFulResult {


    ListView list;
    IncidentListAdapter adapter;
    public  HomeActivity CustomListView = null;
    public ArrayList<IncidentListModel> CustomListViewValuesArr = new ArrayList<IncidentListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomListView = this;
        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        getListData();

        Resources res =getResources();
        list= ( ListView )findViewById( R.id.incidentlist );  // List defined in XML ( See Below )
        /**************** Create Custom Adapter *********/
        adapter=new IncidentListAdapter(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter( adapter );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddIncident = new Intent(HomeActivity.this, Add_incident.class);
                startActivity(intentAddIncident);
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        final GestureDetector gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Intent intent=new Intent(HomeActivity.this,Edit_Incident.class);
                startActivity(intent);
                return true;
            }

        });
        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gd.onTouchEvent(event);
            }
        });

        /*list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText(getApplicationContext(), "Edit Page", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeActivity.this,Edit_Incident.class);
                startActivity(intent);
                return false;
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    /****** Function to set data in ArrayList *************/
    public void getListData()
    {
        Map<String, Object> homeMap = new HashMap<String, Object>();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("page", "1");
        queryParam.put("items_per_page", "25");
        queryParam.put("sort", "-creation_time");
        homeMap.put("baseurl", RestfulEndpoints.Base.get());
        homeMap.put("path", RestfulEndpoints.AllIncident.get());
        homeMap.put("queryparam", queryParam);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "AllIncident", true);
        restFulGet.execute(homeMap);
    }

    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        IncidentListModel tempValues = ( IncidentListModel ) CustomListViewValuesArr.get(mPosition);
        // SHOW ALERT

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.incident) {

        } else if (id == R.id.setting) {

        } else if (id == R.id.logout) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onResfulResponse(String result, String responseFor) {
        Gson gson = new Gson();
        if (responseFor.equals("AllIncident")) {

            SDListPaginationResponse response = gson.fromJson(result, SDListPaginationResponse.class);
            IncidentListResponseData inci_obj = gson.fromJson(gson.toJson(response.getResults()),
                    IncidentListResponseData.class);
            ArrayList<IncidentListModel> inci_list = inci_obj.getIncidentList();
            int i = inci_list.size();
            Log.i("Size", i+ "");

        }
    }
}
