import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #25206 너의 평점은 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Double> map  = new HashMap<String, Double>();
		map.put("A+", 4.5);
		map.put("A0", 4.0);
		map.put("B+", 3.5);
		map.put("B0", 3.0);
		map.put("C+", 2.5);
		map.put("C0", 2.0);
		map.put("D+", 1.5);
		map.put("D0", 1.0);
		map.put("F", 0.0);
		
		double sa = 0;
		double sn = 0;
		StringTokenizer st;
		for(int i = 0 ; i < 20 ; i ++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			double tn = Double.parseDouble(st.nextToken()); 
			String tm = st.nextToken();
			if(tm.equals("P")) continue;
			sn += tn;
			sa += map.get(tm) * tn;
		}
		System.out.println(sa / sn);
	}
}
