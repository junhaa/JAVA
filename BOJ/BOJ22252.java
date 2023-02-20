import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #22252 정보 상인 호석
public class Main {

	static long answer = 0;
	static HashMap<String, PriorityQueue<Integer>> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				String name = st.nextToken();
				int len = Integer.parseInt(st.nextToken());
				if(!map.containsKey(name)) {
					PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
					for(int j = 0 ; j < len ; j ++) {
						tmp.add(Integer.parseInt(st.nextToken()));
					}
					map.put(name, tmp);
				}
				else {
					for(int j = 0 ; j < len ; j ++) {
						map.get(name).add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			else {
				String name = st.nextToken();
				if(!map.containsKey(name)) continue;
				int cnt = Integer.parseInt(st.nextToken());
				for(int j = 0 ; j < cnt ; j ++) {
					if(map.get(name).isEmpty()) break;
					answer += map.get(name).poll();
				}
			}
		}
		System.out.println(answer);
	}
}
