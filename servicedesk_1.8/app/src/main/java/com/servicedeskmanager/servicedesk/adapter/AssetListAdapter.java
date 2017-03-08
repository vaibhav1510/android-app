package com.servicedeskmanager.servicedesk.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.servicedeskmanager.servicedesk.R;
import com.servicedeskmanager.servicedesk.model.AssetListModel;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DMX-I-LT-41 on 2/28/2017.
 */

public class AssetListAdapter extends RecyclerView.Adapter<AssetListAdapter.AssetViewHolder> {

    private AssetListModel[] assetList;
    private Activity activity;
    private static LayoutInflater inflater = null;
    private final OnItemClickListener listener;
    private SparseBooleanArray selectedItems;


    public interface OnItemClickListener {
        void onItemClick(AssetListModel item);
    }

    public class AssetViewHolder extends RecyclerView.ViewHolder {

        public TextView display_id, criticality, itemclass, item_type, inventory_id;


        public AssetViewHolder(View vi) {
            super(vi);
            display_id = (TextView) vi.findViewById(R.id.item_id);
            criticality = (TextView) vi.findViewById(R.id.item_criticality);
            // itemclass = (TextView) vi.findViewById(R.id.item_class);
            item_type = (TextView) vi.findViewById(R.id.item_type);
            inventory_id = (TextView) vi.findViewById(R.id.item_inventory_id);

        }

        public void bind(final AssetListModel tempValues, final OnItemClickListener listener) {

            display_id.setText(tempValues.getDisplay_id());

            criticality.setText(tempValues.getCriticality());
            criticality.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(),
                    SDUtil.getCriticalityColor(tempValues.getCriticality())));

           /* itemclass.setText(R.string.item_class_label + tempValues.getItemClass(), TextView.BufferType.SPANNABLE);
            Spannable classSpan = (Spannable) itemclass.getText();
            classSpan.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            itemclass.setText(classSpan);*/

            item_type.setText("Item Type : " + tempValues.getItem_type(), TextView.BufferType.SPANNABLE);
            Spannable itemSpan = (Spannable) item_type.getText();
            itemSpan.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item_type.setText(itemSpan);

            inventory_id.setText("Inventory Id : "+ tempValues.getInventory_id(), TextView.BufferType.SPANNABLE);
            Spannable inventorySpan = (Spannable) inventory_id.getText();
            inventorySpan.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            inventory_id.setText(inventorySpan);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    View view =((RecyclerView)itemView).findChildViewUnder(e.getX(), e.getY());
//                    if (actionMode != null) {
//                        return;
//                    }
//                    actionMode =
//                            startActionMode(RecyclerViewDemoActivity.this);
//                    int idx = recyclerView.getChildPosition(view);
//                    myToggleSelection(idx);
//                    super.onLongPress(e);

                    listener.onItemClick(tempValues);
                }
            });

        }
    }

    public AssetListAdapter(Activity a, AssetListModel[] data, OnItemClickListener listener) {
        this.activity = a;
        this.assetList = data;
        this.listener = listener;
    }

    //To perform the action on select and deselect the item
    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<AssetListModel> getSelectedItems() {
        List<AssetListModel> toReturn = new ArrayList<>();
        List<Integer> items = new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            toReturn.add(assetList[selectedItems.keyAt(i)]);
        }
        return toReturn;
    }

    @Override
    public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.asset_item_list_view, null);
        return new AssetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssetViewHolder holder, int position) {
       System.out.println(position+"position");
        AssetListModel tempValues = assetList[position];

        System.out.println(tempValues+"tempValues");
        holder.bind(tempValues, listener);

    }

    @Override
    public int getItemCount() {
        return assetList == null ? 0 : assetList.length;
    }


}
