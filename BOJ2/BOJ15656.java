import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #15656 N과 M (7)
public class Main {
	static int N, M;
	static int[] select, arr;

	static StringBuilder sb = new StringBuilder();

	static void recursive(int len) {
		if (len == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			select[len] = arr[i];
			recursive(len + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new int[M];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			select[0] = arr[i];
			recursive(1);
		}
		System.out.println(sb);
	}
}
