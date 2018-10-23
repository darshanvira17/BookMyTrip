import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.prompt.PromptSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class phpTravels extends Frame implements WindowListener,ActionListener {
//Hierarchy!
//		JFrame
//		'-JPanel
//	    	'-JButton
//			'-JTextField
//			'-JComboBox
//			'-JLabel
//	
//	
	//New Frame
	static JFrame guiFrame = new JFrame();
	
	//New Panels
	//0.Container Panel(This contains all other Panels)
	static JPanel container=new JPanel();
	//1. City Name Panel
	static JPanel CityName = new JPanel(); //Details Name of the City
	//2. Check-in Check-Out Dates Panel
	static JPanel Dates = new JPanel(); //Dates of check-in and check-out
	//3.Count of People Panel
	static JPanel Counts = new JPanel(); //Number of Adults and Children
	//4. Submit Panel
	static JPanel Submit=new JPanel(); //Submit Button!
	
	
	
	//New Components
	//1.City Name 
	static JTextField cityName=new JTextField(10);
	//2.checkIn and checkOut dates 
	static JTextField checkIn =  new JTextField(10);
	static JTextField checkOut =  new JTextField(10);
	//3. Counts 
	static Integer[] numberofPeople={0,1,2,3,4,5,6,7,8,9,10};
	static JComboBox numberOfAdults= new JComboBox(numberofPeople);
	static JComboBox numberOfChild= new JComboBox(numberofPeople);
	static JButton submit = new JButton( "Let's Go!");
	
	//Components Value variables
	static String nameofCity;
	static String checkInDate;
	static String checkOutDate;
	static int childCount;
	static int adultCount;
	
	//(nameofCity,checkInDate,checkOutDate,adultCount,adultCount);
	//Selenium Code to open phpTravels and Show the booking options!
	public void bookMyTrip(String city, String inDate, String outDate,int adults, int child){
		//https://phptravels.com/demo/
		try {
			//set system property for web driver
			System.setProperty("webdriver.gecko.driver","O:\\worksapce\\Copy of winiumtests\\src\\test\\resources\\drivers\\geckodriver.exe");
			// Create an instance of the driver	
			Thread.sleep(2000);
			WebDriver driver = new FirefoxDriver();
			// Navigate to a web page
			driver.get("https://phptravels.com/demo/");
			Thread.sleep(2000);
			String unamePass=driver.findElement(By.xpath("//body/section[contains(@class,'grey-box')]/div/div/div[contains(@class,' wow fadeInUp col-md-12 animated')]/div/div/div[contains(@class,'col-md-9')]/div[contains(@class,'col-md-12')]/div//div[contains(@class,'col-md-8')]/div")).getText();
			String username=(unamePass.substring(unamePass.indexOf("user"),unamePass.indexOf("user")+19));
			String password=(unamePass.substring(unamePass.indexOf("demo")));

			driver.get("https://www.phptravels.net/login");
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='loginfrm']/button")).click();
			driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[@id='s2id_autogen9']")).sendKeys(city);
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[16]/ul/li/ul/li[1]/div")).click();
			driver.findElement(By.xpath("/html/body/div[4]/section/div[2]/div/div/div[2]/div/div[1]/form/div[2]/div/input")).sendKeys(inDate);
			driver.findElement(By.xpath("/html/body/div[4]/section/div[2]/div/div/div[2]/div/div[1]/form/div[3]/div/input")).sendKeys(outDate);
			driver.findElement(By.xpath("//*[@id='travellersInput']")).click();
			if(adults==1){
				driver.findElement(By.xpath("//*[@id='adultMinusBtn']")).click();	
			}
			else{
			for(int i=0;i<adults-2;i++){
			//driver.findElement(By.xpath("//*[@id='travellersInput']")).click();
			driver.findElement(By.xpath("//*[@id='adultPlusBtn']")).click();
			}
			}
			for(int i=0;i<child;i++){
				driver.findElement(By.xpath("//*[@id='childPlusBtn']")).click();	
			}
			driver.findElement(By.xpath("/html/body/div[4]/section/div[2]/div/div/div[2]/div/div[1]/form/div[5]/button")).click();
			
//to perform right clicks on any element			
//			Actions action = new Actions(driver).contextClick(Form);
//			action.build().perform();
			Thread.sleep(10000);
			driver.quit();
		}
		catch(Exception e){
		e.printStackTrace();	
		}
	}
	
	//Main method containing the GUI Details
	public static void main(String[] args) {
		final phpTravels pt= new phpTravels();
		
		//-----------------------------------Design of the GUI-------------------------------------
		
		
		//Frame Properties
		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Book Your Trip!");
		guiFrame.setSize(400,225);	
		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("O:\\worksapce\\AutoLogInReservation\\src\\main\\java\\logo.png");
		guiFrame.setIconImage(image.getImage());

		//Panels Properties
		//Container Properties
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); //X_AXIS for Horizontal List Display
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK , 3));
		
		//CityName Properties
		CityName.setLayout(new GridLayout(1,1,1,1));
		CityName.setVisible(true);
		TitledBorder Name;
		Name = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,2),"City");
		
		//Dates Properties
		Dates.setLayout(new GridLayout(1,2,1,1));
		TitledBorder Date;
		Date = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,2),"Check-in and Check-Out Dates:");
		
		//Counts Properties
		Counts.setLayout(new GridLayout(2,2,1,1));
		TitledBorder Count;
		Count = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,2),"Number of People");
		
		//Submit Properties
		Submit.setLayout(new BorderLayout());
		Submit.setVisible(true);
		
		//Components Properties
		//1.cityName Properties
		PromptSupport.setPrompt("Name of the City",cityName);
		cityName.setBorder(Name);
		CityName.add(cityName);
		container.add(CityName);
		
		//Check-in Check-out components properties
		PromptSupport.setPrompt("Check-In Date: DD/MM/YYYY", checkIn);
		PromptSupport.setPrompt("Check-Out Date: DD/MM/YYYY", checkOut);
		Dates.add(checkIn);
		Dates.add(checkOut);
		Dates.setBorder(Date);
		container.add(Dates);
		
		//3. NumberOfAdults and NumberofChild component properties
		JLabel AdultsCount=new JLabel("Adults: ");
		JLabel ChildCount=new JLabel("Child: ");
		Counts.add(AdultsCount);
		Counts.add(numberOfAdults);
		Counts.add(ChildCount);
		Counts.add(numberOfChild);
		Counts.setBorder(Count);
		container.add(Counts);
		
		//4. Submit Button properties
		submit.setPreferredSize(new Dimension(20, 20));
		Submit.add(submit, BorderLayout.SOUTH);
		container.add(Submit);
		
		//After all Panels are added to the container then add the Container to the guiFrame.
		guiFrame.add(container);
		//and make sure ONLY THEN the JFrame is visible if Jframe is setVisible before, it wont show the components added later!
		guiFrame.setVisible(true);
		
		//-----------------------------------------------On CLICK ACTION---------------------------------------------
		//The ActionListener class is used to handle the event that happens when the user clicks the button.
				//It will turn on Selenium Driver, go to phptravels.com, log in, put in the details fed in by the user
				//and give the results to the user!
		
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//check the City Name input
						if((cityName.getText().equals(null)) | (cityName.getText().equals(""))){
							String message="City Name is needed";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
							return;
							}
							else{
								//if okay, store it in a a variable
							nameofCity=cityName.getText();
							}
						//Check check-in date entry and even call dateIsValid
						if((checkIn.getText().equals(null)) | (checkIn.getText().equals(""))){
							String message="Check-In Date is needed";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
							return;
							}
							else{
							if((checkIn.getText().length() != 10 ) || !(checkIn.getText().substring(2,3).equals("/")) || !(checkIn.getText().substring(5,6).equals("/"))){
								String message="Check In Date Format Required : DD/MM/YYYY";
								JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
								return;
								}
							}
							//if okay, store it in a a variable
							checkInDate=checkIn.getText();
							int inday= Integer.parseInt(checkIn.getText().substring(0,2));
							int inmonth= Integer.parseInt(checkIn.getText().substring(3,5));
							int inyear= Integer.parseInt(checkIn.getText().substring(6));
							if(!(dateIsValid(inday,inmonth,inyear))){
								return;
							}
							
						//check check-out date same as check-in date
						if((checkOut.getText().equals(null)) | (checkOut.getText().equals(""))){
							String message="Check-Out Date is needed";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
							return;
							}
						else{
							if((checkOut.getText().length() != 10 ) || !(checkOut.getText().substring(2,3).equals("/")) || !(checkOut.getText().substring(5,6).equals("/"))){
								String message="Check Out Date Format Required : DD/MM/YYYY";
								JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
								return;
								}
							}	
							//if okay, store it in a a variable
							checkOutDate=checkOut.getText();
							int outday= Integer.parseInt(checkOut.getText().substring(0,2));
							int outmonth= Integer.parseInt(checkOut.getText().substring(3,5));
							int outyear= Integer.parseInt(checkOut.getText().substring(6));
							if(!(dateIsValid(outday,outmonth,outyear))){
								return;
							}
							//check if Check-Out date is in future than CHeck-in date!
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  Date in = new Date();
							  Date out = new Date();
							  try {
									in=dateFormat.parse(checkInDate);
									out=dateFormat.parse(checkOutDate);
									if(in.after(out)){
										String message="Shouldn't live in our pasts!\n Change Check-Out date to proceed";
										JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
										return;
										}
									}
								catch (ParseException ex) {
									ex.printStackTrace();
								}
							  //check if Number of Adults is not zero
						if(numberOfAdults.getSelectedIndex()==(0)){		
							String message="Number of Adults should be greater than zero";
							JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
							return;
							}
							else{
							adultCount=numberOfAdults.getSelectedIndex();
						}
						childCount=numberOfChild.getSelectedIndex();
						//if everything is proper, call Selenium Web Driver to lookup phpTravels with this info!
						if(nameofCity!=null & checkInDate!=null & checkOutDate!=null & adultCount > 0)
						{
						 JFrame frame=null;
						JOptionPane.showMessageDialog(frame,"The details entered: \nCity : "+nameofCity+"\nCheck-In Date : "+checkInDate+"\nCheck-Out Date : "+checkOutDate+"\nNumber of Adults : "+adultCount+"\nNumber of Child : "+childCount);
						pt.bookMyTrip(nameofCity,checkInDate,checkOutDate,adultCount,childCount);
					}
					}
				});
	}
	public static boolean dateIsValid(int day, int month, int year){
		ArrayList<Integer> thirtyOneMonths=new ArrayList<Integer>();
		thirtyOneMonths.add(1);
		thirtyOneMonths.add(3);
		thirtyOneMonths.add(5);
		thirtyOneMonths.add(7);
		thirtyOneMonths.add(8);
		thirtyOneMonths.add(10);
		thirtyOneMonths.add(12);
		ArrayList<Integer> thirtyMonths=new ArrayList<Integer>();
		thirtyMonths.add(4);
		thirtyMonths.add(6);
		thirtyMonths.add(9);
		thirtyMonths.add(11);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  Date today = new Date();
		  Date input = new Date();
		  try {
			input=dateFormat.parse(day+"/"+month+"/"+year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  if(today.after(input)){
			  String message="Time Travel is Not Possible, Yet! \n Change Check-In date to proceed!";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
				return false;
		  }
		  
		if(month > 12){
			String message="Month Value shouldn't exceed 12";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(thirtyOneMonths.contains(month)){
			if(day>31){
				String message="Day Value Shouldn't exceed 31 for selected month";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else if(thirtyMonths.contains(month)){
			if(day>30){
			String message="Day Value Shouldn't exceed 30 for selected month";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		}
		else{
			if (month==2 && (year%4==0 && (year%100!=0||year%400==0))){
				if(day>29){
					String message="Day Value Shouldn't exceed 29 for selected month";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
					return false;
				}
				}
			else{
				if(day>28){
					String message="Day Value Shouldn't exceed 28 for selected month";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",JOptionPane.ERROR_MESSAGE);
					return false;
			}
			}
		}
		
		return true;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
