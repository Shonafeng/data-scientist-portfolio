import java.io.*;

import java.util.Scanner;
/*
 * This program is to Simulate a vender machine
 * @author fengxiao
 *
 */
public class VenderMachineSimulator 
{
	/**
	 * load product in vender machine	
	 * @param item - product
	 * @param name - name of product
	 * @param price - price of product
	 * @param container - container of product
	 * @param unit - unit of product
	 * @param size - size of product
	 * @param quantity -quantity of prodcut
	 */
	private static void loadProduct(Product item[],String name[], String[] price, String[] container,String[] unit, String[] size, String[] quantity)
	{
	    for (int i=0; i<item.length ; i++)
	    {
	        item[i] = new Product();
	        item[i].setName(name[i]);
	        item[i].setPrice(price[i]);
	        item[i].setContainer(container[i]);
	        item[i].setUnit(unit[i]);
	        item[i].setSize(size[i]);
	        item[i].loadQuantity(quantity[i]);
	    }
	}

	/**
	 * Load currency in vender machine
	 * @param currency -currency
	 * @param type -type of currency
	 * @param handler - handler of currency
	 * @param currencyName -currency name
	 * @param value - value of currency
	 * @param currencySize - currency size
	 * @param material - currency material
	 * @param currencyQuantity - currency quantity
	 * @return currency - currency object
	 */
	private static Currency[] loadCurrency(Currency [] currency,String type[], String handler[], String currencyName[],String value[],
	        String currencySize[], String material[], String currencyQuantity[])
	{
	    for (int i=0; i<currency.length ; i++)
	    {
	        currency[i] = new Currency();
	        currency[i].setType(type[i]);
	        currency[i].setHandler(handler[i]);
	        currency[i].setName(currencyName[i]);
	        currency[i].setValue(value[i]);
	        currency[i].setSize(currencySize[i]);
	        currency[i].setMaterial(material[i]);
	        currency[i].loadQuantity(currencyQuantity[i]);
	    }
	    return currency;		
	}
	
	/**
	 * display command
	 */
	public static void showCommand()
	{
	    System.out.print("Available Commands\n\n"
				 + "Show Commands:     0\n"
				 + "Display Inventory: 1\n"
				 + "Display Currency:  2\n"
				 + "Purchase Item:     3\n"
				 + "Exit:             -1\n\n\n"
				 );
	}
	
	
	/**
	 * display product in vendor machine
	 * @param item product object
	 */
	public static void displayProduct(Product [] item)
	{		
	    for(int i=0;i<item.length ;i++)
	    {
	        System.out.println(i + ": " + item[i].toSting() );			
	    }
	    System.out.println();		
	}
	
	/**
	 * check if currency handles/provides paper and coin 
	 * @param currency currency object 
	 * @param currencyType currency type
	 * @param needQuantityCheck dose currency quantity check needed
	 * @return needQuantityCheck boolean 
	 */
	private static boolean checkCurrency (Currency currency[], String currencyType, boolean needQuantityCheck)
	{
	    for (int i=0; i<currency.length ;i++)
	    {
	        if(currency[i].getMaterial().equals(currencyType))
	        {	
	            if((needQuantityCheck && currency[i].getQuantity() > 0) || !needQuantityCheck) 
	            { 
	                return true;
	            }
	        }				
	    }
	    return false;	
	}
	
	
	/**
	 * display currency in vendor machine
	 * @param currency currency array object
	 */
	public static void displayCurrency(Currency [] currency)
	{
	    System.out.println("Currency Handler: " + currency[0].getHandler()); 
	    System.out.println("Handles Paper Currency: " + checkCurrency (currency, "paper", false));  
	    System.out.println("Handles Coins Currency: " + checkCurrency (currency, "metal", false));
	    System.out.println("Provides change paper: " + checkCurrency (currency, "paper", true));
	    System.out.println("Provides change coin: " + checkCurrency (currency, "metal", true));

	    for(int i=0;i<currency.length;i++) 
	    {
	        System.out.println(currency[i].toString() + " Quantity: " + currency[i].getQuantity() + " in stock.");
	    }
	    System.out.println();
	}
	
