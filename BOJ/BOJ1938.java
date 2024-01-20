import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #1938 통나무 옮기기
class Chunk {
	int y, x, rotate; // rotate 0 => 가로 |  1 => 세로

	public Chunk(int y, int x, int rotate) {
		this.y = y;
		this.x = x;
		this.rotate = rotate;
	}

	@Override
	public boolean equals(Object obj) {
		Chunk c = (Chunk)obj;
		if (this.y == c.y && this.x == c.x && this.rotate == c.rotate)
			return true;
		return false;
	}
}

public class Main {
	static char[][] map;
	static int N;

	static boolean[][][] visited;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

	static int BFS(Chunk start, Chunk end) {
		Queue<Chunk> q = new LinkedList<>();
		visited = new boolean[2][N][N];
		visited[start.rotate][start.y][start.x] = true;
		q.offer(start);

		int L = 0;

		while (!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Chunk cur = q.poll();
				if (cur.equals(end))
					return L;
				for (int j = 0; j < 4; j++) {
					int ny = cur.y + dy[j];
					int nx = cur.x + dx[j];
					if (!OOB(ny, nx) && !visited[cur.rotate][ny][nx] && canMove(cur, j)) {
						q.offer(new Chunk(ny, nx, cur.rotate));
						visited[cur.rotate][ny][nx] = true;
					}
				}
				if (!visited[1 - cur.rotate][cur.y][cur.x] && canRotate(cur)) {
					q.offer(new Chunk(cur.y, cur.x, 1 - cur.rotate));
					visited[1 - cur.rotate][cur.y][cur.x] = true;
				}
			}
			L++;
		}
		return 0;
	}

	static boolean canRotate(Chunk cur) {
		int sum = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int ny = cur.y + i;
				int nx = cur.x + j;
				if (OOB(ny, nx))
					return false;
				if (map[ny][nx] == '1')
					sum++;
			}
		}
		if (sum != 0)
			return false;
		return true;
	}

	static boolean canMove(Chunk cur, int dir) {
		switch (dir) {
			case 0: // up
				if (cur.rotate == 0) {
					if (OOB(cur.y - 1, cur.x))
						return false;
					for (int i = -1; i <= 1; i++) {
						if (map[cur.y - 1][cur.x + i] == '1')
							return false;
					}
				} else {
					if (OOB(cur.y - 2, cur.x))
						return false;
					if (map[cur.y - 2][cur.x] == '1')
						return false;
				}
				break;

			case 1: // right
				if (cur.rotate == 0) {
					if (OOB(cur.y, cur.x + 2))
						return false;
					if (map[cur.y][cur.x + 2] == '1')
						return false;
				} else {
					if (OOB(cur.y, cur.x + 1))
						return false;
					for (int i = -1; i <= 1; i++) {
						if (map[cur.y + i][cur.x + 1] == '1')
							return false;
					}
				}
				break;

			case 2: // down
				if (cur.rotate == 0) {
					if (OOB(cur.y + 1, cur.x))
						return false;
					for (int i = -1; i <= 1; i++) {
						if (map[cur.y + 1][cur.x + i] == '1')
							return false;
					}
				} else {
					if (OOB(cur.y + 2, cur.x))
						return false;
					if (map[cur.y + 2][cur.x] == '1')
						return false;
				}
				break;

			case 3: // left
				if (cur.rotate == 0) {
					if (OOB(cur.y, cur.x - 2))
						return false;
					if (map[cur.y][cur.x - 2] == '1')
						return false;
				} else {
					if (OOB(cur.y, cur.x - 1))
						return false;
					for (int i = -1; i <= 1; i++) {
						if (map[cur.y + i][cur.x - 1] == '1')
							return false;
					}
				}
				break;
		}
		return true;
	}

	static boolean OOB(int y, int x) {
		if (y >= 0 && y < N && x >= 0 && x < N)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Chunk start = null, end = null;

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				char cur = input.charAt(j);
				map[i][j] = cur;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B' && start == null) {
					if (!OOB(i, j + 1) && map[i][j + 1] == 'B') {
						start = new Chunk(i, j + 1, 0);
					} else
						start = new Chunk(i + 1, j, 1);
				}

				if (map[i][j] == 'E' && end == null) {
					if (!OOB(i, j + 1) && map[i][j + 1] == 'E') {
						end = new Chunk(i, j + 1, 0);
					} else
						end = new Chunk(i + 1, j, 1);
				}
			}
		}
		System.out.println(BFS(start, end));
	}
}
