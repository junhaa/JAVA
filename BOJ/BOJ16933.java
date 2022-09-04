import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16933 벽 부수고 이동하기 3
class Point{
	int x;
	int y;
	int wall;
	public Point(int x, int y, int wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
}
public class Main {
	
	static boolean[][] map;
	static int[][] ch;
	
	static int solution(int N, int M, int K) { // BFS
		Queue<Point> Q = new LinkedList<>();
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		int answer = 0; // answer % 2 == 0 이면 낮, 1이면 밤
		Q.offer(new Point(0, 0, K));
		ch[0][0] = K;
		
		while(!Q.isEmpty()) {
			int length = Q.size();
			int time = answer % 2;
			for (int i = 0; i < length; i++) {
				Point tmp = Q.poll();
				if (tmp.x == M - 1 && tmp.y == N - 1) return ++answer;
				if(time == 1) Q.offer(tmp);
				for(int j = 0 ; j < 4 ; j ++) {
					int xx = tmp.x + dx[j];
					int yy = tmp.y + dy[j];
					if(yy >= 0 && yy < N && xx >= 0 && xx < M) {
						if(map[yy][xx] && time == 0 && tmp.wall > 1 && ch[yy][xx] < tmp.wall - 1) { // 벽이 있고 낮이면
							ch[yy][xx] = tmp.wall - 1;
							Q.offer(new Point(xx, yy, tmp.wall - 1));
						}
						else if(!map[yy][xx] && ch[yy][xx] < tmp.wall) {
							ch[yy][xx] = tmp.wall;
							Q.offer(new Point(xx, yy, tmp.wall));
						}
					}
				}
			}
			answer ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		ch = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				if(input.charAt(k) == '1') map[i][k] = true;
			}
		}
		System.out.println(T.solution(N, M, K + 1));
		
	}
}
