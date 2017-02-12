package manageservice.listdemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class ListAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    ListModel tempValues=null;
    int i=0;

    /*************  ListAdapter Constructor *****************/
    public ListAdapter(Activity a, ArrayList d,Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
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
        public TextView inciId;
        public TextView summary;
        public TextView description;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate list_item.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_item, null);

            /****** View Holder Object to contain list_item.xml file elements ******/

            holder = new ViewHolder();
            holder.customername = (TextView) vi.findViewById(R.id.customer_name);
            holder.inciId=(TextView)vi.findViewById(R.id.incident_id);
            holder.summary=(TextView)vi.findViewById(R.id.summary);
            holder.description=(TextView)vi.findViewById(R.id.description);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.customername.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ListModel ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.customername.setText( tempValues.getCustomerName() );
            holder.inciId.setText( tempValues.getIncidentId() );
            holder.summary.setText(tempValues.getSummary());
            holder.summary.setText(tempValues.getDescription());
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("ListAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {


            MainActivity sct = (MainActivity)activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(mPosition);
        }
    }
}