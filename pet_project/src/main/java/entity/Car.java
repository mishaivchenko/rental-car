package entity;

public class Car {
    private int id;
    private String carName;
    private String model;
    private QualityClass qualityClass;
    private int year;
    private float rentPrice;
    private Status status;
    public Car(int id, String carName, String model, QualityClass qualityClass, int year, float rentPrice,Status status) {
        this.id = id;
        this.carName = carName;
        this.model = model;
        this.qualityClass = qualityClass;
        this.year = year;
        this.rentPrice = rentPrice;
        this.status = status;
    }
    public Car(){

    }

    public enum QualityClass {
        A,B,C,D,E,F,MINIBUS
    }
    public enum Status{
        IN_STOCK,OUT_OF_STOCK
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public QualityClass getQualityClass() {
        return qualityClass;
    }

    public void setQualityClass(QualityClass qualityClass) {
        this.qualityClass = qualityClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (year != car.year) return false;
        if (Float.compare(car.rentPrice, rentPrice) != 0) return false;
        if (carName != null ? !carName.equals(car.carName) : car.carName != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return qualityClass != null ? qualityClass.equals(car.qualityClass) : car.qualityClass == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (carName != null ? carName.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (qualityClass != null ? qualityClass.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (rentPrice != +0.0f ? Float.floatToIntBits(rentPrice) : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", model='" + model + '\'' +
                ", QualityClass='" + qualityClass + '\'' +
                ", year=" + year +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
