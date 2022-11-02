import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16234 인구 이동
public class Main {
	
	static int[][] map;
	static int[][] ch;
	static boolean[][] visited;
	static int sum, cnt, answer = 0, L, R, N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean flag;
	
	static void DFS(int y, int x) {
		visited[y][x] = true;
		cnt++;
		sum += map[y][x];
		ch[y][x] = -1;
		for(int i = 0 ; i < 4;  i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx < N && yy < N && xx >= 0 && yy >= 0 && !visited[yy][xx]) {
				if(L <= Math.abs(map[y][x] - map[yy][xx]) && R >= Math.abs(map[y][x] - map[yy][xx])) { 
					flag = true;
					DFS(yy, xx);
				}
			}
		}
	}
	
	static void changeDFS(int y, int x) {
		ch[y][x] = sum / cnt;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx < N && yy < N && xx >= 0 && yy >= 0 && ch[yy][xx] == -1) {
				changeDFS(yy,xx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ch = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			flag = false;
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(!visited[i][k]) {
						sum = 0;
						cnt = 0;
						DFS(i, k);
						changeDFS(i,k);
					}
				}
			}
			if(!flag) break;
			map = ch;
			ch = new int[N][N];
			visited = new boolean[N][N];
			answer ++;
		}
		System.out.println(answer);
	}

}
