import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17829 222-풀링
public class Main {
	
	static int[][] map;
	static int[] dy = { 0, 0, 1, 1 };
	static int[] dx = { 0, 1, 1, 0 };
	static int N;
	
	static int DAQ(int len, int y, int x) {
		if(len == 1) {
			return map[y][x];
		}
		int first = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = y + dy[i] * len / 2;
			int nx = x + dx[i] * len / 2;
			int tmp = DAQ(len / 2, ny, nx);
			if(tmp > first) { 
				second = first;
				first = tmp;
			}
			else if(tmp > second) second = tmp;
		}
		return second;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N]; // 0-based
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.DAQ(N, 0, 0));
	}
}
