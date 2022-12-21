import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// BOJ #11652 카드
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<Long, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N ; i ++) {
			long tmp = Long.parseLong(br.readLine());
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		int maxCnt = Integer.MIN_VALUE;
		long maxNum = 0;
		for(Long key : map.keySet()) {
			if(map.get(key) > maxCnt) {
				maxNum = key;
				maxCnt = map.get(key);
			}
			else if(map.get(key) == maxCnt) {
				maxNum = Math.min(maxNum, key);
			}
		}
		System.out.println(maxNum);
	}
}
