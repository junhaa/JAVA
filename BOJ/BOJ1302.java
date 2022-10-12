import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// BOJ #1302 베스트셀러
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;
		String answer = "";
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N ; i ++) {
			String tmp = br.readLine();
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		for(String x : map.keySet()) {
			if(map.get(x) > max) {
				max = map.get(x);
				answer = x;
			}
			else if(map.get(x) == max && x.compareTo(answer) < 0) {
				answer = x;
			}
		}
		System.out.println(answer);
	}
}
