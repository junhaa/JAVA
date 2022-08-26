import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #7576 토마토
class Point{
	int x;
	int y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int map[][];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Queue<Point> Q = new LinkedList<>();
	static int blank = 0;
	
	static int solution(int N, int M) {
		int cnt = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				for(int k = 0 ; k < 4 ; k ++) {
					int xx = tmp.x + dx[k];
					int yy = tmp.y + dy[k];
					if(xx >= 0 && yy >= 0 && xx < M && yy < N && map[yy][xx] == 0) {
						blank --;
						map[yy][xx] = 1;
						Q.offer(new Point(yy, xx));
						if(blank == 0) return ++cnt;
					}
				}
			}
			cnt ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 가로
		int N = Integer.parseInt(st.nextToken()); // 세로 
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < M ; k ++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 1) Q.offer(new Point(i, k));
				else if(input == 0) blank ++;
				map[i][k] = input;
			}
		}
		if(blank == 0) System.out.println(0);
		else System.out.println(T.solution(N, M));
	}
}
