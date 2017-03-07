package com.servicedeskmanager.servicedesk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.adapter.IncidentAdapter;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;
import com.servicedeskmanager.servicedesk.model.UserProfileModel;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDListPaginationResponse;
import com.servicedeskmanager.servicedesk.util.SDUserDeatilsResponse;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RestFulResult {

    IncidentAdapter inciAdapter;
    public  HomeActivity CustomListView = null;
    TextView userName, email;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomListView = this;
        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        getUserDetails();
        getListData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddIncident = new Intent(HomeActivity.this, Add_incident.class);
                startActivity(intentAddIncident);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*Function for getting the user deatils from Server*/
    public void getUserDetails() {
        Map<String, Object> homeMap = new HashMap<String, Object>();
        String domainShared = SDUtil.getSession(this, "domain");
        homeMap.put("baseurl", domainShared);
        homeMap.put("path", RestfulEndpoints.GetUserDetails.get());
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "GetUserDetails", true);
        restFulGet.execute(homeMap);
    }

    /******
     * Function to get data from server in ArrayList
     *************/
    public void getListData() {
        Map<String, Object> homeMap = new HashMap<String, Object>();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("page", "1");
        queryParam.put("items_per_page", "25");
        queryParam.put("sort", "-creation_time");
        String domainShared = SDUtil.getSession(this, "domain");
        homeMap.put("baseurl", domainShared);
        homeMap.put("path", RestfulEndpoints.AllIncident.get());
        homeMap.put("queryparam", queryParam);
        RestFulGet restFulGet = new RestFulGet(this, this.getApplication(), "Please Wait", "AllIncident", true);
        restFulGet.execute(homeMap);
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

        } else if (id == R.id.logout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.logout_msg)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SDUtil.callIntent(HomeActivity.this, LoginActivity.class, HomeActivity.this, "no");
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            //Other stuff in OnCreate();

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
            IncidentListModel[] inci_obj = gson.fromJson(gson.toJson(response.getResults()),
                    IncidentListModel[].class);

            Resources res = getResources();
            recyclerView = (RecyclerView) findViewById(R.id.recycler_incident_view);
            inciAdapter = new IncidentAdapter(this, inci_obj, new IncidentAdapter.OnItemClickListener() {
                @Override public void onItemClick(IncidentListModel item) {
                    Intent intent = new Intent(HomeActivity.this, Edit_Incident.class);
                    intent.putExtra("incidentId", item.getId() + "");
                    startActivity(intent);
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(inciAdapter);

        } else if (responseFor.equals("GetUserDetails")) {
            SDUserDeatilsResponse user_response = gson.fromJson(result, SDUserDeatilsResponse.class);
            UserProfileModel user_profile = gson.fromJson(gson.toJson(user_response.getProfile()),
                    UserProfileModel.class);
            userName = (TextView) findViewById(R.id.userName);
            email = (TextView) findViewById(R.id.email);
            userName.setText(user_profile.getUsername());
            email.setText(user_profile.getOrganization_name());
        }
    }
}
