import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #3009 네 번째 점
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> mapX = new HashMap<>();
		HashMap<Integer, Integer> mapY = new HashMap<>();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < 3 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			mapX.put(tmp1, mapX.getOrDefault(tmp1, 0) + 1);
			int tmp2 = Integer.parseInt(st.nextToken());
			mapY.put(tmp2, mapY.getOrDefault(tmp2, 0) + 1);
		}
		for(int x : mapX.keySet()) {
			if(mapX.get(x) % 2 == 1) {
				sb.append(x + " ");
				break;
			}
		}
		for(int y : mapY.keySet()) {
			if(mapY.get(y) % 2 == 1) {
				sb.append(y);
				break;
			}
		}
		System.out.println(sb);
	}
}
