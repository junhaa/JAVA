import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #13913 숨바꼭질 4
public class Main {

	static int[] move = new int[100001];
	static boolean[] visited = new boolean[100001];
	static StringBuilder sb = new StringBuilder();
	
	
	static void BFS(int start, int end) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(start);
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length; i ++) {
				int tmp = Q.poll();
				if(tmp == end) {
					sb.append(L + "\n");
					return;
				}
				if(tmp + 1 <= 100000 && !visited[tmp + 1]) {
					move[tmp + 1] = tmp;
					visited[tmp + 1] = true;
					Q.offer(tmp + 1);
				}
				if(tmp - 1 >= 0 && !visited[tmp - 1]) {
					move[tmp - 1] = tmp;
					visited[tmp - 1] = true;
					Q.offer(tmp - 1);
				}
				if(tmp * 2 <= 100000 && !visited[tmp * 2]) {
					move[tmp * 2] = tmp;
					visited[tmp * 2] = true;
					Q.offer(tmp * 2);
				}
			}
			L ++;
		}
	}
	
	static void backTracking(int N, int K) {
		if(K == N) {
			sb.append(K + " ");
			return;
		}
		backTracking(N, move[K]);
		sb.append(K + " ");
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Arrays.fill(move, -1);
		T.BFS(N, K);
		T.backTracking(N, K);
		System.out.println(sb);
	}
}
