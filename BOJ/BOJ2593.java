import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2593 엘리베이터
public class Main {
	static int[] visited;
	// static int[][] input;
	static int N, M;

	static ArrayList<Integer>[] des;
	static ArrayList<Integer>[] ele;

	static Stack<Integer> stack = new Stack<>();

	/*
	//Extended Euclidean Algorithm -> 다시 풀어보기
	public static int[] EEA(int a, int b) {
		int r1 = a, r2 = b, s1 = 1, s2 = 0, t1 = 0, t2 = 1;

		while (r2 != 0) {
			int q = r1 / r2;
			int r = r1 % r2;
			int s = s1 - q * s2;
			int t = t1 - q * t2;
			r1 = r2;
			r2 = r;
			s1 = s2;
			s2 = s;
			t1 = t2;
			t2 = t;
		}

		return new int[] {r1, s1, t1};
	}

	static boolean canChange(int start, int end) {
		int a = input[1][start];
		int b = input[1][end] * -1;
		int d = input[0][end] - input[0][start];

		int[] ret = EEA(a, b);
		if (ret[0] < 0) {
			ret[0] *= -1;
			ret[1] *= -1;
			ret[2] *= -1;
		}

		if (d % ret[0] != 0 || ret[1] < 0 || ret[2] < 0)
			return false;
		int mul = d / ret[0];
		int x = ret[1] * mul;
		int y = ret[2] * mul;

		if (x * a + input[0][start] > N || y * b + input[0][end] > N) {
			return false;
		}
		return true;
	}


	static boolean canMove(int des, int idx) {
		double a = (double)(des - input[0][idx]) / input[1][idx];
		if (a == (int)a && a >= 0) {
			return true;
		}
		return false;
	}
	 */

	static int BFS(int start, int end) {
		Queue<Integer> Q = new LinkedList<>();

		Arrays.fill(visited, -1);

		HashSet<Integer> set = new HashSet<>();

		/*
		for (int i = 0; i < M; i++) {

			// Add Start
			if (canMove(start, i)) {
				Q.offer(i);
				visited[i] = Integer.MAX_VALUE;
			}

			//Add End
			if (canMove(end, i)) {
				set.add(i);
			}
		}
		*/

		for (int s : des[start]) {
			Q.offer(s);
			visited[s] = Integer.MAX_VALUE;
		}

		for (int e : des[end]) {
			set.add(e);
		}

		while (!Q.isEmpty()) {
			int len = Q.size();
			for (int i = 0; i < len; i++) {
				int cur = Q.poll();
				if (set.contains(cur))
					return cur;

				for (int k = 0; k < ele[cur].size(); k++) {
					int desFloor = ele[cur].get(k);
					for (int j = 0; j < des[desFloor].size(); j++) {
						int nextElev = des[desFloor].get(j);
						if (visited[nextElev] == -1) {
							visited[nextElev] = cur;
							Q.offer(nextElev);
						}
					}
				}
			}
		}
		return -1;
	}

	static void recursive(int cur) {
		stack.push(cur);
		if (visited[cur] == Integer.MAX_VALUE) {
			return;
		}
		recursive(visited[cur]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[M];
		// input = new int[2][M];
		// edges = new boolean[M][M];

		des = new ArrayList[N + 1];
		ele = new ArrayList[M + 1];

		for (int i = 0; i <= M; i++) {
			ele[i] = new ArrayList<>();
		}
		for (int i = 0; i <= N; i++) {
			des[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// input[0][i] = a;
			// input[1][i] = b;
			while (a <= N) {
				des[a].add(i);
				ele[i].add(a);
				a += b;
			}
		}

		/*
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				if (canChange(i, j)) {
					edges[i][j] = true;
					edges[j][i] = true;
				}
			}
		}
		 */

		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int ret = BFS(start, end);

		if (ret != -1) {
			recursive(ret);
			StringBuilder sb = new StringBuilder();
			sb.append(stack.size() + "\n");
			while (!stack.isEmpty()) {
				sb.append((stack.pop() + 1) + "\n");
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}
}
