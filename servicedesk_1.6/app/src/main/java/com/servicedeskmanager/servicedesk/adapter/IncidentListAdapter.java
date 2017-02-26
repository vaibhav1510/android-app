package com.servicedeskmanager.servicedesk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.servicedeskmanager.servicedesk.R;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;


/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class IncidentListAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private IncidentListModel[] data;
    private static LayoutInflater inflater=null;
    public Resources res;
    IncidentListModel tempValues=null;
    int i=0;

    /*************  ListAdapter Constructor *****************/
    public IncidentListAdapter(Activity a, IncidentListModel[] d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

          }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data==null || data.length<=0)
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

        public TextView customername;
        public TextView asigndate;
        public TextView inciId;
        public TextView impact;
        public TextView summary;
        public TextView description;
        public TextView priority;
        public TextView sladate;
        public TextView status;
        public RelativeLayout state;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){
            /****** Inflate list_item.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.incident_list_item_view, null);

            /****** View Holder Object to contain list_item.xml file elements ******/

            holder = new ViewHolder();
            holder.customername = (TextView) vi.findViewById(R.id.customer_name);
            holder.asigndate = (TextView) vi.findViewById(R.id.asign_date);
            holder.inciId=(TextView)vi.findViewById(R.id.incident_id);
            holder.impact=(TextView)vi.findViewById(R.id.impact_service);
            holder.summary=(TextView)vi.findViewById(R.id.summary);
            holder.description=(TextView)vi.findViewById(R.id.description);
            holder.priority=(TextView)vi.findViewById(R.id.priority);
            holder.sladate=(TextView) vi.findViewById(R.id.sla_date);
            holder.status=(TextView) vi.findViewById(R.id.status);

            holder.state=(RelativeLayout) vi.findViewById(R.id.state);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data==null || data.length<=0)
        {
            holder.customername.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = data[position];

            /************  Set Model values in Holder elements ***********/
//
            holder.customername.setText( tempValues.getRequester() );
            holder.asigndate.setText( tempValues.getSladata().get("creation_time"));
            holder.inciId.setText( tempValues.getDisplay_id() );
            holder.impact.setText(tempValues.getService_name());
            holder.summary.setText(tempValues.getSummary());
            holder.description.setText(tempValues.getDescription());
            holder.priority.setText(tempValues.getPriority());
            holder.status.setText(tempValues.getStatus());
            holder.sladate.setText( tempValues.getSladata().get("creation_time"));
            holder.status.setBackgroundColor(Color.parseColor(tempValues.getStatus_color()));
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