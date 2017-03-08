package com.servicedeskmanager.servicedesk.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.util.Log;

import com.servicedeskmanager.servicedesk.R;
import com.servicedeskmanager.servicedesk.model.AssetListModel;
import com.servicedeskmanager.servicedesk.util.SDUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by cb-vaibhav on 06/03/17.
 */

public class AssetListAdapter extends RecyclerView.Adapter<AssetListAdapter.AssetViewHolder> {

    private AssetListModel[] assetList;
    private Activity activity;
    private static LayoutInflater inflater = null;
    private final OnItemClickListener listener;
    int count=0;

    public interface OnItemClickListener {
        void onItemClick(AssetListModel item);
    }

    public class AssetViewHolder extends RecyclerView.ViewHolder {

        public TextView display_id, criticality, itemclass, item_type, inventory_id, barCode, qrCode;

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

            inventory_id.setText("Inventory Id : " + tempValues.getInventory_id(), TextView.BufferType.SPANNABLE);
            Spannable inventorySpan = (Spannable) inventory_id.getText();
            inventorySpan.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            inventory_id.setText(inventorySpan);

            //tempValues.view.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
            //itemView..getItemSelectionIndicator().setSelected(position == mSelectedPosition);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tempValues);
                    tempValues.setSelected(!tempValues.isSelected());
                    if(tempValues.isSelected()){
                        count++;
                        v.setBackgroundResource(R.drawable.ic_action_tick);}
                    else
                        v.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }

    public AssetListAdapter(Activity a, AssetListModel[] data, OnItemClickListener listener) {
        this.activity = a;
        this.assetList = data;
        this.listener = listener;
    }

     /* public AssetListModel[] getSelectedItems(){
        AssetListModel aDoc = AssetListModel.get(position);
      // AssetListModel[]  selectedItems= new AssetListModel[];

      public ArrayList<Item> getSelectedItems() {
            ArrayList<Item> selectedItems = new ArrayList<>();
            for( Item item : items ) {
                if( item.isSelect() )
                    selectedItems.add(item);
            }
            return selectedItems;
        }
    }*/


    @Override
    public AssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.asset_item_list_view, null);
        return new AssetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssetViewHolder holder, int position) {
        AssetListModel tempValues = assetList[position];
        holder.bind(tempValues, listener);
        holder.itemView.setSelected(tempValues.isSelected());
    }

    @Override
    public int getItemCount() {
        // AssetListModel aDoc=(AssetListModel)l.getItemAtPosition(position);
        return assetList == null ? 0 : assetList.length;

    }


}
