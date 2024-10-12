import java.util.*;
import java.sql.*;
class Login {
	 static Scanner sc=null;
	 static Connection conn=null;
	 Login(Scanner sc,Connection conn)
	 {
		 this.sc=sc;
		 this.conn=conn;
	 }
	 public void in()
	 {
		 System.out.print("Enter Your Email :");
		 String email=sc.next();
		 System.out.print("Enter the PIN :");
		 String pin=sc.next();
		 try
		 {
			 Statement st=conn.createStatement();
			 ResultSet rs=st.executeQuery("select Email,PIN,Name from Accountdetails");
			 while(rs.next())
			 {
				 if(rs.getString(1).equals(email) && rs.getString(2).equals(pin))
				 {
					System.out.println(".............Login Successful ✔ .................");
					System.out.println("Welcome "+rs.getString(3)); 
					Login(email);
					return;
				 }
			 }
			 System.out.println(".............Email Or PIN Is Incorrect ✖ ...........");
			 return;
			 
			 
		 }
		 catch(Exception e)
		 {
			 System.out.print(e);
		 }
	 }
	 public static void Login(String email)
	 {
		 boolean val=true;
		 while(true)
		 {
			System.out.println("1.Account Creation"+"\n"+"2.Amount Deposit"+"\n"+"3.Amount Withdrawel"+"\n"+"4.Check Balance"+"\n"+"5.Amount Transaction"+"\n"+"6.Log Out"); 
		    System.out.print("Enter the choice : ");
			int ch=sc.nextInt(); 
			switch(ch)
			{
			case 1:
				System.out.println("........Account creation........");
				if(Already(email))
				{
					System.out.println(".....You Already Have Account......");
					break;
				}
				else
				{
				    createAccount();
				    break;
				}
			case 2:
				AmountDeposit(email);
				break;
			case 3:
				AmountDebit(email);
				break;
			case 4:
				CheckBalance(email);
				break;
			case 5:
				AmountTransaction(email);
				break;
			case 6:
				val=false;
				System.out.println(".............Logout Successful.............");
				return;
				
			default:
				break;
			}
		  }
	  }
	  public static void AmountTransaction(String email)
	  {
		     System.out.print("Enter The Amount For Transaction :");
			 int amount=sc.nextInt();
			 System.out.print("Enter The PIN : ");
			 String pin=sc.next();
			 System.out.print("Enter the Reciever Account No : ");
			 String  accno=sc.next();
			 if(validation(email,pin) )
			 {
			 try
			 {
			 Statement st=conn.createStatement();
			 String query="select Amount from customerDetails where Email='%s'";
			 String str=String.format(query,email);
			 ResultSet rs=st.executeQuery(str);
			 if(rs.next())
			 {
			 int Total=Integer.valueOf(rs.getString(1));
			 int newAmount=Total-amount;
			 String query1="update customerDetails set Amount='%s' where Email='%s'";
			 String Amount=String.valueOf(newAmount);
			 String str2=String.format(query1,Amount,email);
			 st.executeUpdate(str2);
			 }
			 String qu="select Amount from customerDetails where AccountNo='%s'";
			 String qu1=String.format(qu,accno);
			 ResultSet rs1=st.executeQuery(qu1);
			 if(rs1.next())
			 {
				 int Total=Integer.valueOf(rs1.getString(1));
				 int newAmount=Total+amount;
				 String query1="update customerDetails set Amount='%s' where AccountNo='%s'";
				 String Amount=String.valueOf(newAmount);
				 String str2=String.format(query1,Amount,accno);
				 st.executeUpdate(str2);
			 }
			 System.out.println("..............Amount Transaction was Successfully Completed...........");
		}
			 catch(Exception e)
			 {
				 System.out.println(e);
			 }
			 }
			 else
			 {
				 System.out.println("..............Incorrect PIN..............");
			 }
			 
	  }
	 public static void AmountDebit(String email)
	 {
		 System.out.print("Enter The Amount for Withdrawel :");
		 int amount=sc.nextInt();
		 System.out.print("Enter The PIN : ");
		 String pin=sc.next();
		 if(validation(email,pin))
		 {
		 try
		 {
			 Statement st=conn.createStatement();
			 String query="select Amount from customerDetails where Email='%s'";
			 String str=String.format(query,email);
			 ResultSet rs=st.executeQuery(str);
			 if(rs.next())
			 {
			 int Total=Integer.valueOf(rs.getString(1));
			 int newAmount=Total-amount;
			 String query1="update customerDetails set Amount='%s' where Email='%s'";
			 String Amount=String.valueOf(newAmount);
			 String str2=String.format(query1,Amount,email);
			 st.executeUpdate(str2);
			 }
			 System.out.println("...............Amount Successfully Debited..........");
		 }
		 catch(Exception e)
		 {
			 System.out.print(e);
		 }
		 }
		 else
		 {
			 System.out.println("...........Incorrcet PIN.........");
		 }
	 }
	 public static void CheckBalance(String email)
	 {
		 try
		 {
			 Statement st=conn.createStatement();
			 String query="select Amount from customerDetails where Email='%s'";
			 String str=String.format(query,email);
			 ResultSet rs=st.executeQuery(str);
		     String amount="";
			 if(rs.next())
			 {
				amount=rs.getString(1);
			 }
			 System.out.println("Account Balance : "+ amount);
		 }
		 catch(Exception e)
		 {
			 System.out.print(e);
		 }
	 }
	 public static void AmountDeposit(String email)
	 {
		 System.out.print("Enter The Amount To Deposit :");
		 int amount=sc.nextInt();
		 System.out.print("Enter The PIN : ");
		 String pin=sc.next();
		 if(validation(email,pin))
		 {
		 try
		 {
			 Statement st=conn.createStatement();
			 String query="select Amount from customerDetails where Email='%s'";
			 String str=String.format(query,email);
			 ResultSet rs=st.executeQuery(str);
			 if(rs.next())
			 {
			 int Total=Integer.valueOf(rs.getString(1));
			 int newAmount=Total+amount;
			 String query1="update customerDetails set Amount='%s' where Email='%s'";
			 String Amount=String.valueOf(newAmount);
			 String str2=String.format(query1,Amount,email);
			 st.executeUpdate(str2);
			 }
			 System.out.println("...............Amount Credited Successfully..........");
		 }
		 catch(Exception e)
		 {
			 System.out.print(e);
		 }
		 }
		 else
		 {
			 System.out.println("...........Incorrcet PIN..........");
			 return;
		 }
	 }
	 public static boolean Already(String email)
	 {
		 try
		 {
			 Statement st=conn.createStatement();
			 ResultSet rs=st.executeQuery("select Email from customerDetails");
			 while(rs.next())
			 {
				 if(rs.getString(1).equals(email))
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
	 public static void createAccount()
	 {
		 System.out.print("Enter your Name : ");
		 String name=sc.next();
		 System.out.print("Enter your Email : ");
		 String Email=sc.next();
		 System.out.print("Enter MobileNo : ");
		 String mobile=sc.next();
		 System.out.print("Enter Intial Amount : ");
		 String amount=sc.next();
		 System.out.print("Enter Your PIN : ");
		 String pin=sc.next();
		 if(!validation(Email,pin))
		 {
			System.out.print("............Email or PIN Is Incorrect ✖ .........");
			return;
		 }
		 Random random = new Random();
	     int Accno = random.nextInt(1000000 + 1);
		 String accno=String.valueOf(Accno);
		 String str="insert into customerDetails values('%s','%s','%s','%s','%s','%s')";
		 String str2=String.format(str,name,Email,mobile,amount,pin,accno);
		 try
		 {
			 Statement st=conn.createStatement();
			 st.executeUpdate(str2);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 System.out.println("Account Was Created Succesfully ✔ ");
		 System.out.println("Your Account No is : "+accno);
		 System.out.println("..............................................................");
	 }
	 public static boolean validation(String email,String pin)
	 {
		try
		{
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select Email,PIN from Accountdetails");
			while(rs.next())
			{
				if(rs.getString(1).equals(email) && rs.getString(2).equals(pin))
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
