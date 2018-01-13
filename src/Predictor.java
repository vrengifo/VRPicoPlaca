import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Created by: Victor Rengifo
 * Date: 2018-01-12
 * 
 * 
 */
public class Predictor {

	private String sPlateNumber;
	private String sDate;
	private String sTime;
	
	/*
	 * Getters and Setters
	 */
	public String getsPlateNumber() {
		return sPlateNumber;
	}

	public void setsPlateNumber(String sPlateNumber) {
		this.sPlateNumber = sPlateNumber;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	private Date dDatetime;
	private Boolean bIsValid;
	
	public Boolean getIsValid() {
		return bIsValid;
	}
	
	/**
	 * Constructor
	 * @param plateNumber
	 * @param date
	 * @param time
	 */
	public Predictor(String plateNumber, String date, String time) {
		
		this.sPlateNumber = plateNumber;
		this.sDate = date;
		this.sTime = time;
		
		if (this.validPlate() && this.validDateTime())
			this.bIsValid = Boolean.TRUE;
		else
			this.bIsValid = Boolean.FALSE;
	}
	
	/**
	 * Validation of string: 7 characters, first 3 chars alphabetics, 4 chars as number
	 * @return
	 */
	private Boolean validPlate(){	
	  Boolean isValid = Boolean.FALSE;
	  /* validation: ABC1234 */
	  
	  if(this.sPlateNumber==null)
		  isValid=Boolean.FALSE;
	  else
	  {
	  	  if (this.sPlateNumber.length()==7)
	  	  {
	  		isValid = Boolean.TRUE;
	  		  for (int i=0; i<this.sPlateNumber.length(); i++)
		  	  {
		  		  if ( (i<=2)&& isValid ) {
		  		    isValid = Character.isAlphabetic(this.sPlateNumber.charAt(i));
		  		  }
		  		  else
		  		  {
		  			if (i>2 && isValid)
		  			{
		  				isValid = Character.isDigit(this.sPlateNumber.charAt(i));
		  			}
		  			else
		  			{
		  				isValid = Boolean.FALSE;
		  			}
		  		  }
		  	  }	  		  
	  	  }
	  	  else
	  		  isValid = Boolean.FALSE;
	  }	  
	  	  	  
	  return isValid;
	}
	
	/**
	 * Validation of Date Time with format yyyy-MM-dd HH:mm
	 * @return
	 */
	private Boolean validDateTime(){	
		  Boolean isValid = Boolean.FALSE;
		  /* validation: ABC1234 */
		  
		  DateFormat inFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				this.dDatetime = inFormat.parse(this.sDate+" "+this.sTime);
				isValid=Boolean.TRUE;
			} catch (ParseException e) {
				// TODO: handle exception
				this.dDatetime = null;
				e.printStackTrace();
				isValid=Boolean.FALSE;
			}
			return isValid;
	}
	
	/***
	 * Logic to determine if based on parameters the car "can" or "cannot" be on the road
	 * @return
	 */
	public String evaluate()
	{
		String res = "";
		
		if (this.bIsValid)
		{
			// get last digit
			Integer lastDigit = Integer.parseInt(this.sPlateNumber.substring(6));
			
			// get day of the week
			Integer dayOfWeek = this.getDayOfWeek();
			
			// get time
			Integer currTime = Integer.parseInt(this.getTime());
			
			if (dayOfWeek==1 || dayOfWeek==7)
				res = "CAN";
			else
			{
				switch (dayOfWeek) {
				case 2:
					if (((lastDigit==1) || (lastDigit==2)) && this.restrictedTime(currTime) )
					  res = "CANNOT";
					else
						res = "CAN";
					break;
				case 3:
					if (((lastDigit==3) || (lastDigit==4)) && this.restrictedTime(currTime) )
					  res = "CANNOT";
					else
						res = "CAN";
					break;
				case 4:
					if (((lastDigit==5) || (lastDigit==6)) && this.restrictedTime(currTime) )
					  res = "CANNOT";
					else
						res = "CAN";
					break;
				case 5:
					if (((lastDigit==7) || (lastDigit==8)) && this.restrictedTime(currTime) )
					  res = "CANNOT";
					else
						res = "CAN";
					break;
				case 6:
					if (((lastDigit==9) || (lastDigit==0)) && this.restrictedTime(currTime) )
					  res = "CANNOT";
					else
						res = "CAN";
					break;	

				default:
					break;
				}
			}	
		}
		else
			res = "NOT VALID DATA";
		return res;
	}
	
	/***
	 * Get day of week as int.  Sunday = 1, Monday=2 .. Friday=6
	 * @return
	 */
	private int getDayOfWeek()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.dDatetime);
		return(cal.get(Calendar.DAY_OF_WEEK));
	}
	
	/***
	 * Get the time as a number.  hhmm
	 * @return
	 */
	private String getTime()
	{
		int auxHour,auxMinute;
		String aux="";
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.dDatetime);
		auxHour=cal.get(Calendar.HOUR_OF_DAY);
		auxMinute=cal.get(Calendar.MINUTE);
		if(auxMinute<10)
			aux=String.valueOf(auxHour)+"0"+String.valueOf(auxMinute);
		else
			aux=String.valueOf(auxHour)+String.valueOf(auxMinute); 
		return(aux);
	}

	/***
	 * validate if the time is restricted or no
	 * @param currTime
	 * @return
	 */
	private Boolean restrictedTime(Integer currTime)
	{
		Boolean isNotValid = Boolean.FALSE;
		
		if ((currTime >= 700 && currTime <= 930 ) || (currTime >= 1600 && currTime <= 1930))
			isNotValid = Boolean.TRUE;
		else
			isNotValid = Boolean.FALSE;
		
		return isNotValid;
	}
	
}
