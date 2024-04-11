import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15664 N과 M (10)
public class Main {
	static int N, M, arrL = 0;
	static int[] select, arr, idx;

	static StringBuilder sb = new StringBuilder();

	static void recursive(int len, int last) {
		if (len == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = last; i < arrL; i++) {
			if (arr[idx[i]] == 0)
				continue;
			select[len] = idx[i];
			arr[idx[i]]--;
			recursive(len + 1, i);
			arr[idx[i]]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new int[M];
		arr = new int[10001];
		idx = new int[8];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[Integer.parseInt(st.nextToken())]++;
		}
		for (int i = 0; i <= 10000; i++) {
			if (arr[i] != 0) {
				idx[arrL] = i;
				arrL++;
			}
		}

		for (int i = 0; i < arrL; i++) {
			select[0] = idx[i];
			arr[idx[i]]--;
			recursive(1, i);
			arr[idx[i]]++;
		}
		System.out.println(sb);
	}
}
