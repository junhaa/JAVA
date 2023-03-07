import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16197 두 동전 
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
class Coin{
	Point c1, c2;
	public Coin(Point p1, Point p2) {
		c1 = p1;
		c2 = p2;
	}
}
public class Main {

	static char[][] map;
	static int N, M;
	static ArrayList<Point> coin = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	static int fall(Point p) {
		if(p.x < 0 || p.x >= M || p.y < 0 || p.y >= N) return 1;
		else return 0;
	}
	
	static int BFS() {
		Queue<Coin> Q = new LinkedList<>();
		Q.offer(new Coin(coin.get(0), coin.get(1)));
		
		int L = 0;
		while(!Q.isEmpty() && L <= 10) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Coin tmp = Q.poll();
				if(fall(tmp.c1) + fall(tmp.c2) == 1) {
					return L;
				}
				else if(fall(tmp.c1) + fall(tmp.c2) == 2) {
					continue;
				}
				for(int j = 0 ; j < 4 ; j ++) {
					int nx1 = tmp.c1.x + dx[j];
					int ny1 = tmp.c1.y + dy[j];
					int nx2 = tmp.c2.x + dx[j];
					int ny2 = tmp.c2.y + dy[j];
					
					if((nx1 >= 0 && nx1 < M && ny1 >= 0 && ny1 < N) && map[ny1][nx1] == '#') {
						nx1 = tmp.c1.x;
						ny1 = tmp.c1.y;
					}
					if((nx2 >= 0 && nx2 < M && ny2 >= 0 && ny2 < N) && map[ny2][nx2] == '#') {
						nx2 = tmp.c2.x;
						ny2 = tmp.c2.y;
					}
					Q.offer(new Coin(new Point(ny1, nx1), new Point(ny2, nx2)));
				}
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'o') {
					coin.add(new Point(i, j));
					map[i][j] = '.';
				}
			}
		}
		System.out.println(T.BFS());
	}
}
