import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2206 벽 부수고 이동하기
class Point {
	int x;
	int y;
	int flag;
	public Point(int y, int x, int flag) { // 0 == false 1 == true
		this.x = x;
		this.y = y;
		this.flag = flag;
	}
}

public class Main {
	static int[][] map;
	static int[] mx = {0, 1, 0 ,-1};
	static int[] my = {-1, 0, 1, 0};
	static int N, M;
	
	public static int solution() {
		int[][][] ch = new int[N][M][2];
		Queue<Point> Q = new LinkedList<>();
		int answer = 1;
		Q.offer(new Point(0,0,0));
		ch[0][0][0] = 1;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(tmp.x == M - 1 && tmp.y == N - 1) return answer;
				for(int k = 0 ; k < 4 ; k ++) {
					int xx = mx[k] + tmp.x;
					int yy = my[k] + tmp.y;
					if(xx >= 0 && xx < M && yy >= 0 && yy < N && ch[yy][xx][tmp.flag] == 0) {
						if(map[yy][xx] == 0) {
							Q.offer(new Point(yy,xx,tmp.flag));
							ch[yy][xx][tmp.flag] = 1;
						}
						else if(map[yy][xx] == 1 && tmp.flag == 0) {
							Q.offer(new Point(yy,xx,1));
							ch[yy][xx][tmp.flag] = 1;
						}
					}
				}
			}
			answer++;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = str.charAt(k) - '0';
			}
		}
		System.out.println(T.solution());
	}
}
