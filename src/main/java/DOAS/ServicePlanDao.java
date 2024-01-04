package DOAS;

import java.util.List;

import Entities.ServicePlan;

    public interface ServicePlanDao {
        List<ServicePlan> getAllServicePlans();
        // Add other service plan-related methods as needed
    }

