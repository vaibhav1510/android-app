package com.servicedeskmanager.servicedesk;

/**
 * Created by Admin-PC on 2/13/2017.
 */

public class IncidentListModel {
    private  String CustomerName="";
    private  String AsignDate="";
    private  String IncidentId="";
    private  String Impect="";
    private  String Summary="";
    private  String Description="";
    private  String Priority="";
    private  String SlaDate="";

    /*********** Set Methods ******************/

    public void setCustomerName(String CustomerName)
    {
        this.CustomerName = CustomerName;
    }

    public void setAsignDate(String AsignDate){this.AsignDate = AsignDate;}

    public void setIncidentId(String IncidentId)
    {
        this.IncidentId = IncidentId;
    }

    public void setImpect(String Impect)
    {
        this.Impect = Impect;
    }

    public void setSummary(String Summary)
    {
        this.Summary = Summary;
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    public void setPriority(String Priority) { this.Priority = Priority; }

    public void setSlaDate(String SlaDate) { this.SlaDate = SlaDate; }

    /*********** Get Methods ****************/

    public String getCustomerName()
    {
        return this.CustomerName;
    }

    public String getAsignDate()
    {
        return this.AsignDate;
    }

    public String getIncidentId()
    {
        return this.IncidentId;
    }

    public String getImpect()
    {
        return this.Impect;
    }

    public String getSummary()
    {
        return this.Summary;
    }

    public String getDescription()
    {
        return this.Description;
    }

    public  String getPriority(){ return  this.Priority; }

    public  String getSlaDate(){ return  this.SlaDate; }
}
