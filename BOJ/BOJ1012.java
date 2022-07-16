import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #1012 ¿Ø±‚≥Û πË√ﬂ
class Point {
	int row;
	int col;
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
public class Main {
	static int[][] map;
	static int[] mx = {0, 1, 0, -1};
	static int[] my = {-1, 0, 1, 0};
	static int N, M;
	public static void DFS(int row, int col) {
		map[row][col] = -1;
		for(int i = 0 ; i < 4 ; i ++) {
			int yy = row + my[i];
			int xx = col + mx[i];
			if(xx >= 0 && xx < M && yy >= 0 && yy < N && map[yy][xx] == 1) {
				DFS(yy,xx);
			}
		}
	}
	
	public static int solution(ArrayList<Point> list, int[][] map) {
		int answer = 0;
		for(Point x : list) {
			if(map[x.row][x.col] == 1) {
				answer ++;
				DFS(x.row,x.col);
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(test-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int [N][M];
			ArrayList<Point> list = new ArrayList<>();
			for(int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1;
				list.add(new Point(Y, X));
			}
			sb.append(T.solution(list, map)).append('\n');
		}
		System.out.println(sb);
	}
}
