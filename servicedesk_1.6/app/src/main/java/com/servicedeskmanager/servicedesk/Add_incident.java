package com.servicedeskmanager.servicedesk;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Add_incident extends AppCompatActivity {

    String[] categorylist = {"Hardware", "Software"};
    String[] impactlist = {"Low", "Medium","High"};
    String[] urgencylist = {"Low", "Medium","High"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.add(Menu.NONE, 1000, Menu.NONE, "Save");
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("====>>","SAVEDDDDDDD");
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_incident);
        getIntent();

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,categorylist);
        MaterialBetterSpinner categorySpinner = (MaterialBetterSpinner) findViewById(R.id.Category_spinner);
        categorySpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, impactlist);
        MaterialBetterSpinner impactSpinner = (MaterialBetterSpinner) findViewById(R.id.impact_spinner);
        impactSpinner.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, urgencylist);
        MaterialBetterSpinner urgencySpinner = (MaterialBetterSpinner) findViewById(R.id.Urgency_spinner);
        urgencySpinner.setAdapter(arrayAdapter3);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
