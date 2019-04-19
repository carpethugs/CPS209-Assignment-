/**
 * Daniel Mezhibovski
 * 500899282
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CarDealershipSimulator 
{
private static ArrayList<Car> cars=new ArrayList<Car>();
private static String helpText=
	"\nL: Lists all cars in the store."+
	"\nQ: Exits Program"+
	"\nADD: Adds list of cars to the store."+
	"\nBUY *VIN Number*: Buys Car from store."+
	"\nRET *Transaction Number*: Returns recently bought car."+
	"\nSPR: Sorts cars by price."+
	"\nSSF: Sorts cars by safety rating."+
	"\nSMR: Sorts cars by maximum range."+
	"\nFEL: Filters out all non-electric cars."+
	"\nFAW: Filters out all non-All Wheel Drive cars."+
	"\nFPR *min price* *max price*: Filters out all cars outside the given range."+
	"\nFCL: Clears all active filters.\nHELP: Shows all commands."+
	"\nSALES: Prints all sales conducted."+
	"\nSALES TEAM: Prints the names of all the sales people."+
	"\nSALES TOPSP: Prints the name of the sales person(s) with the highest sales."+
	"\nSALES *Month*: Prints all sales conducted in given month 'm'."+
	"\nSALES STATS: Prints all information about overall sales statistics.";
  public static void main(String[] args) throws FileNotFoundException
  {	
		CarDealership store=new CarDealership();//Creates CarDealership object
		Car lastCar=null;//Creates variable to hold the previous car bought	  
		ArrayList<String> l=readInput("cars.txt");//Reads file into arraylist line by line
		makeCars(l);//Makes a list of car objects from the inputed list
		System.out.println(helpText+"\n\nInput Below:\n");
		Scanner in = new Scanner(System.in);
		String inLine;
		Scanner commandLine;
		String word;
		while(in.hasNextLine()){
			inLine=in.nextLine().toUpperCase();
			String alphaLine=inLine.replaceAll("[^a-zA-Z0-9]", "");
			commandLine=new Scanner(inLine);
			if(commandLine.hasNext())
				 word= commandLine.next().toUpperCase();
			else
				continue;
			if(word.equals("L"))//Displays the list of cars
				store.displayInventory();
			else if(word.equals("Q"))//Exits Program
				return;
			else if(word.equals("BUY")){//Buys a car
				if(!commandLine.hasNext()){
					System.out.println("\nNo VIN Provided\n");
					in = new Scanner(System.in);
					continue;
				}
				int buyNum=checkInt(commandLine.next());//Check to see if input is an integer
				if(buyNum<100||buyNum>499)//Check to see if input is in the list of cars
					System.out.println("\nNo Car Found");
				else
					store.buyCar(buyNum);
			}
			else if(word.equals("RET")){//Returns the last bought car
				if(store.ac.getListSize()==0)//Check to see if a car was bought recently
					System.out.println("\nNo Cars Bought Recently");
				else{
					int retID=checkInt(commandLine.next());
					if(store.returnCar(retID))
						System.out.println("\nReturned: "+store.ac.getTransaction(retID).getCar().display());
				} 
			}
			else if(word.equals("ADD")){//Adds cars to the store
				store.addCars(cars);
				cars=new ArrayList<Car>();
			}
			else if(word.equals("SPR"))//Displays all the cars
				store.sortByPrice();
			else if(word.equals("SSR"))//Sorts cars by rating
				store.sortByRating();
			else if(word.equals("SMR"))//Sorts cars by maxRange
				store.sortByRange();
			else if(word.equals("FPR")){//Sorts cars by price
				if(!commandLine.hasNext()){
					System.out.println("\nNo Range Provided\n");
					in = new Scanner(System.in);
					continue;
				}
				String inOne=commandLine.next();//Check if input is valid for filter function
				double p1=checkDouble(inOne);
				if(p1<0){
					System.out.println("\nInput Below:\n");
					in = new Scanner(System.in);
					continue;
				}
				else if(!commandLine.hasNext()){
					System.out.println("\nNo Max Provided\n");
					in = new Scanner(System.in);
					continue;
				}
				String inTwo=commandLine.next();
				double p2=checkDouble(inTwo);
				if(p2<0){
					System.out.println("\nInput Below:\n");
					in = new Scanner(System.in);
					continue;
				}
				if(p1>=p2)
					System.out.println("\nInvalid Price Range");
				else
					store.filterByPrice(p1, p2);
			}
			else if(word.equals("FEL"))//Filters out all non electric cars from list
				store.filterByElectric();
			else if(word.equals("FAW"))//Filters out all non all wheel drive cars from list
				store.filterByAWD();
			else if(word.equals("FCL"))//Clears all filters
				store.FiltersClear();
			else if(word.equals("HELP"))//Prints help text
				System.out.println(helpText);
			else if(alphaLine.equals("SALESTEAM")){//Prints out the names of every salesperson
				store.team.printSalesPersons();
			}
			else if(alphaLine.equals("SALESTOPSP")){//Prints the top sales person or the tied salespersons
				store.team.printTopSales();
			}
			else if(alphaLine.equals("SALESSTATS")){//Prints the statistics about all sales
				store.ac.getSalesStats();
			}
			else if(alphaLine.equals("SALES")){//Prints all the sales
				store.ac.getSales();
			}
			else if(word.equals("SALES")){//Prints 
				int month=checkInt(commandLine.next());
				if(month>11||month<0)
					System.out.println("\nInvalid Month");
				else
					store.ac.getMonthSales(month);	
			}
			else//Check for invalid input
				System.out.println("\nInvalid Input"); 
			System.out.println("\nInput Below:\n");
			in = new Scanner(System.in);
		}
	}
	/**
	* Reads lines from a file into an ArrayList
	* @param file String that will be converted to a file
	* @return List with all the lines taken from the input file
	*/
	public static ArrayList<String> readInput(String file){
		ArrayList<String> lines = new ArrayList<String>(); 
		File carText=new File(file);
		Scanner s;
		try{
			s = new Scanner(carText);
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found\n\nNo Cars Inputted\n");
			return lines;
		}
		
		while(s.hasNextLine()){
			lines.add(s.nextLine());

		}
		s.close();
		return lines;
	}
	/**
	 * Takes list of strings and breaks them down into components and constructs car objects out of them
	 * @param list List with all the cars in string form
	 */
	public static void makeCars(ArrayList<String> list){
		for(int i=0;i<list.size();i++){
			Scanner line = new Scanner(list.get(i));
			String mfr=line.next();//Gets MFR
			String color=line.next();//Gets Color
			String model=line.next();//Gets Model
			if(!line.next().equals("ELECTRIC_MOTOR")){//Checks if car is gas car
				int power=0;//Sets power type
				double rating=line.nextDouble();//Gets rating 
				int range=line.nextInt();//Gets Range
				boolean AWD;//Checks for All Wheel Drive
				if(line.next().equals("AWD"))
					 AWD=true;
				else
					 AWD=false;
				double price =(double) line.nextInt();//Gets price
				cars.add(new Car(mfr,color,model,power,rating,range,AWD,price));//Constructs car
			}
			else{//If electric car

				int power =0;
				double rating=line.nextDouble();
				int range=line.nextInt();
				boolean AWD;
				if(line.next().equals("AWD"))
					 AWD=true;
				else
					 AWD=false;
				double price =(double) line.nextInt();
				int time = line.nextInt();//Gets recharge time
				cars.add(new ElectricCar(mfr,color,model,power,rating,range,AWD,price,time));//Constructs electric car
			}
		}

	}
	/** 
	 * Takes string and returns parsed double
	 * Checks for invalid inputs and returns -1 if invalid
	 * @param str String that will be parsed for a double
	 * @return Parsed double from the given string
	*/
	public static double checkDouble(String str){
		try{
			double d = Double.parseDouble(str);
		}
		catch(NullPointerException e1){
			System.out.println("\nNothing Provided");
			return -1;
		}
		catch(NumberFormatException e2){
			System.out.println("\nNot a number");
			return -1;
		}
		double d =Double.parseDouble(str);
		if(d<0){
			System.out.println("\nInvalid Range Value");	
			return -1;
		}
		return d;
	}
	/**
	 * Takes string and returns parsed integer
	 * Checks for invalid inputs and returns -1 if invalid
	 * @param str String that will be parsed for integer
	 * @return parsed integer 
	 */
	public static int checkInt(String str){
		int i;
		try{
			i= Integer.parseInt(str);
		}
		catch(NullPointerException e1){
			System.out.println("\nNothing Provided");
			return -1;
		}
		catch(NumberFormatException e2){
			System.out.println("\nNot an Integer");
			return -1;
		}
		i= Integer.parseInt(str);
		return i;
	}

}


