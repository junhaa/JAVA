import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15649 N과 M (1)
public class Main {
	static int N, M;
	static int[] select;
	static boolean[] visited;

	static StringBuilder sb = new StringBuilder();

	static void recursive(int len) {
		if (len == M) {
			for (int i = 0; i < M; i++) {
				sb.append(select[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				select[len] = i;
				visited[i] = true;
				recursive(len + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new int[M];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			select[0] = i;
			recursive(1);
			visited[i] = false;
		}
		System.out.println(sb);
	}
}
