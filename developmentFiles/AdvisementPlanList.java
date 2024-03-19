import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AdvisementPlanList 
{
    private static AdvisementPlanList advisementPlanList;
    private HashMap<UUID, AdvisementPlan> advisementPlan;

    private AdvisementPlanList() 
    {
        advisementPlan = new HashMap<UUID, AdvisementPlan>();
    }

    public static AdvisementPlanList getInstance()
    {
        if (advisementPlanList == null)
        {
            System.out.println("Making a new advisement plan list");
            advisementPlanList = new AdvisementPlanList();
        }
        return advisementPlanList;
    }

    public ArrayList<AdvisementPlanList> getAllList()
    {
        return null;
    }

    public boolean AddList(AdvisementPlan newPlan)
    {
        return true;
    }
}
