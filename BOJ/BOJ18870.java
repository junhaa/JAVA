import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #18870 좌표 압축
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> input = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			list.add(tmp);
			input.add(tmp);
		}
		Collections.sort(list);
		int idx = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(map.containsKey(list.get(i))) continue;
			map.put(list.get(i), idx ++);
		}
		for(int tmp : input) {
			sb.append(map.get(tmp) + " ");
		}
		System.out.println(sb);
	}
}
