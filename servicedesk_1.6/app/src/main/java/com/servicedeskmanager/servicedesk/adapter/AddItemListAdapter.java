package com.servicedeskmanager.servicedesk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.servicedeskmanager.servicedesk.R;
import com.servicedeskmanager.servicedesk.model.AddIncidentModel;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;
import com.servicedeskmanager.servicedesk.model.ItemListModel;

/**
 * Created by Admin-PC on 2/24/2017.
 */

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AddItemListAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ItemListModel[] data;
    private static LayoutInflater inflater=null;
    public Resources res;
    ItemListModel tempValues=null;
    int i=0;

    /*************  ListAdapter Constructor *****************/
    public AddItemListAdapter(Activity a,  ItemListModel[] d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.length<=0)
            return 1;
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView display_id;
        public TextView criticality;
        public TextView itemclass;   //json name class
        public TextView item_type;
        public TextView inventory_id;

        public TextView barCode;    //json name not found
        public TextView qrCode;     //json name not found



    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;


        if(convertView==null){
            /****** Inflate list_item.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.add_item_list_view, null);

            /****** View Holder Object to contain list_item.xml file elements ******/

            holder = new ViewHolder();
            holder.display_id=(TextView)vi.findViewById(R.id.item_id);
            holder.criticality=(TextView) vi.findViewById(R.id.item_criticality);
            holder.itemclass=(TextView) vi.findViewById(R.id.item_class);
            holder.item_type=(TextView) vi.findViewById(R.id.item_type);
            holder.inventory_id=(TextView) vi.findViewById(R.id.item_inventory_id);
            holder.barCode=(TextView) vi.findViewById(R.id.item_barcode);
            holder.qrCode=(TextView) vi.findViewById(R.id.item_qrcode);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.length<=0)
        {
            holder.display_id.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = data[position];

            /************  Set Model values in Holder elements ***********/

//          Spannable s1 = tempValues.getItemClass();
            Spannable s1 = new SpannableStringBuilder(tempValues.getItemClass());

            holder.display_id.setText( tempValues.getItemId() );
            holder.criticality.setText( tempValues.getPriority());


            holder.itemclass.setText( s1 );

            holder.item_type.setText(tempValues.getItemType());
            holder.inventory_id.setText(tempValues.getInventoryId());
            holder.barCode.setText(tempValues.getBarCode());
            holder.qrCode.setText(tempValues.getQrCode());

        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("ListAdapter", "=====Row button clicked=====");
        // Toast.makeText(activity, "Edit page", Toast.LENGTH_LONG).show();
    }
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            com.servicedeskmanager.servicedesk.HomeActivity sct = (com.servicedeskmanager.servicedesk.HomeActivity) activity;

            /****  Call  onItemClick Method inside HomeActivity Class ( See Below )****/

            sct.onItemClick(mPosition);

        }
    }

}