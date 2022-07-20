import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ #2178 ¹Ì·Î Å½»ö
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][] map;
	static int[] mx = {0 , 1, 0, -1};
	static int[] my = {-1, 0, 1 ,0};
	public static int solution(int N, int M) {
		int[][] ch = new int[N][M];
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0,0));
		int answer = 1;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int k = 0 ; k < length ; k ++) {
				Point tmp = Q.poll();
				if(tmp.y == N - 1 && tmp.x == M - 1) return answer;
				for(int i = 0 ; i < 4 ; i ++) {
					int xx = tmp.x + mx[i];
					int yy = tmp.y + my[i];
					if(xx >= 0 && xx < M && yy >= 0 && yy < N && map[yy][xx] == 1 && ch[yy][xx] == 0) {
						Q.offer(new Point(xx,yy));
						ch[yy][xx] = 1;
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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = str.charAt(k) - '0';
			}
		}
		System.out.println(T.solution(N, M));
	}
	
}
