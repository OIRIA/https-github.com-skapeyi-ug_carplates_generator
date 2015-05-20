

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class XmlExtraction extends SaveToDatabase implements Job {
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
   for(int i=1;i<=5;i++){//this for loop helps us loop through the for data files in the folder.
	   int k =i;
        try {

            List<Car> cars = new ArrayList<>();
            
            Calendar cal = Calendar.getInstance();
		 	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH");
		 	
		 	/**
		 	 * The "+dateformat.format(cal.getTime())" ensures that only data for that hour is passed...
		 	 * the data from before  will not be  parsed again*/
            File fXmlFile = new File("src/xml/Station_"+k+"_data_"+dateformat.format(cal.getTime())+".xml");
           
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(fXmlFile);

            document.getDocumentElement().normalize();

            System.out.println("Root element :" + document.getDocumentElement().getNodeName());

            NodeList nList = document.getElementsByTagName("CarPass");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                
                
                Node node = nList.item(temp);

                System.out.println("\nCurrent Element :" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Car car = new Car();
                    Element eElement = (Element) node;

                    System.out.println("No. Plate : " + eElement.getElementsByTagName("carplate").item(0).getTextContent());
                    System.out.println("Type : " + eElement.getElementsByTagName("cartype").item(0).getTextContent());
                    System.out.println("Time Captured : " + eElement.getElementsByTagName("timepassed").item(0).getTextContent());
                    System.out.println("Speed : " + eElement.getElementsByTagName("speed").item(0).getTextContent());

                    String plate = eElement.getElementsByTagName("carplate").item(0).getTextContent();
                    String type = eElement.getElementsByTagName("cartype").item(0).getTextContent();
                    String time = eElement.getElementsByTagName("timepassed").item(0).getTextContent();
                    String speed = eElement.getElementsByTagName("speed").item(0).getTextContent();
                    
                    car.setCarPlate(plate);
                    car.setCarType(type);
                    car.setSpeed(speed);
                    car.setTimePassed(time);
                    //after creating a car object, we call the saveToDatabase method,and pass data stored in the objects to it,
                    //which then saves the data to the database
                    //
                    saveToDatabase(plate, type, time, speed, k);
                    cars.add(car);
                 
                    
                }
            }
            
            System.out.println("THE XML HAS BEEN CONVERTED INTO JAVA CAR LIST OBJECT OF SIZE: " + cars.size());
            System.out.println(".......CAR OBJECT......."+ cars);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
        try{
            //then move the file whose data has been extracted to avoid repeating the same data
        	Calendar cal = Calendar.getInstance();
		 	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH");
        	File fXmlFile = new File("src/xml/Station_"+k+"_data_"+dateformat.format(cal.getTime())+".xml");
            File fXmlFileOut = new File("src/parsed/Station_"+k+"_data_"+dateformat.format(cal.getTime())+".xml");
            FileInputStream inStream = new FileInputStream(fXmlFile);
            FileOutputStream outStream = new FileOutputStream(fXmlFileOut);
            byte[] buffer = new byte[1024];
            
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
 
    	    	outStream.write(buffer, 0, length);
 
    	    }
 
    	    inStream.close();
    	    outStream.close();
 
    	    //delete the original file
    	    fXmlFile.delete();
 
    	    System.out.println("The Parsed XML  has been copied successfully to the Parsed folder");
 
            }catch(IOException e){
        	    e.printStackTrace();
        	}
   }
   }
}
