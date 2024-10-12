import java.util.*;
import java.sql.*;
public class Main {
	 private static String url="jdbc:mysql://localhost:3306/college";
	 private static String username="root";
     private static String password="Mohan@2004";
	public static void main(String[] args) {
		try
		{
		Connection conn=DriverManager.getConnection(url,username,password);
		Scanner sc=new Scanner(System.in);
		Register obj=new Register(sc,conn);
		Login obj1=new Login(sc,conn);
		System.out.println("!............ WELCOME TO BANK MANAGEMENT SYSTEM 🤍 .............!");
		boolean val=true;
		while(val)
		{
			System.out.println("1.Register"+"\n"+"2.Login"+"\n"+"3.Exit");
			System.out.print("Enter the choice : ");
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				obj.register();
				break;
			case 2:
				obj1.in();
				break;
			case 3:
				val=false;
				System.out.println("............THANK YOU ❤..............");
				break;
			default:
				break;
			}	
		}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

}
