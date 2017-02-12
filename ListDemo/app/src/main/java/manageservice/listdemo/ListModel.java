package manageservice.listdemo;

/**
 * Created by Admin-PC on 2/10/2017.
 */

public class ListModel {

    private  String CustomerName="";
    private  String IncidentId="";
    private  String Summary="";
    private  String Description="";

    /*********** Set Methods ******************/

    public void setCustomerName(String CustomerName)
    {
        this.CustomerName = CustomerName;
    }

    public void setIncidentId(String IncidentId)
    {
        this.IncidentId = IncidentId;
    }

    public void setSummary(String Summary)
    {
        this.Summary = Summary;
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    /*********** Get Methods ****************/

    public String getCustomerName()
    {
        return this.CustomerName;
    }

    public String getIncidentId()
    {
        return this.IncidentId;
    }

    public String getSummary()
    {
        return this.Summary;
    }
    public String getDescription()
    {
        return this.Description;
    }
}