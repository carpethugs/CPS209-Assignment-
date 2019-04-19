/**
 * Daniel Mezhibovski
 * 500899282
 */
import java.util.*;
import java.text.*;

public class AccountingSystem{
    static ArrayList<Integer> transIds=new ArrayList<Integer>();
    HashMap<Integer,Transaction> list;//List connecting transactions to their identification numbers
    HashMap<Integer,Integer> buyRet;//List connecting buy and return transactions
    DecimalFormat df = new DecimalFormat(".##");
    /**
     * Creates an accounting system object
     */
    public AccountingSystem(){
        list=new HashMap<Integer,Transaction>();
        buyRet=new HashMap<Integer,Integer>();
    }
    /**
     * Adds transaction object to the accounting system, and generates a random transaction date
     * @param date Date of the transaction
     * @param car Car bought/sold
     * @param salesPerson The person who conducted the transaction 
     * @param transType The type of transaction, either a BUY or a RETURN
     * @param salsalesPrice The price of the car in the transaction
     * @return String containing the information about the transaction
     */
    public  String add(int transaction,Calendar date, Car car, String salesPerson, String type, double salePrice){
        Random rand=new Random();
        int id=Math.abs(rand.nextInt(98))+1;
        while(transIds.contains(id)){
            id=Math.abs(rand.nextInt(98))+1;
        }
        transIds.add(id);
        boolean saleType;
        if(type.equals("BUY"))
            saleType=true;
        else
            saleType=false;
        if (date instanceof GregorianCalendar) {
            GregorianCalendar gregDate = (GregorianCalendar) date;
            Transaction trans=new Transaction(id,salesPerson,gregDate,car,saleType,salePrice);
            list.put(id,trans);
            if(!saleType)
                buyRet.put(transaction,id);
            return trans.display();
        }
        else
            return "Improper Date Format";
       
    }
    /**Returns transaction from the accounting system based on the given id
     * @param id The id of the transaction that is being looked for
     * @return Transaction object that is being looked for
     */
    public Transaction getTransaction(int id){
        for(int i : list.keySet()){
            if(list.get(i).getId()==id)
                return list.get(i);
        }
        return null;
    }
    /** 
     * Prints all the transactions that year 
     */
    public void getSales(){
        for(int i : list.keySet()){
            System.out.println("\n"+list.get(i).display());
        }
    }
    /** Displays all transactions of a given month
     *@param m The month that is being displayed 
     */
    public void getMonthSales(int m){
        for(int i : list.keySet()){
            if(list.get(i).getCalendar().get(Calendar.MONTH)==m)
                System.out.println("\n"+list.get(i).display());
        }
    }
    /** 
     * Displays the total value of all sales, the average sales for a month, the total cars sold, the top sales month and the total returns
     */
    public void getSalesStats(){
        double totalSales=0;
        int totalCars=0;
        int totalReturns=0;
        HashMap<Integer,Integer> months=new HashMap<Integer,Integer>();//Key is the month, value is the sales
        for(int i:list.keySet()){
            Transaction trans=list.get(i);
            int month=trans.getCalendar().get(Calendar.MONTH);
            if(trans.getTransType().equals("BUY")){
                totalSales+=trans.getSalesPrice();
                totalCars++;
                if(!months.containsKey(month)){
                    months.put(month,1);
                }
                else{
                    months.put(month,months.get(month)+1);
                }
            }
            else{
                totalReturns++;
                totalSales-=trans.getSalesPrice();
            }
        }
        System.out.println("Total Sales: $"+totalSales
        +" Average Month Sales: $"+df.format(totalSales/12)
        +" Total Cars Sold: "+totalCars
        +" Top Sales Month: "+ maxMonth(months)
        +" Total Returns: "+totalReturns);
    }
    /** 
     * @return The size of the list of transactions
     */
    public int getListSize(){
        return list.size();
    }
    /**
     * @param map A hashmap containing months and the amount of sales for that month
     * @return an interger representing the month with the most sales
     */
    public String maxMonth(HashMap<Integer,Integer> map){
        int max=0;
        String maxMonth="";
        if(map.isEmpty())
            return "None";
        for(int i : map.keySet()){
            if(map.get(i)>max){
                max=map.get(i);
            }
        }
        for(int i : map.keySet()){
            if(map.get(i)==max){
                maxMonth+=i+", ";
            }
        }
        return maxMonth.substring(0,maxMonth.length()-2);
    }
    /**
     * Checks to see if a return transaction has been made for a buy
     * @param trans the buy transaction
     * @return either -1 that represents that the transaction already has a return mapped to it or it just passes the id of
     * the buy forwards
     */
    public int checkBuyToRet(Transaction trans){
        if(buyRet.containsKey(trans.getId())){
            System.out.println("\nCar has already been returned");
            return -1;
        }
        else if(trans.getTransType().equals("RETURN")){
            System.out.println("\nNot a buy transaction");
            return -1;
        }
        return trans.getId();
    }
}