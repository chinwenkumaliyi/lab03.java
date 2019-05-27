package lab3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class lab03  extends JFrame implements ActionListener {
	
			
	        //stating the componenents
	        
	        JLabel label, labelA, labelB, labelC, labelD, labelE;

	        JTextField textFeildA, textFieldB, textFeildC, textFieldD, textFieldE;

	        JButton addCustomer;
	      
		
	        
	        //constructor for JFrame component
	        lab03(){
	        
	        	 //Providing Title

	            super("To extract Student Information");

	            labelE = new JLabel("Enter Name:");

	            labelE.setBounds(20, 20, 100, 20);

	            textFieldE = new JTextField(25);

	            textFieldE.setBounds(130, 20, 200, 20);

	            addCustomer = new JButton("Click Me");

	            addCustomer.setBounds(50, 50, 100, 20);

	            addCustomer.addActionListener(this);
	            
	            label = new JLabel("Searching Student Information From Database");

	            label.setBounds(30, 80, 450, 30);

	            label.setForeground(Color.red);

	            label.setFont(new Font("Serif", Font.BOLD, 20));
	            
	            setVisible(true);

	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            setSize(600, 500);
	            labelA = new JLabel("Customer FirstName:");

	            labelA.setBounds(20, 120, 100, 20);

	            textFieldA = new JTextField(50);

	            textFieldA.setBounds(130, 120, 200, 20);

	            labelB = new JLabel("Enter your LastName:");

	            labelB.setBounds(20, 150, 100, 20);

	            textFieldB = new JTextField(100);

	            textFieldB.setBounds(130, 150, 200, 20);

	            labelC = new JLabel("Customer Number:");

	            labelC.setBounds(20, 180, 100, 20);
	            
	            textFieldC = new JTextField(50);

	            textFieldC.setBounds(130, 180, 200, 20);

	            labelD = new JLabel("Country:");

	            labelD.setBounds(20, 210, 100, 20);

	            textFieldD = new JTextField(50);

	            textFieldD.setBounds(130, 210, 100, 20);

	            setLayout(null);
	        	
	            //Add components to the JFrame

	            add(labelE);

	            add(textFieldE);

	            add(addCustomer);

	     

	            add(label);

	            add(labelA);

	            add(textFieldA);

	            add(labelB);

	            add(textFeildB);

	            add(labelC);

	            add(textFieldC);

	            add(labelD);

	            add(textFieldD);
	            
	          //Set TextField Editable False

	            textFieldA.setEditable(false);

	            textFieldB.setEditable(false);

	            textFieldC.setEditable(false);

	            textFieldD.setEditable(false);
	        }
	        
	        public void actionPerfromed(ActionEvent e) {
	        //Creating database connection and extracting recordss
	        
	        	try {
	        		String str = textFieldE.getText();
	        		
	        		Class.forName("oracle.jdbc.driver.OracleDriver");
	        		Connection con = DriverManager.getConnection(("jdbc:oracle:thin:@mcndesktop07:1521", "ctomm", "welcome");
	        		PreparedStatement st = con.prepareStatement("select * from emp where FirstName=?");
	        		st.setString(1,str);
	        		
	        		ResultSet ss = st.executeQuery();
	        		
	        		if(ss.next()) {
	        			
	        		String nc = ss.getString(1);
	        		String nc1 = ss.getString(2);
	        		String nc2= ss.getString(3);
	        		String nc3 = ss.getString(4);
	        		
	        		// Records in TextFields.

	                textFieldA.setText(ss);

	                textFieldB.setText(nc1);

	                textFieldC.setText(nc2);

	                textFieldD.setText(nc3);

	            } else {

	                JOptionPane.showMessageDialog(null, "Oops record not found");

	            }
	                //For Exception Handler

	            } catch (Exception ex) {

	     

	                System.out.println(ex);

	            }


	    //The Running Constructor
	        		
	        	}

	public static void main(String[] args) {
	new lab03();
	
		Scanner sc = new Scanner(System.in);
		ArrayList<Person1> personList = new ArrayList<Person1>();
		ArrayList<Person1> personListOutPut = new ArrayList<Person1>();
		File myFile = new File("Lab02.txt");
		System.out.println("Welcome to the person manager\n");
		String input = "";
		while (true) {
			while (true) {
				System.out.println("\nCreate customer or employee?(c/e)");
				input = sc.nextLine();
				if (input.equals(" ")) {
					System.out.println("Error! This entry is required. Try Again");
					continue;
				}
				if (!input.equals("c") && !input.equals("e")) {
					System.out.println("Error! Entry must be 'c' or 'e'. Try Again");
					continue;
				}
				break;
			}
			String fname, lname, cno, ssn;
			System.out.print("FirstName:");
			fname = sc.nextLine();
			System.out.print("LastName:");
			lname = sc.nextLine();
			if (input.equals("c")) {
				System.out.print("Customer Number:");
				cno = sc.nextLine();
				personList.add(new Customer(fname, lname, cno));
				
			}
			if (input.equals("e")) {
				System.out.print("SSN : ");
				ssn = sc.nextLine();
				personList.add(new Employee1(fname, lname, ssn));
			} 
			
			System.out.println("Continue?(y/n)");
			String cont = sc.nextLine();
			while (true) {
				if (!cont.equals("y") && !cont.equals("n")) {
					System.out.println("Error! Entry must be 'y' or 'n'. Try Again.");

				}
				break;
			}
			if (cont.equals("n")) {
				break;
			}
		} 
		try {
			FileOutputStream fout = new FileOutputStream(myFile);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			System.out.println(personList.size());
			for(Person1 p: personList)
			{
				oout.writeObject(p);
			} 
			
			FileInputStream fin = new FileInputStream(myFile);
			ObjectInputStream oin = new ObjectInputStream(fin);
			try
			{
				while(true)
				{
					Person1 p1 = (Person1)oin.readObject();
					personListOutPut.add(p1);
				}
			}
			catch(EOFException ex)
			{
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Error:"+ex);
		}
		for(Person1 p1 : personListOutPut)
		{
		
			System.out.println(p1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
