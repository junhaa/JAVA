import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #8978 VLAK
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][26];
		int[] train = new int[N];
		for (int i = 0; i < P; i++) {
			int cur = br.readLine().charAt(0) - 'a';
			int min = Integer.MAX_VALUE;
			int idx = 0;
			int trainMin = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (train[j] == K)
					continue;
				if (arr[j][cur] < min) {
					min = arr[j][cur];
					idx = j;
					trainMin = train[idx];
				} else if (arr[j][cur] == min && train[j] < trainMin) {
					min = arr[j][cur];
					idx = j;
					trainMin = train[idx];
				}
			}
			arr[idx][cur]++;
			train[idx]++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < 26; j++) {
				sum += arr[i][j];
			}
			sb.append(sum + " ");
		}
		System.out.println(sb);

	}
}
