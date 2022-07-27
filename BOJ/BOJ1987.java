import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #1987 ¾ËÆÄºª
public class Main {
	static char[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[] ch = new boolean[27];
	static int answer = Integer.MIN_VALUE, R, C;

	static ArrayList<Character> list = new ArrayList<>();
	
	public static void DFS(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);
		ch[map[y][x] - 'A'] = true;
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx >= 0 && yy >= 0 && xx < C && yy < R && !ch[map[yy][xx] - 'A']) DFS(xx,yy, cnt + 1);
		}
		ch[map[y][x] - 'A'] = false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			String str = br.readLine();
			for(int k = 0 ; k < C ; k ++) {
				map[i][k] = str.charAt(k);
			}
		}
		T.DFS(0,0,1);
		bw.write(String.valueOf(answer));
		bw.flush();
	}
}
