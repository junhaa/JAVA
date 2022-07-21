import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2146 다리 만들기
class Point{
	int x ;
	int y ;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {
	static int[][] map;
	static int[][] ch;
	static int[] mx = { 0, 1, 0, -1 };
	static int[] my = { -1, 0, 1, 0 };
	static int N, land = 2, cnt = 1;
	static Queue<Point> Q = new LinkedList<>();
	public static void DFS(int y, int x) {
		map[y][x] = land;
		
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + mx[i];
			int yy = y + my[i];
			if(xx >= 0 && xx < N && yy >= 0 && yy < N && ch[yy][xx] == 0) {
				ch[yy][xx] = 1;
				if( map[yy][xx] == 1 ) DFS(yy,xx);
				else if( map[yy][xx] == 0) Q.offer(new Point(yy,xx));
			}
		}
	}
	
	public static int BFS() {
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int l = 0 ; l < length ; l ++) {
				Point tmp = Q.poll();
				for(int j = 0 ; j < 4 ; j ++) {
					int xx = tmp.x + mx[j];
					int yy = tmp.y + my[j];
					if(xx >= 0 && xx < N && yy >= 0 && yy < N && ch[yy][xx] == 0) {
						if(map[yy][xx] == 1) return cnt;
						else {
							Q.offer(new Point(yy,xx));
							ch[yy][xx] = 1;
						}
					}
				}
			}
			cnt ++;
		}
		return Integer.MAX_VALUE;
	}
	
	public static int solution() {
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(map[i][k] == 1) {
					cnt = 1; 
					DFS(i, k); // ch 사용
					land ++;
					min = Math.min(min, BFS());
					Q.clear();
					ch = new int[N][N];
				}
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ch = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution());
	}
}
