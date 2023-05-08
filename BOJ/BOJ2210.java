import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #2210 숫자판 점프
public class Main {
	static int map[][] = new int[5][5];
	static HashSet<String> set = new HashSet<>();
	static char[] arr = new char[6];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static void DFS(int y, int x, int L) {
		arr[L] = Character.forDigit(map[y][x], 10);
		if(L == 5) {
			String tmp = "";
			for(int i = 0 ; i <= 5 ; i ++) {
				tmp += arr[i];
			}
			set.add(tmp);
			return;
		}
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
				DFS(ny, nx, L + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0 ; i < 5 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 5 ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < 5 ; i ++) {
			for(int j = 0 ; j < 5 ; j ++) {
				T.DFS(i, j, 0);
			}
		}
		System.out.println(set.size());
	}
}
