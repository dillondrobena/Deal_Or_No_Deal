import java.util.*;
public class Cases {

	public String[] passableMoney = new String[]{"0.01", "1.0", "5.0", "10.0", "25.0", "50.0", "75.0", "100.0", "200.0", "300.0", "400.0", "500.0", "750.0", "1000.0", "5000.0", "10000.0", "25000.0", "50000.0", "75000.0", "100000.0", "200000.0", "300000.0", "400000.0", "500000.0", "750000.0", "1000000.0"};
	
	public List<String> getRandomCash(){
		String[] tempMoney = new String[]{"0.01", "1.0", "5.0", "10.0", "25.0", "50.0", "75.0", "100.0", "200.0", "300.0", "400.0", "500.0", "750.0", "1000.0", "5000.0", "10000.0", "25000.0", "50000.0", "75000.0", "100000.0", "200000.0", "300000.0", "400000.0", "500000.0", "750000.0", "1000000.0"};
		double[] money = new double[26];
		List<String> temp = new ArrayList<String>(Arrays.asList(tempMoney));
		Collections.shuffle(temp);
		temp.toArray(tempMoney);
		for (int i = 0; i < money.length; i++){
			money[i] = Double.parseDouble(tempMoney[i]);
		}
		return temp;
	}
}
