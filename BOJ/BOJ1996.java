import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// BOJ #1996 지뢰 찾기
class Point{
	int y, x, val;
	public Point(int y, int x, int val) {
		this.y = y;
		this.x = x;
		this.val = val;
	}
}
public class Main {

	static int map[][];
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static void solution(ArrayList<Point> list, int N) {
		for(Point x : list) {
			for(int i = 0 ; i < 8 ; i ++) {
				int nx = x.x + dx[i];
				int ny = x.y + dy[i];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					map[ny][nx] += x.val;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < N ; j ++) {
				if(input.charAt(j) != '.') {
					list.add(new Point(i, j, input.charAt(j) - '0'));
				}
			}
		}
		T.solution(list, N);
		for(Point t : list) {
			map[t.y][t.x] = -1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				if(map[i][j] >= 10) sb.append('M');
				else if(map[i][j] >= 0) sb.append(map[i][j]);
				else sb.append('*');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
