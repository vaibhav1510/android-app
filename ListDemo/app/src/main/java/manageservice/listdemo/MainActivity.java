package manageservice.listdemo;

import android.app.Activity;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class  MainActivity extends Activity {

    ListView list;
    ListAdapter adapter;
    public  MainActivity CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new ListAdapter(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter( adapter );

    }

    /****** Function to set data in ArrayList *************/
    public void setListData()
    {

        for (int i = 0; i < 11; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setCustomerName("Customer Name "+i);
            sched.setIncidentId("Incident Id"+i);
            sched.setSummary("Summary"+i);
            sched.setDescription(""+i);
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView, "Customername:"+tempValues.getCustomerName() +" IncidentId:"+tempValues.getIncidentId() +" Summary:"+tempValues.getSummary() + " Description:"+tempValues.getDescription(), Toast.LENGTH_LONG).show();
    }
}