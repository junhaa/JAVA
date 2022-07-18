import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

// BOJ #2108 ≈Î∞Ë«–
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		int sum = 0;
		int mid = 0;
		int cnt = 0;
		int range;
		int max = Integer.MIN_VALUE;
		int mode = 0; 
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			list.add(tmp);
		}
		Collections.sort(list);
		for(int x : list) {
			map.put(x, map.getOrDefault(x, 0) + 1);
		}

		for(int x : map.keySet()) {
			cnt += map.get(x);
			if(cnt >= N / 2 + 1) {
				cnt = Integer.MIN_VALUE;
				mid = x;
			}
			sum += map.get(x) * x;
			if(max < map.get(x)) {
				mode = x;
				max = map.get(x);
			}
		}
		for(int x : map.keySet()) {
			if(x != mode && map.get(x) == max) {
				mode = x;
				break;
			}
		}
		sb.append(Math.round((double)sum / N)).append('\n');
		sb.append(mid).append('\n');
		sb.append(mode).append('\n');
		sb.append(Math.abs(list.get(list.size() - 1) - list.get(0)));
		
		System.out.println(sb);
	}
}