	/**
	 * make purchase and update product quantity
	 * @param itemPrice price of item
	 * @param payValue customer paid value
	 * @param item product object
	 * @param itemNumber product number
	 * @param diffCurrency number of different currency
	 * @param currency currency object
	 * @param currencyType currency type
	 * @param currencyCount count of currency 
	 */
	public static void makePurchase(double itemPrice, double payValue, Product item[], int itemNumber, int diffCurrency,
	        Currency currency[], int currencyType[], int currencyCount[])
	{
	    if (itemPrice<=payValue && item[itemNumber].getQuantity()>0)
	    {
	        item[itemNumber].setQuantity(item[itemNumber].getQuantity()-1);
	        for (int index=0; index<diffCurrency; index++)
	        {
	            currency[ currencyType[index] ].setQuantity(currency[ currencyType[index] ].getQuantity() + currencyCount[index]);
	        }
	        System.out.println("\nItem Purchased");
	    }
	    else if(itemPrice > payValue)
	    {
	        System.out.println("Inadaquate funds\tPaid: " + payValue +" item cost: "+ itemPrice +
					" Purchase failed.\tTry another time");
	    }
	    else if(item[itemNumber].getQuantity() <= 0)
	    {
	        System.out.println("inadaquate item for purchase\nTry other items");
	    }
	}
	
	
	/**
	 * check if changes are enough 
	 * @param payValue - costumer paid value
	 * @param itemPrice - product price
	 * @param currency - currency
	 * @return isChangeEnough
	 */
	public static boolean isChangeEnough(double payValue,double itemPrice,Currency currency[])
	{
	    double changeValue = 0;		
	    changeValue = payValue-itemPrice;
	    for(int i=0;i<currency.length ;i++)
		{			
	        while(changeValue >= currency[i].getValue())
			{
	            changeValue -= currency[i].getValue()*currency[i].getQuantity();
			}				
		}
	    return changeValue<=0;
	}
	
	/**
	 * calculate and get change
	 * @param payValue - costumer paid value
	 * @param itemPrice - price of product
	 * @param currency - currency object
	 */
	public static void returnChange(double payValue,double itemPrice,Currency currency[])
	{
	    double changeValue=0;
	    changeValue= payValue-itemPrice;
	    if(changeValue>0)
		{
	        System.out.println("Change Amount: $"+changeValue );
	        for (int i=0; i<currency.length;i++)
			{
	            while(changeValue>=currency[i].getValue() && currency[i].getQuantity()>0)
				{				
	                System.out.println(currency[i].toString());
	                changeValue-=currency[i].getValue();
	                currency[i].setQuantity(currency[i].getQuantity()-1);															
				}								
			}			
		}
	}
	

	/**
	 * machine command method
	 * @param item - product object 
	 * @param currency - currency object
	 */
	public static void machineCommand(Product item[],Currency currency[])
	{
	    Scanner in=new Scanner(System.in);
	    showCommand();
	    System.out.print("Command:");
	    int command=in.nextInt();
		
	    while(command!=-1)
	    {
	        switch(command)
	        {			
	            case 0:
	            {//display command
	                showCommand();
	                break;
	            }	
	            case 1:
	            {//display product
	                displayProduct(item);
	                break;
	            }
	            case 2:
	            {//display currency
	                displayCurrency(currency);
	                break;
				}
	            case 3:
	            {//make purchase
	                System.out.print("Item #: ");
	                int itemNumber=in.nextInt();
	                double itemPrice=item[itemNumber].getPrice();
					
	                System.out.print("How many diffrent bill type?: ");
	                int diffCurrency=in.nextInt();
	                int [] currencyType=new int[diffCurrency];
	                int [] currencyCount=new int[diffCurrency];
	                double payValue=0;
					
	                for (int k=0; k<diffCurrency; k++)
	                {
	                    for(int i=0;i<currency.length ;i++)
	                    {
	                        System.out.println("Currency #:" + i + ": " + currency[i].toString());
	                    }
	                    System.out.print("Bill type: ");
	                    currencyType[k] = in.nextInt();
	                    System.out.print("Number of currency #" + currencyType[k] + ":" );
	                    currencyCount[k] = in.nextInt();
	                    payValue = payValue + currency[currencyType[k]].getValue() * currencyCount[k];
	                }
					//make purchase
	                if(isChangeEnough(itemPrice,payValue,currency))
	                {
	                    makePurchase( itemPrice,  payValue,  item, itemNumber,  diffCurrency, currency,  currencyType, currencyCount);
						//calculate change
	                    returnChange(payValue,itemPrice,currency);						
	                }
	                else
	                {
	                    System.out.println("Not enough change, transaction cancelled");
	                }
	                break;										
	            }					
	        }
	        System.out.print("\nCommand:");
	        command=in.nextInt();			
	    }
	    System.out.println("Purchase end, Thank you!");
		
	    in.close();
	}
	
