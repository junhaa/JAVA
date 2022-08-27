import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #13549 ¼û¹Ù²ÀÁú 3
class Point implements Comparable<Point>{
	int x;
	int cnt;
	public Point(int x, int cnt) {
		this.x = x;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Point o) {
		if(this.cnt == o.cnt) return this.x - o.x;
		else return this.cnt - o.cnt;
	}
}
public class Main {
	
	static boolean[] visited = new boolean[100001];
	
	static int solution(int N, int K) {
		if(N > K) return N - K;
		if(N == K) return 0;
		PriorityQueue<Point> pQ = new PriorityQueue<>();
		pQ.offer(new Point(N, 0));
		visited[N] = true;
		int answer = Integer.MAX_VALUE;
		while(!pQ.isEmpty()) {
			Point tmp = pQ.poll();
			if(tmp.x == K) answer = Math.min(answer, tmp.cnt);
			if(tmp.x * 2 <= 100000 && !visited[tmp.x * 2]) {
				pQ.offer(new Point(tmp.x * 2, tmp.cnt));
				visited[tmp.x * 2] = true;
			}
			if(tmp.x > 0 && !visited[tmp.x - 1] ) {
				pQ.offer(new Point(tmp.x - 1, tmp.cnt + 1));
				visited[tmp.x - 1] = true;
			}
			if(tmp.x + 1 <= 100000 && !visited[tmp.x + 1]) {
				pQ.offer(new Point(tmp.x + 1, tmp.cnt + 1));
				visited[tmp.x + 1] = true;
			}
			
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ¼öºó
		int K = Integer.parseInt(st.nextToken()); // µ¿»ý
		System.out.println(T.solution(N, K));
	}
}
