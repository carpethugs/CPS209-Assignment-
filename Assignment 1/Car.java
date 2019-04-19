/**
 * Daniel Mezhibovski
 * 500899282
 */
public class Car extends Vehicle implements Comparable<Car>
{
    private String model;
    private int maxRange;
    private double safetyRating,price;
    private boolean AWD;
    /**
     * Constructs Car object with
     * @param manufactur
     * @param color
     * @param model
     * @param power
     * @param rating
     * @param maxRange
     * @param allWheelDrive
     * @param price
     */
    public Car(String manufacture,String color,String model,int power,double rating,int maxRange,boolean allWheelDrive,double price){
        super(manufacture,color,power);
        this.model=model;
        this.maxRange=maxRange;
        safetyRating=rating;
        this.price=price;
        AWD=allWheelDrive;
    }
    /**
     * Compares two car objects by through their inhereted vehicle atributes and the model and the awd
     * @param otherCar Car object that will be compared to for equality
     * @return boolean representing whether or not the two cars are equal
     */
    public boolean equals(Car otherCar){
        if(super.equals(otherCar)){
            if(model==otherCar.getModel() && AWD==otherCar.getAWD()){
                return true;
            }
        }
        return false;
    }
    /**
     * Compares two car objects by their price
     * @param other Car that will be compared
     * @return interger that is negative if the car is cheaper, 0 if the same price, and positive if more expensive that the other car
     */
    public int compareTo(Car other){
        return Double.compare(price, other.getPrice());
    }
    /**
     * @return String containing the cars information
     */
    public String display(){
        return super.display()+" "+model+" "+price+"$ SF: "+safetyRating+" RNG: "+ maxRange;
    }
    /**
     * @return model of the car
     */
    public String getModel(){
        return model;
    }
    /**
     * @return boolean representing whether or not the car is all wheel drive
     */
    public boolean getAWD(){
        return AWD;
    }
    /**
     * @return the price of the car
     */
    public double getPrice(){
        return price;
    }
    public double getRating(){//Returns safetyRating
        return safetyRating;
    }
    public int getRange(){//Returns maxRange
        return maxRange;
    }
   
}
