import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #17071 숨바꼭질 5
public class Main {
	static int solution(int N, int K) {
		Queue<Integer> Q = new LinkedList<>();
		boolean[][] visited = new boolean[2][500001];
		Q.offer(N);
		int T = 0; // 시간
		int num = 0;
		int cnt = 0;
		visited[0][N] = true;
		while(!Q.isEmpty()) {
			int length = Q.size();
			if(visited[T % 2][K + num]) return T;
			num += ++cnt;
			if(K + num > 500000) return -1;
			T++;
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp > 0 && !visited[T % 2][tmp - 1]) {
					Q.offer(tmp - 1);
					visited[T % 2][tmp - 1] = true;
				}
				if(tmp < 500000 && !visited[T % 2][tmp + 1]) {
					Q.offer(tmp + 1);
					visited[T % 2][tmp + 1] = true;
				}
				if(tmp <= 250000 && !visited[T % 2][tmp * 2]) {
					Q.offer(tmp * 2);
					visited[T % 2][tmp * 2] = true;
				}
			}
		}
		return -1;
	}
	
	
	/*
	static int solution(int N, int K) {
		if(N == K) return 0;
		boolean[] visited = new boolean[1000001];
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(N);
		visited[N + 500000] = true;
		int num = 0;
		int L = 0;
		int cnt = 1;
		while(!Q.isEmpty()) {
			if(K + num > 500000) return -1;
			int length = Q.size();
			num += cnt++;
			L ++;
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp > 0 && tmp - 1 - num + 500000 >= 0 && !visited[tmp - 1 - num + 500000]) {
					if(tmp - 1 - num == K) return L; 
					Q.offer(tmp - 1);
					visited[tmp - 1 - num + 500000] = true;
				}
				if(tmp < 500000 && tmp + 1 - num + 500000 >= 0 && !visited[tmp + 1 - num + 500000]) {
					if(tmp + 1 - num == K) return L; 
					Q.offer(tmp + 1);
					visited[tmp + 1 - num + 500000] = true;
				}
				if (tmp <= 250000 && tmp + 1 - num + 500000 >= 0 && !visited[tmp * 2 - num + 500000]) {
					if(tmp * 2 - num == K) return L; 
					Q.offer(tmp * 2);
					visited[tmp * 2 - num + 500000] = true;
				}
			}	
		}
		return -1;
	}
	*/
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int K = Integer.parseInt(st.nextToken()); // 동생의 위치
		System.out.println(T.solution(N, K));
	}
}	
