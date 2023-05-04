import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2630 색종이 만들기 
public class Main {

	static int[][] prefix;
	static int[] dx = { 0, 1, 0, 1 };
	static int[] dy = { 0, 0, 1, 1 };
	static int white = 0, blue = 0;
	static int getSum(int y1, int x1, int y2, int x2) {
		return prefix[y1][x1] - prefix[y2 - 1][x1] - prefix[y1][x2 - 1] + prefix[y2 - 1][x2 - 1];
	}
	
	static void DAQ(int N, int cury, int curx) { 
		int tmpSum = getSum(cury + N - 1, curx + N - 1, cury, curx);
		if(tmpSum == N * N) {
			blue ++;
			return;
		}
		else if(tmpSum == 0) {
			white ++;
			return;
		}
		if(N == 2) {
			blue += tmpSum;
			white += 4 - tmpSum;
			return;
		}
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = cury + N / 2 * dy[i];
			int nx = curx + N / 2 * dx[i];
			DAQ(N / 2, ny, nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		prefix = new int[N + 1][N + 1];
		StringTokenizer st;
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j ++) {
				prefix[i][j] = prefix[i][j - 1] + Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1 ; i <= N ; i ++) {
			for(int j = 1 ; j <= N ; j ++) {
				prefix[i][j] += prefix[i - 1][j];
			}
		}
		T.DAQ(N, 1, 1);
		System.out.println(white + "\n" + blue);
	}
}
