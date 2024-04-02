package testClasses;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdvisementPlanList 
{
    private static AdvisementPlanList advisementPlanList;
    private HashMap<UUID, AdvisementPlan> advisementPlans;

    private AdvisementPlanList() 
    {
        advisementPlans = new HashMap<UUID, AdvisementPlan>();
        ArrayList<AdvisementPlan> temp = new ArrayList<>();
        temp = ReadFile.readAdvisePlans();
        for(AdvisementPlan plan : temp)
        {
            advisementPlans.put(plan.getPlanID(), plan);
        }
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

    public ArrayList<AdvisementPlan> getAllList()
    {
        ArrayList<AdvisementPlan> temp = new ArrayList<AdvisementPlan>();
        for(Map.Entry<UUID, AdvisementPlan> entry : advisementPlans.entrySet())
        {
            temp.add(entry.getValue());
        }
        return temp;
    }

    public boolean AddPlan(AdvisementPlan newPlan)
    {
        return true;
    }
}
