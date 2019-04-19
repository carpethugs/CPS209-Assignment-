/**
 * Daniel Mezhibovski
 * 500899282
 */
public class ElectricCar extends Car
{
    private int rechargeTime;
    private String batteryType;
    /**
     * Constructs Electric Car object with
     * @param manufactur
     * @param color
     * @param model
     * @param power
     * @param rating
     * @param maxRange
     * @param allWheelDrive
     * @param price
     * @param time
     */
    public ElectricCar(String manufacture,String color,String model,int power,double rating,int maxRange,boolean allWheelDrive,double price,int time){
        super(manufacture,color,model,power,rating,maxRange,allWheelDrive,price);
        rechargeTime=time;
        batteryType="Lithium";//Default battery type is lithium 
    }
    /**
     * @return string with all the electric cars attributes
     */
    public String display(){
        return super.display()+" EL, BAT: "+batteryType+" RCH: "+rechargeTime;
    }
    public int getRechargeTime(){//Returns recharge time
        return rechargeTime;
    }
    public String getBatteryType(){//Returns battery type
        return batteryType;
    }
    public void setRechargeTime(int time){//Sets recharge time
        rechargeTime=time;
    }
    public void setBatteryType(String type){//Sets battery type
        batteryType=type;
    }
}