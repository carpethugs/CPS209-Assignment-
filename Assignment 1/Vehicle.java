/**
 * Daniel Mezhibovski
 * 500899282
 */

import java.util.*;
public class Vehicle
{
    private String mfr,color;
    private int power,numWheels;
    public final int ELECRIC_MOTOR=0,GAS_ENGINE=1;
    private int VIN;
    private static ArrayList<Integer> VINS=new ArrayList<Integer>();

    /**
     * Constructor for a vehicle that takes and also creates a random vehicle idendification number
     * @param manufacture
     * @param color
     * @param power
     */
    public Vehicle(String manufacture,String color,int power){
        numWheels=4;//Sets default number of wheels to four
        mfr=manufacture;
        this.color=color;
        this.power=power;
        Random rand = new Random();
        VIN=rand.nextInt(499);
        while(VIN<100||VINS.contains(VIN)){
            VIN= rand.nextInt(499);
        }
        VINS.add(VIN);
    }
    /**
     * @return String containing inforamtion about the vehicle
     */
    public String display(){
        return "VIN: "+VIN+" "+mfr+" "+color;
    }
    /**
     * @return Manufacturer of vehicle
     */
    public String getMfr(){
        return mfr;
    }
    /**
     * @return Color of vehicle
     */
    public String getColor(){
        return color;
    }
    /**
     * @return Integer representing the engine type of the vehicle
     */
    public int getPower(){
        return power;
    }
    /**
     * @return Number of wheels of the vehicle
     */
    public int getNumWheels(){
        return numWheels;
    }
    public int getVIN(){
        return VIN;
    }
    /**
     * Sets the manufacturer of the vehicle 
     * @param mfr New manufacurer of the vehicle
     */
    public void setMfr(String mfr){
        this.mfr=mfr;
    }
    /**
     * Sets the color of the vehicle
     * @param color New color of the vehicle
     */
    public void setColor(String color){
        this.color=color;
    }
    /**
     * Sets the engine type of the vehicle
     * @param power New engine type 
     */
    public void setPower(int power){
        this.power=power;
    }
    /**
     * Sets the number of wheels of the vehicle
     * @param numWheels The new number of wheels
     */
    public void setNumWheels(int numWheels){
        this.numWheels=numWheels;
    }
    /**
     * Compares two vehicle objects by their manufacturer, power and number of wheels
     * @param otherVehicle Another vehicle that will be checked for equality
     * @return A boolean describing whether or not the vehicles are the same
     */
    public boolean equals(Vehicle otherVehicle){
        if(VIN==otherVehicle.getVIN()&&power==otherVehicle.getPower()&&mfr==otherVehicle.getMfr()&&numWheels==otherVehicle.getNumWheels())
            return true; 
        return false;
    }

}