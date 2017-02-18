package com.mindsservicemanager.servicedesk;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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
        setListData();

        Resources res =getResources();
        list= ( ListView )findViewById( R.id.incidentlist );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new IncidentListAdapter(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter( adapter );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), "Edit Page", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intentAddIncident = new Intent(HomeActivity.this, IncidentAdd.class);
                startActivity(intentAddIncident);
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
    /****** Function to set data in ArrayList *************/
    public void setListData()
    {

        final IncidentListModel inci1 = new IncidentListModel();
        final IncidentListModel inci2 = new IncidentListModel();
        final IncidentListModel inci3 = new IncidentListModel();
        /******* Firstly take data in model object ******/
        inci1.setCustomerName("Harshita ");
        inci1.setAsignDate("Feb 10,2017");
        inci1.setIncidentId("INCI201702130018");
        inci1.setImpect("Service 1");
        inci1.setSummary("Ram not working");
        inci1.setDescription("I bought a laptop last month and i installed the graphics card but my...");
        inci1.setPriority("1-Critical");
        inci1.setSlaDate("Feb 10,2017");
        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(  inci1 );

        inci2.setCustomerName("Nikita ");
        inci2.setAsignDate("Feb 10,2017");
        inci2.setIncidentId("INCI201702130019");
        inci2.setImpect("Service 1");
        inci2.setSummary("testing");
        inci2.setDescription("I bought a laptop last month and i installed the graphics card but my...");
        inci2.setPriority("4-Low");
        inci2.setSlaDate("Feb 10,2017");
        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add( inci2 );

        inci3.setCustomerName("Samtoshi ");
        inci3.setAsignDate("Feb 10,2017");
        inci3.setIncidentId("INCI201702130020");
        inci3.setImpect("Service 1");
        inci3.setSummary("Hardware");
        inci3.setDescription("I bought a laptop last month and i installed the graphics card but my...");
        inci3.setPriority("5-very Low");
        inci3.setSlaDate("Feb 10,2017");
        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add( inci3 );

    }

    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        IncidentListModel tempValues = ( IncidentListModel ) CustomListViewValuesArr.get(mPosition);
        // SHOW ALERT
        Toast.makeText(CustomListView, "Edit page", Toast.LENGTH_LONG).show();
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
            // Handle the camera action
        } else if (id == R.id.addincident_nav) {

        }else if (id == R.id.editincident_nav) {
            Intent intent=new Intent(HomeActivity.this,Edit_Incident.class);
            startActivity(intent);
        }else if (id == R.id.setting) {

        } else if (id == R.id.logout) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
