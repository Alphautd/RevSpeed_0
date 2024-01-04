package Services;

import DOAS.ServicePlanDao;
import DOASImplemtationLayer.ServicePlanDaoImpl;

import java.util.List;

public class ServicePlan implements ServicePlanDao {
    ServicePlanDaoImpl refServiceImpl=new ServicePlanDaoImpl();
    @Override
    public List<Entities.ServicePlan> getAllServicePlans() {
        List<Entities.ServicePlan> ls=refServiceImpl.getAllServicePlans();
        return ls;
    }
}
