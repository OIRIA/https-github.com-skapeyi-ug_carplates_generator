/**
 * This Class has method that generate car data.
 * 
 * */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class CarDetails { 
	
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

}
