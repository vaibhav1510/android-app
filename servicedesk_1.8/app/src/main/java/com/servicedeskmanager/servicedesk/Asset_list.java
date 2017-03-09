package com.servicedeskmanager.servicedesk;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeTransform;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.google.gson.Gson;
import com.servicedeskmanager.servicedesk.adapter.AssetListAdapter;
import com.servicedeskmanager.servicedesk.model.AssetListModel;
import com.servicedeskmanager.servicedesk.restful.RestFulGet;
import com.servicedeskmanager.servicedesk.restful.RestFulResult;
import com.servicedeskmanager.servicedesk.restful.RestfulEndpoints;
import com.servicedeskmanager.servicedesk.util.SDRequesterSearchResponce;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.view.GestureDetector.SimpleOnGestureListener;

public class Asset_list extends AppCompatActivity implements RestFulResult,
        RecyclerView.OnItemTouchListener,
        View.OnClickListener,
        android.view.ActionMode.Callback
        {
//, GestureDetector.OnGestureListener - implement this
    AssetListAdapter assetAdapter;
    SDRequesterSearchResponce response;
    RecyclerView recyclerView;
    AssetListModel asset_obj;
    String searchData;
    Integer reqId;
            Context mContext;
            GestureDetectorCompat gestureDetector;
    String reqName,reqPhone,reqMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setAllowReturnTransitionOverlap(true);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setSharedElementExitTransition(new ChangeTransform());

        setContentView(R.layout.activity_asset_list);

        Intent intent = getIntent();
        searchData = intent.getStringExtra("Search Data");
        getAssetData(searchData);

        gestureDetector =
                new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

    }

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

                        assetAdapter.getSelectedItems();

                        Bundle obj = new Bundle();
                        obj.putSerializable("Selected Assets", (Serializable) assetAdapter.getSelectedItems());
                        Log.i("object","object");
                        intent.putExtra("Selected Assets",obj);
                        intent.setClass(this, Add_incident.class);

                        startActivity(intent);
                        break;
                }
                return true;
            }



            private void addItemToList() {
                AssetListModel model = new AssetListModel();
               // model.label = "New Item " + itemCount;
                itemCount++;

                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                        findFirstVisibleItemPosition();
                // needed to be able to show the animation
                // otherwise the view would be inserted before the first
                // visible item; that is outside of the viewable area
                position++;
                RecyclerViewDemoApp.addItemToList(model, position);
                assetAdapter.addData(model, position);
            }

            private void removeItemFromList() {
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                        findFirstCompletelyVisibleItemPosition();
                RecyclerViewDemoApp.removeItemFromList(position);
                assetAdapter.removeData(position);
            }

            //error in this code, have to add

         /*   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab_add) {
                    // fab click
                    addItemToList();
                } else if (view.getId() == R.id.item_asset_list_view) {
                    // item click
                    int idx = recyclerView.getChildPosition(view);
                    if (actionMode != null) {
                        myToggleSelection(idx);
                        return;
                    }
                    AssetListModel data = assetAdapter.getItem(idx);
                    View innerContainer = view.findViewById(R.id.item_asset_list_view);
                    innerContainer.setTransitionName(SyncStateContract.Constants.NAME_INNER_CONTAINER + "_" + data.id);
                    Intent startIntent = new Intent(this, CardViewDemoActivity.class);
                    startIntent.putExtra(SyncStateContract.Constants.KEY_ID, data.id);
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(this, innerContainer, SyncStateContract.Constants.NAME_INNER_CONTAINER);
                    this.startActivity(startIntent, options.toBundle());
                }
            }*/

            private void myToggleSelection(int idx) {
                assetAdapter.toggleSelection(idx);
              //  String title = getString(R.string.selected_count, adapter.getSelectedItemCount());
              //  actionMode.setTitle(title);
            }

            public void getAssetData(String searchData){

        Map<String, Object> assetDataMap = new HashMap<String, Object>();
        String domainShared = SDUtil.getSession(this, "domain");
        String searchUrl = RestfulEndpoints.SearchRequester.get()+ searchData + "/";
        assetDataMap.put("baseurl", domainShared);
        assetDataMap.put("path", searchUrl);
        RestFulGet restFulGet = new RestFulGet(this, this, "Please Wait", "SearchRequester", true);
        restFulGet.execute(assetDataMap);
    }


            private void addItemToList() {
                AssetListModel model = new AssetListModel();
                model.getDisplay_id() = "New Item " + itemCount;
                itemCount++;

                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).
                        findFirstVisibleItemPosition();
                // needed to be able to show the animation
                // otherwise the view would be inserted before the first
                // visible item; that is outside of the viewable area
                position++;
                RecyclerViewDemoApp.addItemToList(model, position);
                assetAdapter.addData(model, position);
            }


            @Override
    public void onResfulResponse(String result, String responseFor) {
       Gson gson = new Gson();
       if (responseFor.equals("SearchRequester")) {
           response = gson.fromJson(result, SDRequesterSearchResponce.class);
           AssetListModel[] asset_obj = gson.fromJson(gson.toJson(response.getAsset_details()),
                   AssetListModel[].class);
           Resources res = getResources();
           recyclerView = (RecyclerView) findViewById(R.id.recycler_asset_list);

           LinearLayoutManager layoutManager = new LinearLayoutManager(this);
           // actually VERTICAL is the default,
           // just remember: LinearLayoutManager
           // supports HORIZONTAL layout out of the box
           layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
           // you can set the first visible item like this:
           layoutManager.scrollToPosition(0);
           recyclerView.setLayoutManager(layoutManager);


           // allows for optimizations if all items are of the same size:
           recyclerView.setHasFixedSize(true);

           List<AssetListModel> items = RecyclerViewDemoApp.getDemoData();
           assetAdapter = new RecyclerViewDemoAdapter(items);
           recyclerView.setAdapter(assetAdapter);

           RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
           recyclerView.addItemDecoration(itemDecoration);

           // this is the default; this call is actually only necessary with custom ItemAnimators
           recyclerView.setItemAnimator(new DefaultItemAnimator());

           // onClickDetection is done in this Activity's onItemTouchListener
           // with the help of a GestureDetector;
           // Tip by Ian Lake on G+ in a comment to this post:
           // https://plus.google.com/+LucasRocha/posts/37U8GWtYxDE
           recyclerView.addOnItemTouchListener(this);

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

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                gestureDetector.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.menu_cab_recyclerviewdemoactivity, menu);
               // fab.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                this.actionMode = null;
                assetAdapter.clearSelections();
                //fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClick(View v) {

            }


            private class RecyclerViewOnGestureListener extends SimpleOnGestureListener {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    onClick(view);
                    return super.onSingleTapConfirmed(e);
                }

                public void onLongPress(MotionEvent e) {
                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (actionMode != null) {
                        return;
                    }
                    // Start the CAB using the ActionMode.Callback defined above
                    actionMode = startActionMode(Asset_list.this);
                    int idx = recyclerView.getChildPosition(view);
                    myToggleSelection(idx);
                    super.onLongPress(e);
                }
            }


        }
