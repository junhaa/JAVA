import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2618 경찰차
class Point {
	int y, x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static Point[] map;
	static int[][] mem, move;
	static int N, W;

	static private final int MAX = 1_000_000_000;

	static int getDis(int a, int b, boolean flag) {
		Point pa, pb = map[b];
		if (a == 0) {
			if (!flag) {
				pa = new Point(0, 0);
			} else {
				pa = new Point(N - 1, N - 1);
			}
		} else {
			pa = map[a];
		}

		return Math.abs(pa.x - pb.x) + Math.abs(pa.y - pb.y);
	}

	static int recursive(int a, int b) {
		if (a == W || b == W) {
			return mem[a][b] = 0;
		}
		if (mem[a][b] != 0) {
			return mem[a][b];
		}

		int next = Math.max(a, b) + 1;
		int ra = recursive(a, next) + getDis(b, next, true);
		int rb = recursive(next, b) + getDis(a, next, false);
		if (ra < rb) {
			move[a][b] = 2;
			mem[a][b] = ra;
		} else {
			move[a][b] = 1;
			mem[a][b] = rb;
		}
		return mem[a][b];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());

		map = new Point[W + 1];
		mem = new int[W + 1][W + 1]; // [1번 경찰차][2번 경찰차]
		move = new int[W + 1][W + 1]; // 이동 경로

		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			map[i + 1] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		sb.append(recursive(0, 0) + "\n");

		int cur = 0;
		int curx = 0, cury = 0;

		while (cur < W) {
			int ans = move[curx][cury];
			sb.append(ans + "\n");
			if (ans == 1) {
				curx = ++cur;
			} else {
				cury = ++cur;
			}
		}
		System.out.println(sb);
	}
}
