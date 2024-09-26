import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
// BOJ #2015 수들의 합 4
public class Main {
	static int[] pSum;
	static Map<Integer, Integer> map = new HashMap<>();
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pSum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++){
			pSum[i + 1] = pSum[i] + Integer.parseInt(st.nextToken());
		}

		long answer = 0;

		map.put(0, 1);

		for(int i = 1 ; i <= N ; i ++){
			int need = pSum[i] - K;
			answer += map.getOrDefault(need, 0);
			map.put(pSum[i], map.getOrDefault(pSum[i], 0) + 1);
		}

		System.out.println(answer);
	}
}
