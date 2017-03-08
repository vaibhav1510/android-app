package com.servicedeskmanager.servicedesk.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.servicedeskmanager.servicedesk.R;
import com.servicedeskmanager.servicedesk.model.IncidentListModel;
import com.servicedeskmanager.servicedesk.util.SDUtil;

/**
 * Created by DMX-I-LT-41 on 2/28/2017.
 */

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>{

    private IncidentListModel[] incidentList;
    private Activity activity;
    private static LayoutInflater inflater=null;
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(IncidentListModel item);
    }

    public class IncidentViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName, creationDate, inciId, impactService, summary, description,
                priority, resolutionDate, status;
        public RelativeLayout state;

        public IncidentViewHolder(View vi) {
            super(vi);
            customerName = (TextView) vi.findViewById(R.id.customer_name);
            creationDate = (TextView) vi.findViewById(R.id.asign_date);
            inciId=(TextView)vi.findViewById(R.id.incident_id);
            impactService=(TextView)vi.findViewById(R.id.impact_service);
            summary=(TextView)vi.findViewById(R.id.summary);
            description=(TextView)vi.findViewById(R.id.description);
            priority=(TextView)vi.findViewById(R.id.priority);
            resolutionDate=(TextView) vi.findViewById(R.id.sla_date);
            status=(TextView) vi.findViewById(R.id.status);
            state=(RelativeLayout) vi.findViewById(R.id.state);
        }

        public void bind(final IncidentListModel tempValues, final OnItemClickListener listener) {
            customerName.setText( tempValues.getRequester() );
            creationDate.setText( tempValues.getSladata().get("creation_time"));
            inciId.setText( tempValues.getDisplay_id() );
            impactService.setText(tempValues.getService_name());
            summary.setText(tempValues.getSummary());
            description.setText(tempValues.getDescription());
            priority.setText(tempValues.getPriority());
            status.setText(tempValues.getStatus());
            resolutionDate.setText( tempValues.getSladata().get("expected_resolution_time"));
//            status.setBackgroundColor(Color.parseColor(tempValues.getStatus_color()));
            state.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(),
                    SDUtil.getStateColor(tempValues.getState())));
            priority.setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(),
                    SDUtil.getPriorityColor(tempValues.getPriority())));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(tempValues);
                }
            });

        }

    }

    public IncidentAdapter(Activity a, IncidentListModel[] data, OnItemClickListener listener){
        this.activity = a;
        this.incidentList = data;
        this.listener = listener;
    }
    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.incident_list_item_view, null);
        return new IncidentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        IncidentListModel tempValues = incidentList[position];
        holder.bind(tempValues, listener);

    }

    @Override
    public int getItemCount() {
        return incidentList.length;
    }
}
