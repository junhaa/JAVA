import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
// BOJ #12254 Charging Chaos (Small)
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int min = Integer.MAX_VALUE;
			List<Integer> list = new ArrayList<>();
			List<Integer> answerList = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken(), 2));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				answerList.add(Integer.parseInt(st.nextToken(), 2));
			}

			Collections.sort(answerList);

			for (int i = 0; i < (1 << L); i++) {
				List<Integer> curList = new ArrayList<>();
				for (Integer a : list) {
					curList.add(a ^ i);
				}

				Collections.sort(curList);

				if (curList.equals(answerList)) {
					min = Math.min(min, getBitCount(i, L));
				}
			}
			sb.append("Case #" + (t + 1) + ": ");
			sb.append(min == Integer.MAX_VALUE ? "NOT POSSIBLE" : min);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int getBitCount(int num, int L) {
		int count = 0;
		for (int i = 0; i < L; i++) {
			if (((num >> i) & 1) == 1) {
				count++;
			}
		}
		return count;
	}
}
