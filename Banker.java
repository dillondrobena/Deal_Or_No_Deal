
import java.util.*;

public class Banker {
	public double getDeal(int round, List<String> options){
		double offer = 0;
		String[] temp = new String[options.size()];
		double total = 0;
		options.toArray(temp);
		for (int i = 0; i < temp.length; i++){
			total += Double.parseDouble(temp[i]);
		}
		offer = (double) (total / temp.length) * ((double)round / 10);
		return offer;
	}
}
