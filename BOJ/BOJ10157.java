import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10157 자리배정
public class Main {
	static int[] dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1};

	static boolean OOB(int y, int x, int R, int C) {
		if (y >= 0 && y < R && x >= 0 && x < C)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		if (K > C * R) {
			System.out.println(0);
			return;
		}

		int[][] visited = new int[R][C];
		int seq = 0;
		int dir = 0;
		int cury = 0;
		int curx = 0;
		for (int i = 0; i < K - 1; i++) {
			visited[cury][curx] = ++seq;

			int ny = cury + dy[dir];
			int nx = curx + dx[dir];

			if (OOB(ny, nx, R, C) || visited[ny][nx] != 0) {
				dir++;
				dir %= 4;
				ny = cury + dy[dir];
				nx = curx + dx[dir];
			}
			cury = ny;
			curx = nx;
		}

		System.out.println((curx + 1) + " " + (cury + 1));

	}
}
