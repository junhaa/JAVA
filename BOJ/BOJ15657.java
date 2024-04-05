import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #15657 N과 M (8)
public class Main {
	static int N, M;
	static int[] select, arr;

	static StringBuilder sb = new StringBuilder();

	static void recursive(int len, int last) {
		if (len == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = last; i < N; i++) {
			select[len] = arr[i];
			recursive(len + 1, i);
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
			recursive(1, i);
		}
		System.out.println(sb);
	}
}
