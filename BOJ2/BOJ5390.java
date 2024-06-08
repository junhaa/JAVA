import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// BOJ #5390 Gene Shuffle
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer[] st = new StringTokenizer[2];

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st[0] = new StringTokenizer(br.readLine());
			st[1] = new StringTokenizer(br.readLine());

			Set<Integer> set = new HashSet<>();
			int start = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					int cur = Integer.parseInt(st[j].nextToken());
					if (set.isEmpty()) {
						set.add(cur);
					} else if (set.contains(cur)) {
						set.remove(cur);
						if (set.isEmpty()) {
							sb.append((start + 1) + "-" + (i + 1) + " "); // 1 -based
							start = i + 1;
						}
					} else {
						set.add(cur);
					}

				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
