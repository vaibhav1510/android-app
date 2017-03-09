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

public class SelectedAssetListAdapter extends RecyclerView.Adapter<SelectedAssetListAdapter.SelectAssetViewHolder> {

   // private AssetListModel[] assetList;
   private ArrayList assetList;
    private Activity activity;
    private static LayoutInflater inflater = null;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(AssetListModel item);
    }

    public class SelectAssetViewHolder extends RecyclerView.ViewHolder {

        public TextView display_id, criticality, itemclass, item_type, inventory_id;


        public SelectAssetViewHolder(View vi) {
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
                    listener.onItemClick(tempValues);
                }
            });
        }
    }

    public SelectedAssetListAdapter(Activity a,ArrayList data, OnItemClickListener listener) {
        this.activity = a;
        this.assetList = data;
        this.listener = listener;
    }


    @Override
    public SelectAssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.asset_item_list_view, null);
        return new SelectAssetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelectAssetViewHolder holder, int position) {
        System.out.println(position+"position");
        AssetListModel tempValues = (AssetListModel) assetList.get(position);

        System.out.println(tempValues+"tempValues");
        holder.bind(tempValues, listener);

    }

    @Override
    public int getItemCount() {
        return assetList == null ? 0 : assetList.size();
    }

}