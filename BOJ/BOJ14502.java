import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14502 연구소
class Point{
	byte x;
	byte y;
	public Point(byte x, byte y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static ArrayList<Point> list = new ArrayList<>();
	static ArrayList<Point> blist = new ArrayList<>();
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static int cnt, N, M, answer = Integer.MIN_VALUE, blank = 0;
	
	
	static void DFS(int x, int y) {
		visited[y][x] = true;
		cnt ++;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx >= 0 && yy >= 0 && xx < M && yy < N && map[yy][xx] == 0 && !visited[yy][xx]) {
				DFS(xx, yy);
			}
		}
	}
	
	static void getAnswer() {
		visited = new boolean[N][M];
		cnt = 0;
		for(int j = 0 ; j < list.size() ; j ++) {
			if(visited[list.get(j).y][list.get(j).x]) continue;
			DFS(list.get(j).x, list.get(j).y);
			
		}
		answer = Math.max(answer, blank - 3 - cnt + list.size());
	}
	
	static void solution() {
		Point p1 = null, p2 = null, p3 = null; // 첫 번째로 세우는 벽 위치
		boolean flag = false;
		for(int i = 0; i < blist.size() - 2 ; i ++) { 
			if(p1 != null) map[p1.y][p1.x] = 0;
			p1 = blist.get(i);
			map[p1.y][p1.x] = 1;
			for(int k = i + 1 ; k < blist.size() - 1 ; k ++) {
				if(p2 != null) map[p2.y][p2.x] = 0;
				p2 = blist.get(k);
				map[p2.y][p2.x] = 1;
				for(int j = k + 1 ; j < blist.size() ; j ++) {
					if(p3 != null) map[p3.y][p3.x] = 0;
					p3 = blist.get(j);
					map[p3.y][p3.x] = 1;
					getAnswer();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(byte i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(byte k = 0 ; k < M ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
				if(map[i][k] == 2) list.add(new Point(k, i));
				else if(map[i][k] == 0) { 
					blist.add(new Point(k, i));
					blank ++;
				}
			}
		}
		T.solution();
		System.out.println(answer);
	}
}
