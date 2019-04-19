/**
 * Daniel Mezhibovski
 * 500899282
 */
import java.text.SimpleDateFormat;
import java.util.*;
public class Transaction  {
    private int id;
    private String salesPerson;
    private GregorianCalendar transDate;
    private Car car;
    private boolean transType;
    private double salesPrice;
    private SimpleDateFormat sdf;
    /**Constructs a transaction object and sets up the date format
     * @param id The identification number of the transaction
     * @param salesPerson The person who conducted the transaction
     * @param transDate The date that the transaction occured
     * @param car The car that was bought or returned 
     * @param transType The type of transaction, either a BUY or a RETURN
     * @param salesPrice The price of the car in the transaction
     */
    public Transaction(int id,String salesPerson,
    GregorianCalendar transDate,Car car, boolean transType,
    double salesPrice){
        this.id=id;
        this.salesPerson=salesPerson;
        this.transDate=transDate;
        this.car=car;
        this.transType=transType;
        this.salesPrice=salesPrice;
        sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
    }
    /**
     * @return The string containing all the information about the transaction
     */
    public String display(){
        return "Id: "+id
        +"\nSalesPerson: "+salesPerson
        +"\nTransaction Date: "+sdf.format(transDate.getTime())
        +"\nCar: "+car.display()
        +"\nTransaction Type:"+getTransType()
        +"\nSales Price $"+salesPrice ;
    }
    /**
     * @return String representing the type of transaction the object is
     */
    public String getTransType(){
        if(transType)
            return "BUY";
        else    
            return "RETURN";
    }
    /**
     * @return Integer representing the Id of the transaction
     */
    public int getId(){
        return id;
    }
    /** 
     * @return Calendar object that has the date of the transaction
     */
    public Calendar getCalendar(){
        return transDate;
    }
    /**
     * @return Car that was bought or returned 
     */
    public Car getCar(){
        return car;
    }
    /**
     * @return The price of the car in the transaction
     */
    public double getSalesPrice(){
        return salesPrice;
    }
    /**
     * @return The sales person who conducted the transaction
     */
    public String getSalesPerson(){
        return salesPerson;
    }
    /**
     * Checks to see if two transaction objects have the same car
     * @param other transaction object that is being checked for equality
     * @return boolean representing whether or not the transactions have the same car
     */
    public boolean equals(Transaction other){
        return this.getCar().equals(other.getCar());

    }


}