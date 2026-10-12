import java.util.*;
import java.io.*;

class Employee implements Serializable
{
    public int EmpID;
    public String EmpName;
    public int EmpAge;
    public String EmpAddress;
    public int EmpSalary;

    private static int Counter;

    static
    {
        Counter = 1;
    }
    public Employee(String b, int c, String d, int e)
    {
        this.EmpID = Counter++;
        this.EmpName = b;
        this.EmpAge = c;
        this.EmpAddress = d;
        this.EmpSalary = e;
    }

    public void DisplayInformation()
    {
        System.out.println("Emp id : "+this.EmpID + " " + "Emp Name : "+this.EmpName +" " + "Age : "+this.EmpAge + " "+ "Address : "+this.EmpAddress + " "+ "Emp Salary : "+this.EmpSalary);
    }

    public String toString()
    {
        return "Emp id : "+this.EmpID + " " + "Emp Name : "+this.EmpName +" " + "Age : "+this.EmpAge + " "+ "Address : "+this.EmpAddress + " "+ "Emp Salary : "+this.EmpSalary;
    }


}

class MarvellousDBMS implements Serializable
{
    private LinkedList <Employee> Table;

    public MarvellousDBMS()
    {
        System.out.println("Marvellous DBMS Started Successfully....\n");

        Table = new LinkedList<>();
    }

    //Insert Into Employee values(1,'Amit',21,'Pune',2100)
    public void InsertIntoTable(
                                String name, 
                                int Age,
                                String city,
                                int Salary
                                )
    {
        Employee eobj = new Employee(name, Age,city,Salary);
        Table.add(eobj);
        System.out.println("Marvellous DBMS : > New Record Inserted Successfully.....");

    }

    //Select * from Employee
    public void SelectStarFrom()
    {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Data From The Employee Table");
        System.out.println("----------------------------------------------------------------------------------------");
        
        for(Employee eref : Table)
        {
            System.out.println(eref);
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }

    
    public void TakeBackup()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("marvellousDBMS.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

        }
        catch(Exception eobj)
        {
            System.out.println("Exception Occured");
        }
    }

    public static MarvellousDBMS RestoreBackup(String path)
    {
        try
        {
            MarvellousDBMS ret = null;
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ret = (MarvellousDBMS)ois.readObject(this);
            return ret;

        }
        catch(Exception eobj)
        {
            System.out.println("Exception Occured");
            return null;
        }
    }

    //select * from Employee where emplyeeid = 11
    public void SelectSpecificId(int id)
    {
        boolean found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                found = true;
                System.out.println(eref);
                break;
            }

        }

        if(found == false)
        {
            System.out.println("There is No Such record");
        }

    }

    //select * from Employee where employee name = 'Sagar'
    public void SelectSpecificName(String Name)
    {
        boolean found = false;

        for(Employee eref : Table)
        {
            if(Name.equals(eref.EmpName))
            {
                found = true;
                System.out.println(eref);
            }

        }

        if(found == false)
        {
            System.out.println("There is No Such record");
        }

    }   

    //delete from Employee where empid = 11
    public void DeleteSpecificId(int id)
    {
        boolean found = false;
        int index = 0;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                found = true;
                break;
            }
            index++;
        }

        if(found == false)
        {
            System.out.println("There is No Such record");
        }
        else
        {
            Table.remove();
            System.out.println("Record successfully Deleted");
        }

    }   
 

}//End of Marvellous


class program847
{
    public static void main(String A[]) throws Exception
    {
        MarvellousDBMS mobj =  MarvellousDBMS.RestoreBackup("MarvellousDBMS.ser");
        if(mobj == null)
        {
            System.out.println("Unable to restore the backup");
            mobj = new MarvellousDBMS();
        }
        Scanner sobj = new Scanner(System.in);
        int salary = 0;
        int age = 0;
        String name = "";
        String Addr = "";
        int id = 0;

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("----------------------------------- Marvellous DBMS ------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------");

        int iOption = 0;
        while(iOption != 20)
        {

            System.out.println("----------------------------------------------------------------------------------------");

            System.out.println("1 : Insert Into Employee");
            System.out.println("2 : Select * From Employee");
            System.out.println("3 : Take a Backup of table");
            System.out.println("4 : select * from Employee where EmpId = ");
            System.out.println("5 : select * from employee where Employye Name : ");
            System.out.println("6 : Delete From Employee where = ");
            System.out.println("20 : Terminate The DBMS");

            System.out.println("----------------------------------------------------------------------------------------");

            System.out.println("Please Select The desired Operation on DataBase : \n");
            iOption = sobj.nextInt();

            if(iOption == 1)
            {
                System.out.println("Please Enter Tha Data : \n");
                
                sobj.nextLine();
                System.out.println("Enter The Name of the Employee : ");
                name = sobj.nextLine();

                System.out.println("Enter The Age : ");
                age = sobj.nextInt();

                sobj.nextLine();
            
                System.out.println("Enter The Address : ");
                Addr = sobj.nextLine();

                System.out.println("Enter The Salary : ");
                salary = sobj.nextInt();

                mobj.InsertIntoTable(name,age,Addr,salary);

            }

            else if(iOption == 2)
            {
                mobj.SelectStarFrom();
            }

            else if(iOption == 3)
            {
                mobj.TakeBackup();
                System.out.println("The DataBase is Successfully stored into secondary storage \n");
            }

            else if(iOption == 4)
            {
                System.out.println("Enter The Employee id : ");
                id = sobj.nextInt();

                mobj.SelectSpecificId(id);
            }

            else if(iOption == 5)
            {
                sobj.nextLine();
                System.out.println("Enter Tha Name of Employee");
                name = sobj.nextLine();

                mobj.SelectSpecificName(name);
            }

            else if(iOption == 6)
            {
                System.out.println("Enter The Employee id : ");
                id = sobj.nextInt();

                mobj.DeleteSpecificId(id);
            }

            else if(iOption == 20)
            {
                System.out.println("Thank You for Using Marvellous DBMS");
                mobj = null;
                System.gc();
                break;
            }

        }//End of while
        
    }//End of main method        
}//End of main class
