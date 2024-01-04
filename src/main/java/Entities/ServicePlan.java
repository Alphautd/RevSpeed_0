package Entities;

    public class ServicePlan {
        private int servicePlanId;
        private String planName;
        private double planPrice;

        private String planDuration;

        private String planData_planSpeed;

        // Getters and setters


        public int getServicePlanId() {
            return servicePlanId;
        }

        public void setServicePlanId(int servicePlanId) {
            this.servicePlanId = servicePlanId;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public double getPlanPrice() {
            return planPrice;
        }

        public void setPlanPrice(double planPrice) {
            this.planPrice = planPrice;
        }

        public String getPlanDuration() {
            return planDuration;
        }

        public void setPlanDuration(String planDuration) {
            this.planDuration = planDuration;
        }

        public String getPlanData_planSpeed() {
            return planData_planSpeed;
        }

        public void setPlanData_planSpeed(String planData_planSpeed) {
            this.planData_planSpeed = planData_planSpeed;
        }
    }