	/**
	 * number of lines in data file
	 * @param path - data file path
	 * @return lineNumber
	 */
	private static int lineNumber(String path)
	{
		//read file to get how many line of content 
	    int lineNumber = 0;
	    LineNumberReader lnr=null;
		
	    try
	    {
	        lnr = new LineNumberReader(new FileReader(path));
	        lnr.skip(Long.MAX_VALUE);
	        lineNumber=lnr.getLineNumber()+1;
	    }
	    catch (IOException e)
	    {
	        System.out.println("I/O Excepton: " + e.getMessage());
	    }

	    return lineNumber;
	 }
	

	
	public static void main(String[] args) 
	{
		//initiate product
		//read file to get how many line of content 
	    String productDataPath = "/Users/fengxiao/Documents/Johns Hopkins MS Data Science/2017 Spring/intro Java/mini project2/src/product_data.txt";
	    int productArrayLength = lineNumber(productDataPath);
		//initiate currency
        String currencyDataPath = "/Users/fengxiao/Documents/Johns Hopkins MS Data Science/2017 Spring/intro Java/mini project2/src/currency_data.txt";
        int currencyArrayLength = lineNumber(currencyDataPath);

        BufferedReader inFile = null;
		//declare product features
        String [] name = new String[productArrayLength];
        String [] price = new String[productArrayLength];
        String [] container = new String[productArrayLength];
        String [] unit = new String[productArrayLength];
        String [] size = new String[productArrayLength];
        String [] quantity = new String[productArrayLength];
		
		//read in product from data file
        try
        {
            String currentLine;
            inFile = new BufferedReader(new FileReader(productDataPath));
				
            int i=0;
            while ((currentLine = inFile.readLine())!= null)
            {
                String [] array = currentLine.split(",");
                name[i] = array[0];
                price[i] = array[1];
                container[i] = array[2];
                unit[i] = array[3];
                size[i] = array[4];
                quantity[i] = array[5];									
                i++;
            }
        }
        catch (IOException e)
        {
		    System.out.println("I/O Excepton: " + e.getMessage());
        }
		
		
		//declare product features
		
        String [] handler = new String[currencyArrayLength];
        String [] type = new String[currencyArrayLength];
        String [] currencyName = new String[currencyArrayLength];
        String [] value = new String[currencyArrayLength];
        String [] currencySize = new String[currencyArrayLength];
        String [] material = new String[currencyArrayLength];
        String [] currencyQuantity = new String[currencyArrayLength];

		
		//read in currency from data file
        try
        {
            String currentLine;
            inFile = new BufferedReader(new FileReader(currencyDataPath));
				
            int i=0;
            while ((currentLine = inFile.readLine())!= null)
            {
                String [] array = currentLine.split(",");
                handler[i] = array[0];
                type[i] = array[1];
                currencyName[i] = array[2];
                value[i] = array[3];
                currencySize[i] = array[4];
                material[i] = array[5];
                currencyQuantity[i] = array[6];
                i++;
            }
        }
        catch (IOException e)
        {
            System.out.println("I/O Excepton: " + e.getMessage());
        }
        
		//initiate product and currency object		
        Product [] item=new Product[name.length];
        Currency [] currency=new Currency[currencyName.length];
		
		//use setter to load product and currency in vender machine
        loadProduct(item,name, price, container, unit, size, quantity);
        loadCurrency(currency,type,handler,currencyName,value,currencySize,material,currencyQuantity);
		
		//execute machine command method
        machineCommand(item, currency);
	}
}
