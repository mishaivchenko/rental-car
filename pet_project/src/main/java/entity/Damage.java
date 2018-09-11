package entity;

public class Damage {
    private int id;


    private int orderId;
    private String description;
    private float sum;
    private boolean isPaid;


    public Damage(int id, int orderId, String description, float sum, boolean isPaid) {
        this.id = id;
        this.orderId = orderId;
        this.description = description;
        this.sum = sum;
        this.isPaid = isPaid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", description='" + description + '\'' +
                ", sum=" + sum +
                ", isPaid=" + isPaid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Damage damage = (Damage) o;

        if (id != damage.id) return false;
        if (orderId != damage.orderId) return false;
        if (Float.compare(damage.sum, sum) != 0) return false;
        if (isPaid != damage.isPaid) return false;
        return description != null ? description.equals(damage.description) : damage.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sum != +0.0f ? Float.floatToIntBits(sum) : 0);
        result = 31 * result + (isPaid ? 1 : 0);
        return result;
    }
}
