/**
 * Daniel Mezhibovski
 * 500899282
 */
public class SalesPerson{
    String name;
    int sales;
    /** Creates a sales person object
     * @param name of the sales person
     */
    public SalesPerson(String name){
        this.name=name;
        sales=0;
    }
    /** 
     * Increases the number of sales of the sales person
     */
    public void makeSale(){
        sales++;
    }
    /**
     * @return the number of sales that the salesperson has made
     */
    public int getSales(){
        return sales;
    }
    /**
     * @return string with the name of the sales person
     */
    public String toString(){
        return name;
    }
}