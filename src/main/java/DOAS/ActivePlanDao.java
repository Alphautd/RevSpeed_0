package DOAS;

import java.util.List;

import Entities.ActivePlan;

public interface ActivePlanDao {
    List<ActivePlan> getAllActivePlans(int userId);
    void createActivePlan(ActivePlan activePlan);
    void cancelActivePlan(int activePlanId);
    // Add other active plan-related methods as needed
}
