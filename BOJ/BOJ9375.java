import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #9375 패션왕 신해빈
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			HashMap<String, HashSet<String>> map = new HashMap<>();
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				if(!map.containsKey(b)) {
					map.put(b, new HashSet<>());
				}
				map.get(b).add(a);
			}
			int ans = 1;
			for(String str : map.keySet()) {
				ans *= map.get(str).size() + 1;
			}
			sb.append(ans - 1 + "\n");
		}
		System.out.println(sb);
	}
}
