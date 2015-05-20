
/**
 * This is the car class.
 * It is important while parsing the data from the xml files.
 * It contains method for setting car attributes and reading the attributes*/
public class Car {

    private String station;
    private String carPlate;
    private String carType;
    private String timePassed;
    private String speed;

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getStation() {
        return station;
    }

    public String getPlate() {

        return getCarPlate();
    }

    public String getCarType() {
        return carType;

    }

    public String getTimePassed() {
        return timePassed;
    }

    public String getSpeed() {
        return speed;
    }

    /**
     * @param carType the carType to set
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * @param timePassed the timePassed to set
     */
    public void setTimePassed(String timePassed) {
        this.timePassed = timePassed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
