import java.util.LinkedHashMap;
import java.util.Scanner; 

public class GetChnageProblem {
          
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		double moneyThatBuyerGivesInRupees = sc.nextDouble();
		double priceOfTheItemHePurchases = sc.nextDouble();
		
		double[] arrayOfDenomination = {1, 0.5, 0.25, 0.1, 0.05, 0.01};
		
		LinkedHashMap<Double, Integer> map = new LinkedHashMap<>();
		
		for(int i=0; i<arrayOfDenomination.length; i++) {
			map.put(arrayOfDenomination[i], 0);
		}
	
		System.out.println(getChange(moneyThatBuyerGivesInRupees, priceOfTheItemHePurchases, map, arrayOfDenomination));

	}
	
	static double getDecimalFormat(double val) {
		return Math.round((val) * 100) / 100.0;
	}
                
	static String getChange(double x, double y, LinkedHashMap<Double, Integer> map, double[] arr) {

		String result = "";
		double exactAmountToDisburse = getDecimalFormat((x - y));

        for(int i=0; i<arr.length; i++) {
        	while(exactAmountToDisburse >= arr[i]) {
        		exactAmountToDisburse = getDecimalFormat((exactAmountToDisburse - arr[i]));
        		map.put(arr[i], map.get(arr[i]) + 1);
        	}
        	
        	if(exactAmountToDisburse == 0) {
        		break;
        	}
        	
        }
        
        String temp = "]";
        
        for(Integer i: map.values()) {
        	temp += String.valueOf(i) + ",";
        }

        temp = temp.substring(0, temp.length()-1) + "[";

        for(int i=temp.length()-1; i>=0; i--) {
        	result += String.valueOf(temp.charAt(i));
        }                             

        return result;

	}
}
