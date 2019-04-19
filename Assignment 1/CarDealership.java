/**
 * Daniel Mezhibovski
 * 500899282
 */
import java.util.*;

public class CarDealership
{
    ArrayList<Car> cars;
    AccountingSystem ac=new AccountingSystem();
    SalesTeam team; 
    private boolean AWD;
    private boolean electric; 
    private boolean price;
    private double minPrice,maxPrice;
    
    //Constructs Dealership, which initialized a list of cars and new sales team
    public CarDealership(){
        cars=new ArrayList<Car>();
        team=new SalesTeam();
    }
    /**
     * Adds cars to the dealership
     * @param newCars List of cars to add to the store
     */
    public void addCars(ArrayList<Car> newCars){
        for(int i =0;i<newCars.size();i++){
            cars.add(newCars.get(i));
        }
    }
    /**
     * Removes a car at a given VIN to "buy" it
     * @param index Which car in the list to buy
     * @return The car object that was bought
     */
    public Car buyCar(int VIN){
        for(int i=0;i<cars.size();i++){
            if(cars.get(i).getVIN()==VIN){
                SalesPerson salesPerson=team.getRandomSalesPerson();
                salesPerson.makeSale();
                GregorianCalendar buyDate=makeRandomDate();
                
                System.out.println("\n"+ac.add(0,buyDate,cars.get(i),salesPerson.toString(),"BUY",cars.get(i).getPrice()));
                return cars.remove(i);
            }             
       }
       System.out.println("\nNo Car Found");
       return null;
    }
    /**
     * Adds a specified car to the store to "return" it and adds a return transaction to the accounting system
     * @param transaction The transaction number of the purchase
     * @return boolean representing whether or not the return was successful
     */
    public boolean returnCar(int transaction){
        Transaction buyT =ac.getTransaction(transaction);
        if(buyT==null){
            System.out.println("\nNo Transaction with that ID");
            return false;
        }
        else if(ac.checkBuyToRet(buyT)<0){
            return false;
        }
        GregorianCalendar retDate=makeRandomDate(buyT.getCalendar().get(Calendar.MONTH),buyT.getCalendar().get(Calendar.DAY_OF_MONTH));
        SalesPerson salesPerson=team.getRandomSalesPerson();
        cars.add(buyT.getCar());
        System.out.println("\n"+ac.add(transaction,retDate, buyT.getCar(), salesPerson.toString(), "RET", buyT.getSalesPrice()));
        return true;
    }
    /**
     *  Prints car list to terminal
     *  Checks filters while printing to see if anything should be skipped
     */
    public void displayInventory(){
        System.out.print("\n");
        for(int i=0;i<cars.size();i++){
            if((electric && !(cars.get(i) instanceof ElectricCar))
            ||(AWD && !cars.get(i).getAWD())
            ||(price&&(cars.get(i).getPrice()>maxPrice||cars.get(i).getPrice()<minPrice)))
                continue;           
            System.out.println(cars.get(i).display());
        }
    }
    /**
     *Turns on the electric filter 
     */
    public void filterByElectric(){
        electric=true;
    }
    /**
     * Turns on the All Wheel Drive filter
     */
    public void filterByAWD(){
        AWD=true;
    }
    /**
     * Turns on the price filter
     * @param minPrice the minimum price to filter by
     * @param maxPrice the maximum proce to filter by
     */
    public void filterByPrice(double minPrice,double maxPrice){
        price=true;
        this.minPrice=minPrice;
        this.maxPrice=maxPrice;
    }
    /**
     * Turns off all the filters
     */
    public void FiltersClear(){
        electric=false;
        AWD=false;
        price=false;
    }
    /**
     * Sorts cars list by price
     */
    public void sortByPrice(){
        Collections.sort(cars);
    } 
    /**
     * Sorts car list by rating
     */
    public void sortByRating(){
        Collections.sort(cars,new ratingSorter());
    }
    /**
     * Sorts car list by range
     */
    public void sortByRange(){
        Collections.sort(cars, new rangeSorter());
    }
    /**
     * Creates a rating sorting subclass that implements the Comparator interface
     * Adds the compare method which compares two cars by their maximum range
     */
    public class ratingSorter implements Comparator<Car>{
        /**
         * Compares two cars by their safety rating
         * @param car1 First car
         * @param car2 Second car
         * @return Integer representing which car has a greater rating, positive for the first, negative for the second and 0 for equality
         */
        public int compare(Car car1, Car car2){
            double rating1=car1.getRating();
            double rating2=car2.getRating();
            if(rating1-rating2>0)
                return 1;
            else if(rating1-rating2<0)
                return -1;
            else
                return 0;
        }
    }
    /** 
     * @return Gregorian Calendar object with a random date in 2019
     */
    public GregorianCalendar makeRandomDate(){
        int m=(int)(Math.random()*12);
        while(m>=12){
            m=(int)(Math.random()*12);
        }
        int d;
        if(m==0||m==2||m==4||m==6||m==7||m==9||m==11)
            d=(int)(Math.random()*31+1);
        else if(m==3||m==5||m==8||m==10)
            d=(int)(Math.random()*30+1);
        else
            d=(int)(Math.random()*28+1);
        return new GregorianCalendar(2019,m,d);
    }
    /**
     * @param bm Buy month
     * @param bd Buy day
     * @return Gregorian Calendar object that has a date in the same month but a later day than a BUY transaction
     */
    public GregorianCalendar makeRandomDate(int bm, int bd){
        int d=0;
        while(d<bd){
            if(bm==0||bm==2||bm==4||bm==6||bm==7||bm==9||bm==11)
                d=(int)(Math.random()*31+1);
            else if(bm==3||bm==5||bm==8||bm==10)
                d=(int)(Math.random()*30+1);
            else
                d=(int)(Math.random()*28+1);
        }
        return new GregorianCalendar(2019,bm,d);
    }
    /**
     *Creates a range sorting subclass that implements the Comparator interface
     *Adds the compare method which compares two cars by their safety rating
     */
    public class rangeSorter implements Comparator<Car>{
        /**
         * Compares two cars by their maximum range
         * @param car1 First car
         * @param car2 Second car
         * @return Integer representing which car has a greater range, positive for the first, negative for the second and 0 for equality
         */
        public int compare(Car car1, Car car2){
            return car1.getRange()-car2.getRange(); 
        }
    } 
}
