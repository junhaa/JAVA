import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #7510 고급 수학
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append("Scenario #" + i + ":\n");
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> list = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list);
			if ((list.get(2) * list.get(2)) == (list.get(1) * list.get(1)) + (list.get(0) * list.get(0))) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
