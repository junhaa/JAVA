import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #10026 적록색약
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	
	static void DFS(int x, int y, char tmp) {
		visited[y][x] = true;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && map[yy][xx] == tmp) {
				DFS(xx, yy, tmp);
			}
		}
	}
	static void Blind(int x, int y, char tmp) {
		visited[y][x] = true;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx]) {
				if(tmp == 'B' && map[yy][xx] == 'B') Blind(xx, yy, tmp);
				else if(tmp != 'B' && map[yy][xx] != 'B') Blind(xx, yy, tmp);
			}
		}
	}
	
	static void solution() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(!visited[i][k]) { 
					cnt ++;
					DFS(k, i, map[i][k]);
				}
			}
		}
		sb.append(cnt).append(" ");
		visited = new boolean[N][N];
		cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(!visited[i][k]) { 
					cnt ++;
					Blind(k, i, map[i][k]);
				}
			}
		}
		sb.append(cnt);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = input.charAt(k);
			}
		}
		T.solution();
		System.out.println(sb);
	}
}
