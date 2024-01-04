package DOASImplemtationLayer;

import DOAS.ServicePlanDao;
import Entities.ServicePlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePlanDaoImpl implements ServicePlanDao {
    @Override
    public List<ServicePlan> getAllServicePlans() {
        String query = "SELECT * FROM s1";
        List<ServicePlan> servicePlans = new ArrayList<>();

        try (Connection connection = DatabaseConnection.dbConnection().getConn();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ServicePlan servicePlan = new ServicePlan();
                servicePlan.setServicePlanId(resultSet.getInt("s1_id"));
                servicePlan.setPlanName(resultSet.getString("plan_Name"));
                servicePlan.setPlanPrice(resultSet.getDouble("planPrice"));
                servicePlan.setPlanDuration(resultSet.getString("planDuration"));
                servicePlan.setPlanData_planSpeed(resultSet.getString("planData_planSpeed"));

                servicePlans.add(servicePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        return servicePlans;
    }
}

