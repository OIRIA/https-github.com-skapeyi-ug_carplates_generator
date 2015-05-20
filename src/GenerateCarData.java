import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class GenerateCarData extends TriggerDataExtraction implements Job 
{
	//=============================================
	
	/**
	 * This method generates a  character to append to the number plate.
	 * We generate a random character to append to the end of the number plate,
	 * */
	public static String characterGenerator() {
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int character=(int)(Math.random()*26);
		String s=alphabet.substring(character, character+1);		
		return s;
		}
	/**
	 * This method generates the number part of the number plate
	 * The number should be between 1 and 999
	 * */
	public static int plateNumberGenerator(){
		int min = 1;
		int max = 999;
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;		
		return i;
	}
	/**
	 * This method generates the letter part of the number plate.
	 * */
	public static String plateLetterGenerator(){
		String [] plateLetter= {"","UAA","UAB","UAE","UAF","UAG","UAD","UAH","UAJ","UAK", "UAL", "UAM", "UAN","UAP","UAQ", "UAR", "UAS", "UAT", "UAU", "UAV"};	
		int min=1;
		int max=19;
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;		
		return plateLetter[i];
	}
	/**
	 * This method generates the full number plate
	 * It calls the methods which generate the number, the character and the letter parts.
	 * and appends them to generate a valid number plate string.
	 * */
	public static String fullPlateGenerator(){
		int number;
		String letters,letter, fullNumberPlate;
		int appendzero;
		number=plateNumberGenerator();		
		letters=plateLetterGenerator();
		letter=characterGenerator();
		appendzero=String.valueOf(number).length();
		
		if(appendzero==1){
				fullNumberPlate=letters+" 00"+number+letter;
			}
		else if(appendzero==2){
				fullNumberPlate=letters+" 0"+number+letter;			
			}
		else{
			fullNumberPlate = letters+" "+number+letter;
			}		
		return fullNumberPlate;		
		
	}
	/**
	 * This method generate a random number, used to pick the car type.
	 * */
	public static String carTypeGenerator(){
		String carCategories[]={"","SEDAN","SUV","VAN","LIGHT TRUCK","BUS","LORRY","TRAILER"};
		int min=1;
		int max=7;
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;		
		return carCategories[i] ;		
	}
	/**
	 * This method generates the car speed attribute
	 * */
	public static String carSpeedGenerator(){
		int min=1;
		int max=230;
		String carSpeed;
		Random r = new Random();
		int i = r.nextInt(max - min + 1) + min;		
		carSpeed=  String.valueOf(i);
		return carSpeed;
	}
	/**
	 * This method generates the time the car passed, by reading the system time
	 * */
	public static String carTimePassed(){			
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());	
		
		return timeStamp;		
	}
	//=============================================	
	
	//============================================
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
 
		System.out.println("Hello Adminsistrator!");
		
		 try {
			 //For loop is use to create data for all the five stations.
			 	for(int i=0;i<5;i++){
			 	String stationId []={"1","2","3","4","5"};
			 	
			 	//the calendar and simple date format instantiations help in naming a file,
			 	//with these, we create a file name with a timestamp
			 	Calendar cal = Calendar.getInstance();
			 	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH");
			 	
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		 
				// Create root elements for the xml file.
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("Station");
				
				// set attribute for the station element.
				Attr attr = doc.createAttribute("id");
				attr.setValue(stationId[i]);
				rootElement.setAttributeNode(attr);//setting attribute for the root element
				doc.appendChild(rootElement);
				/**
				 * We want every generated xml to have a different number of nodes, because each station
				 * will have a different number of cars passing per hour.
				 * Therefore generate a random number between 1 and 50 to represent the number of cars passing per
				 * station per hour. so for  for any given hours, only a max of 50 cars will go past a given station.
				 * */
				int min=1;
				int max=50;
				Random r = new Random();
				int i1 = r.nextInt(max - min + 1) + min;	
				for(int i2=0;i2<i1;i2++){
				// Car elements
				Element car = doc.createElement("CarPass");
				rootElement.appendChild(car);						 
				
		 
				// Car Plate element
				Element carPlate = doc.createElement("carplate");
				carPlate.appendChild(doc.createTextNode(fullPlateGenerator()));
				car.appendChild(carPlate);
		 
				// Car Type Element
				Element cartype = doc.createElement("cartype");
				cartype.appendChild(doc.createTextNode(carTypeGenerator()));
				car.appendChild(cartype);
		 
				// Time Car Passed
				Element timepassed = doc.createElement("timepassed");
				timepassed.appendChild(doc.createTextNode(carTimePassed()));
				car.appendChild(timepassed);
		 
				// Car Speed elements
				Element carspeed = doc.createElement("speed");
				carspeed.appendChild(doc.createTextNode(carSpeedGenerator()));
				car.appendChild(carspeed);
		 
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				String filename="Station_"+stationId[i]+"_data_"+dateformat.format(cal.getTime());
				StreamResult result = new StreamResult(new File("src/xml/"+filename+".xml"));
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
		 
				transformer.transform(source, result);}
		 
				System.out.println("Station "+(i+1)+"'s Data has been Scanned");}
		 
			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
 
	}
	
 
}