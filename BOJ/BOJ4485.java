import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #4485 ³ì»ö ¿Ê ÀÔÀº ¾Ö°¡ Á©´ÙÁö?
class Edge implements Comparable<Edge>{
	int x;
	int y;
	int cost;
	public Edge(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[][] map, d;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N;
	static StringBuilder sb = new StringBuilder();

	static void dijkstra(int num) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		d[0][0] = map[0][0];
		pQ.offer(new Edge(0,0,d[0][0]));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(tmp.cost > d[tmp.y][tmp.x]) continue;
			for(int i = 0 ; i < 4 ; i ++) {
				int xx = tmp.x + dx[i];
				int yy = tmp.y + dy[i];
				if(yy >= 0 && xx >= 0 && yy < N && xx < N) {
					if(d[yy][xx] > tmp.cost + map[yy][xx]) {
						d[yy][xx] = tmp.cost + map[yy][xx];
						pQ.offer(new Edge(xx, yy, d[yy][xx]));
					}
				}
			}
		}
		sb.append("Problem ").append(num).append(": ").append(d[N - 1][N - 1]).append('\n');
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int num = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			d = new int[N][N];
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < N ; k ++) {
					map[i][k] = Integer.parseInt(st.nextToken());
					d[i][k] = Integer.MAX_VALUE;
				}
			}
			T.dijkstra(num);
			num ++;
		}
		System.out.println(sb);
	}
}
