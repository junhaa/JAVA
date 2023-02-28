import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #22352 항체 인식
public class Main {
	
	static int N, M;
	static int[][] map1, map2;
	static int cx = -1, cy = -1;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	static boolean check() {
		boolean flag = true;
		int num = 2221;
		int last = -1;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map1[i][j] != map2[i][j]) {
					if(map2[i][j] != last) {
						num ++;
					}
					map2[i][j] = num;
					if(cx == -1 && cy == -1) {
						cx = j;
						cy = i;
					}
					flag = false;
				}
			}
		}
		return flag;
	}
	
	static void DFS(int y, int x, int start) {
		map1[y][x] = 2222;
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < N && ny >= 0 && nx < M && nx >= 0 && map1[ny][nx] == start) {
				DFS(ny, nx, start);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map1 = new int[N][M];
		map2 = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(check()) {
			System.out.println("YES");
			return;
		}
		DFS(cy, cx, map1[cy][cx]);
		if(check()) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
}
