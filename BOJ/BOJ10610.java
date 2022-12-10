import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

// BOJ #10610 30
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String N = br.readLine();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N.length() ; i ++) {
			int tmp = N.charAt(i) - '0';
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		if(!map.containsKey(0)) {
			System.out.println(-1);
			return;
		}
		int sum = 0;
		for(int x : map.keySet()) {
			sum += map.get(x) * x; 
		}
		if(sum % 3 != 0) {
			System.out.println(-1);
			return;
		}
		for(int i = 9 ; i >= 0 ; i --) {
			if(map.containsKey(i)) {
				bw.write(String.valueOf(i).repeat(map.get(i)));
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
