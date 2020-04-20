import java. util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;  
import java.util.*;



abstract class VehicleType implements Serializable
{
	 String vehicle_type;


		VehicleType(String vehicle_type)
			{
			this.vehicle_type = vehicle_type ;
			}

		void setVehicleType()
			{
			Scanner sc = new Scanner(System. in);
			System.out.println(" Enter the type of vehicle :");
			vehicle_type = sc.nextLine();
			}


		void getVehicleType()
		{
			System.out.println("\n \n The type of vehicle is :" + vehicle_type);
			
		}
}


interface Color
{
	String vehicle_color = "white" ;

	default public String getcolor()
	{
		return vehicle_color; 
	}
}

interface MfgDate
{

	public  LocalDate getMfgDate();
}


class Vehicle  extends VehicleType implements Serializable , Color , MfgDate 
{
	LocalDate mfg_date ;

	public LocalDate getMfgDate()
	{
		return mfg_date ; 
	}
	
	
	Vehicle(String mfg_date,String vehicle_type)
	{	
		super(vehicle_type);
		this.mfg_date = LocalDate.parse(mfg_date);
		
	}
	
	
	public void setDate()
	{
		String date;
		Scanner sc = new Scanner(System.in);
      	System.out.println(" \n Enter the date yyyy-mm-dd: ");
      	date = sc.nextLine();
	    mfg_date = LocalDate.parse(date);
	    setVehicleType();

	}


	public void getDate () 
	{
	getVehicleType();
	System.out.println("Date is :" + mfg_date);
	String color = getcolor();
	System.out.println("Color is :" + color);
	}

}


public class Test
{
	ArrayList<Vehicle> obj = new ArrayList<Vehicle>();
	public static void main(String[] args) 
	{  
		Test d =new Test();
		Vehicle[] v = new Vehicle[5];

        for(int i = 0; i < v.length; i++)
        {
        	System.out.println("Enter for :"+ (i+1)+ ":");
            v[i] = new Vehicle("2000-12-12","Tvs"); 
            v[i].setDate();
            d.obj.add(v[i]);
             System.out.println("\n\n");
        }

        d.serializeVehicles();
        d.deserializeVehicles();


	}

	void serializeVehicles() 
{
          try {
			FileOutputStream f=new FileOutputStream(new File("Record.txt"));
			ObjectOutputStream o=new ObjectOutputStream(f);
				for(int i=0;i<5;i++)
				{
					o.writeObject((Vehicle)obj.get(i));
				}
				o.close();
				f.close();
					
			}
			catch (FileNotFoundException e)
		{
			System.out.println("File not found......");
		} 
	
	catch (IOException e) 
		{
			System.out.println("Error initializing stream......");
		}
		
	}
	
	void deserializeVehicles()
	{
		Vehicle obj1[] = new Vehicle[5];
		try 
		{
			FileInputStream f1 = new FileInputStream("Record.txt");
			ObjectInputStream i=new ObjectInputStream(f1);

		for(int j=0;j<5;j++)
			{
			
			
				 obj1[j] = (Vehicle)i.readObject();
				 if(obj1[j].getMfgDate().getYear()>2018)
				{
				System.out.println("----------------------------------------------------------------------- \n");
				System.out.println(j+1);
				obj1[j].getDate();
				System.out.println("\n");
				System.out.println("-----------------------------------------------------------------------");
				}
			}
		i.close();
		f1.close();
		}
	catch (FileNotFoundException e)
		{
		System.out.println("File not found......");
		} 
	catch (IOException e) 
		{
		System.out.println("1Error initializing stream......");
		}
	catch(ClassNotFoundException e)
		{
		System.out.println("Class not found .........");
		}
	}
}