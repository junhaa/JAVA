import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16948 데스 나이트 
class Point{
	int r, c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
public class Main {
	static boolean visited[][];

	static int BFS(int r1, int c1, int r2, int c2, int N) {
		Queue<Point> Q = new LinkedList<>();
		int[] dx = { -1, 1, -2, 2, -1, 1 };
		int[] dy = { -2, -2, 0, 0, 2, 2 };
		Q.offer(new Point(r1, c1));
		int L = 0 ;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(tmp.r == r2 && tmp.c == c2) return L;
				for(int k = 0 ; k < 6 ; k ++) {
					int xx = tmp.c + dx[k];
					int yy = tmp.r + dy[k];
					if(xx < N && yy < N && xx >= 0 && yy >= 0 && !visited[yy][xx]) {
						Q.offer(new Point(yy, xx));
						visited[yy][xx] = true;
					}
				}
			}
			L++;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		int r1, c1 ,r2, c2;
		StringTokenizer st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		System.out.println(T.BFS(r1, c1, r2, c2, N));
	}
}
