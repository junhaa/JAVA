import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #2480 주사위 세개
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		int answer = 0;
		for(int i = 0 ; i < 3 ; i ++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		if(map.keySet().size() == 3) {
			int max = Integer.MIN_VALUE;
			for(int x : map.keySet()) {
				max = Math.max(x, max);
			}
			System.out.println(max * 100);
		}
		else if(map.keySet().size() == 2) {
			for(int x : map.keySet()) {
				if(map.get(x) == 2) System.out.println(1000 + x * 100);
			}
		}
		else {
			for(int x : map.keySet()) System.out.println(10000 + 1000 * x);
		}
	}
}
