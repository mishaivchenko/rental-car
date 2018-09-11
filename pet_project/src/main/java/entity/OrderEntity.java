package entity;

import java.util.Date;

public class OrderEntity {


    public enum Status {
      ORDERED,ORDER_CONFIRMED,NEGATIVE,ORDER_HAS_BEEN_PAID,ORDER_RETURNED,RETURN_CONFIRMED,RETURN_WITH_CRASH
    }

    private int orderId;
    private int userId;
    private int carId;
    private int damageId;
    private Status status;
    private Date date;
    private int leaseTerm;
    private boolean driver;
    private String customerName;
    private String customerSurname;
    private float sum;
    private String rejectionReason;
    private String carModelYear;

    public OrderEntity(int orderId, int userId, int carId, int damageId, Status status, Date date, int leaseTerm, boolean driver, String customerName, String customerSurname, float sum,String carModelYear) {
        this.orderId = orderId;
        this.userId = userId;
        this.carId = carId;
        this.damageId = damageId;
        this.status = status;
        this.date = date;
        this.leaseTerm = leaseTerm;
        this.driver = driver;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.sum = sum;
        this.carModelYear = carModelYear;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(String carModelYear) {
        this.carModelYear = carModelYear;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(int leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (userId != that.userId) return false;
        if (carId != that.carId) return false;
        if (damageId != that.damageId) return false;
        if (leaseTerm != that.leaseTerm) return false;
        if (driver != that.driver) return false;
        if (Float.compare(that.sum, sum) != 0) return false;
        if (status != that.status) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (customerSurname != null ? !customerSurname.equals(that.customerSurname) : that.customerSurname != null)
            return false;
        if (rejectionReason != null ? !rejectionReason.equals(that.rejectionReason) : that.rejectionReason != null)
            return false;
        return carModelYear != null ? carModelYear.equals(that.carModelYear) : that.carModelYear == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + carId;
        result = 31 * result + damageId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + leaseTerm;
        result = 31 * result + (driver ? 1 : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerSurname != null ? customerSurname.hashCode() : 0);
        result = 31 * result + (sum != +0.0f ? Float.floatToIntBits(sum) : 0);
        result = 31 * result + (rejectionReason != null ? rejectionReason.hashCode() : 0);
        result = 31 * result + (carModelYear != null ? carModelYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", damageId=" + damageId +
                ", status=" + status +
                ", date=" + date +
                ", leaseTerm=" + leaseTerm +
                ", driver=" + driver +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", sum=" + sum +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", carModelYear='" + carModelYear + '\'' +
                '}';
    }
}

