import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16929 Two Dots
public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

	static boolean OOB(int y, int x) {
		if (y >= 0 && y < N && x >= 0 && x < M)
			return false;
		return true;
	}

	static boolean DFS(int y, int x, char last, int lastY, int lastX) {
		visited[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (!OOB(ny, nx) && visited[ny][nx] && map[ny][nx] == last && (ny != lastY || nx != lastX)) {
				return true;
			} else if (!OOB(ny, nx) && !visited[ny][nx] && map[ny][nx] == last) {
				if (DFS(ny, nx, last, y, x)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char cur = input.charAt(j);
				map[i][j] = cur;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					if (DFS(i, j, map[i][j], -1, -1)) {
						System.out.println("Yes");
						return;
					}
				}
			}
		}

		System.out.println("No");
	}
}
