/**
 * Daniel Mezhibovski
 * 500899282
 */
import java.util.*;

public class SalesTeam{
    LinkedList<SalesPerson> team;
    /** 
     * Creates a sales team object with six salesp people
     */
    public SalesTeam(){
        team=new LinkedList<SalesPerson>();
        team.add(new SalesPerson("Tom"));
        team.add(new SalesPerson("Daniel"));
        team.add(new SalesPerson("Calvin"));
        team.add(new SalesPerson("Jerry"));
        team.add(new SalesPerson("Shawn"));
        team.add(new SalesPerson("Ben"));
    }
    /**
     * @return A random sales person from the team
     */
    public SalesPerson getRandomSalesPerson(){
        return team.get((int)(Math.random()*(team.size()-1)+1));
    }
    /**
     * Prints all the salespeople in the team
     */
    public void printSalesPersons(){
        System.out.println("\n");
        ListIterator iter = team.listIterator();
        while(iter.hasNext()){
            System.out.println(iter.next().toString());
        }
    }
    /**
     * Prints the top sales person by number of sales of the people tied for top
     */
    public void printTopSales(){
        System.out.println("\n");
        ListIterator iter = team.listIterator();
        int maxSales=0;
        SalesPerson sp;
        while(iter.hasNext()){
            sp=(SalesPerson)iter.next();
            int sales=sp.getSales();
            if(sales>maxSales){
                maxSales=sales;
            }
        }
        String out="Top SalesPerson(s): ";
        iter = team.listIterator();
        while(iter.hasNext()){
            sp=(SalesPerson)iter.next();
            if(sp.getSales()==maxSales){
                out+=sp.toString()+" | ";
            }
        }
        System.out.println(out+"Sales: "+maxSales);

    }
    /** 
     * @return the team size
     */
    public int getTeamSize(){
        return team.size()-1;
    }
    /** 
     * @param i the index of the sales person that is being looked for
     * @return gets the name of a specified salesperson on a team  
     */
    public String getSalesPerson(int i){
        return team.get(i).toString();
    }
    
}