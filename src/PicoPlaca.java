/***
 * Created by: Victor Rengifo
 * Date: 2018-01-12
 * Pico & Placa
 */
public class PicoPlaca {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Sample 1: car can be on road
		String sLicensePlate = "ABC1234";
		String sDate = "2018-01-12";
		String sTime = "08:00";
		
		Predictor p = new Predictor(sLicensePlate, sDate, sTime);
		System.out.println("The car with plate "+sLicensePlate+" "+p.evaluate()+" be on the road");
		
		// Sample 2: Plate incomplete
		sLicensePlate = "ABC239";
		sDate = "2018-01-12";
		sTime = "08:00";
		
		Predictor p2 = new Predictor(sLicensePlate, sDate, sTime);
		if(p2.getIsValid())
		  System.out.println("The car with plate "+sLicensePlate+" "+p2.evaluate()+" be on the road");
		else
			System.out.println(p2.evaluate());
		
		// Sample 3: On Pico & Placa
		sLicensePlate = "ABC1230";
		sDate = "2018-01-12";
		sTime = "16:00";
		
		Predictor p3 = new Predictor(sLicensePlate, sDate, sTime);
		System.out.println("The car with plate "+sLicensePlate+" "+p3.evaluate()+" be on the road");
		
		// Sample 4: Incomplete Plate
		sLicensePlate = "AB1230";
		sDate = "2018-01-12";
		sTime = "16:00";
		
		Predictor p4 = new Predictor(sLicensePlate, sDate, sTime);
		System.out.println("The car with plate "+sLicensePlate+" "+p4.evaluate()+" be on the road");
		
		// Sample 5: The car cannot be on road
		sLicensePlate = "XBA1238";
		sDate = "2018-01-11";
		sTime = "16:01";
		
		Predictor p5 = new Predictor(sLicensePlate, sDate, sTime);
		System.out.println("The car with plate "+sLicensePlate+" "+p5.evaluate()+" be on the road");
		
		
	}

}
