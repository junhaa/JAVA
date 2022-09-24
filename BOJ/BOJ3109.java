import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #3109 빵집
public class Main {
	static boolean[][] map;
	static int[] dr = { -1, 0, 1 };
	static boolean[][] visited;
	static int answer = 0, R, C;
	
	static boolean DFS(int r, int c) {
		visited[r][c] = true;
		if(c == C - 1) {
			answer ++;
			map[r][c] = true;
			return true;
		}
		for(int i = 0 ; i < 3 ; i ++) {
			int nr = r + dr[i];
			if(nr >= 0 && nr < R && !map[nr][c + 1] && !visited[nr][c + 1]) {
				if(DFS(nr , c + 1)) {
					return map[r][c] = true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		visited = new boolean[R][C];
		for(int i = 0 ; i < R ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < input.length() ; j ++) {
				if(input.charAt(j) == 'x') {
					map[i][j] = true;
				}
			}
		}
		for(int i = 0 ; i < R ; i ++) {
			if(!map[i][0]) {
				//visited = new boolean[R][C];
				DFS(i, 0);
			}
		}
		System.out.println(answer);
	}
}
