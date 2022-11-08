import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2573 빙산
public class Main {

	static int[][] map, melting;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1 ,0 };
	static boolean[][] visited;
	static int sep = 0;
	
	
	static void melt(int y, int x) {
		int sum = 0;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(map[yy][xx] == 0) sum ++;
		}
		melting[y][x] += sum;
	}
	
	static void DFS(int y, int x) {
		visited[y][x] = true;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(map[yy][xx] != 0 && !visited[yy][xx]) {
				DFS(yy, xx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		int L = 0;
		while(true) {
			L ++;
			melting = new int[N][M];
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < M ; k ++) {
					if(map[i][k] > 0) {
						T.melt(i, k);
					}
				}
			}
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < M ; k ++) {
					if(map[i][k] < melting[i][k]) map[i][k] = 0;
					else map[i][k] -= melting[i][k];
				}
			}
			sep = 0;
			visited = new boolean[N][M];
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < M ; k ++) {
					if(!visited[i][k] && map[i][k] != 0){
						sep ++;
						DFS(i, k);
					}
				}
			}
			if(sep > 1) break;
			if(sep == 0) {
				System.out.println("0");
				return;
			}
		}
		System.out.println(L);
	}
}
