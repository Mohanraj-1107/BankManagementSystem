import java.util.*;
import java.sql.*;
class Register {
	static Scanner sc=null;
	static Connection conn=null;
    public Register(Scanner sc,Connection conn)
    {
	 this.sc=sc;
	 this.conn=conn;
    }
    public void register()
    {
    	System.out.print("Enter your Name :");
    	String name=sc.next();
    	System.out.print("Enter your Email : ");
    	String email=sc.next();
    	System.out.print("Enter your security PIN :");
    	String pin=sc.next();
   	try {
   	    Statement st=conn.createStatement();
    	String str="insert into Accountdetails values('%s','%s','%s')";
    	String str2=String.format(str,name,email,pin);
    	if(exsist(email))
    	{
    		System.out.println("..............User Already Exists..................");
    		return;
    	}
    	else
    	{
   	    st.executeUpdate(str2);
   	    System.out.println("............Registration Succesfull...........");
    	}
    	}
   	catch(Exception e)
   	  {
  		System.out.print(e);
      }
   	
    }
    public static boolean exsist(String Email)
    {
    	try {
    	Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select Email from Accountdetails");
        while(rs.next())
        {
        	if(rs.getString(1).equals(Email))
        	{
        		return true;
        	}
        }
    	}
       catch(Exception e)
    	{
    	   System.out.print(e);
    	}
    	return false;
    }
   
}
