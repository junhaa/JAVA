import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #13896 Sky Tax
public class Main {
	static int N;
	static final int MAX = 17; // 최대 100_000 2^17 = 131_072
	static ArrayList<Integer>[] edges;
	static int[] depth, childs;
	static int[][] parent; // sparse array

	static int DFS(int cur) {
		int cnt = 1; // 자식 노드의 수, 최대 100_000, 자기자신도 포함 -> 1로 초기화
		for (int next : edges[cur]) {
			if (depth[next] == -1) {
				depth[next] = depth[cur] + 1;
				parent[next][0] = cur;
				cnt += DFS(next);
			}
		}
		childs[cur] = cnt;
		return cnt;
	}

	static int LCA(int u, int v) {
		// swap
		if (depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}

		int dif = depth[u] - depth[v];
		for (int i = 0; dif != 0; i++) {
			if ((dif & 1) == 1)
				u = parent[u][i];
			dif >>= 1;
		}

		if (u != v) {
			for (int i = MAX - 1; i >= 0; i--) {
				if (parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
		}
		return u != v ? parent[u][0] : u;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int i = 1; i <= T; i++) {
			sb.append("Case #" + i + ":\n");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시의 수
			int Q = Integer.parseInt(st.nextToken()); // 쿼리 수
			int U = Integer.parseInt(st.nextToken()) - 1; // 현재 수도 도시의 노드 번호
			edges = new ArrayList[N];
			childs = new int[N];
			depth = new int[N];
			parent = new int[N][MAX];
			for (int j = 0; j < N; j++) {
				Arrays.fill(parent[j], -1);
				edges[j] = new ArrayList<>();
			}
			Arrays.fill(depth, -1);
			for (int j = 0; j < N - 1; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1; // 0 - based
				int b = Integer.parseInt(st.nextToken()) - 1;
				edges[a].add(b);
				edges[b].add(a);
			}
			depth[U] = 0;
			DFS(U); // 최초 수도를 기준으로 DFS

			for (int j = 0; j < MAX - 1; j++) {
				for (int k = 0; k < N; k++) {
					if (parent[k][j] != -1) {
						parent[k][j + 1] = parent[parent[k][j]][j];
					}
				}
			}

			for (int j = 0; j < Q; j++) {
				st = new StringTokenizer(br.readLine());
				// 수도 변경 쿼리
				if (Integer.parseInt(st.nextToken()) == 0) {
					U = Integer.parseInt(st.nextToken()) - 1;
				}
				// 세금 처리해야 하는 도시 수 출력 쿼리
				else {
					int x = Integer.parseInt(st.nextToken()) - 1;
					// x가 U(수도)인 경우 모든 자식 노드의 수(N)
					if (x == U) {
						sb.append(N + "\n");
						continue;
					}
					int lca = LCA(x, U);
					if (lca == x) {
						// x가 상위 노드이므로 x의 depth - 1 이면서 U의 부모인 노드의 자식수를 전체에서 빼야 함

						int tmp = U;
						/*
						for (int k = MAX - 1; k >= 0; k--) {
							if (parent[tmp][k] != -1 && parent[tmp][k] != x) {
								tmp = parent[tmp][k];
							}
						}
						// => x보다 상위 노드로 변경될 수 있음
						*/

						int dif = depth[U] - depth[x] - 1;
						for (int k = 0; dif != 0; k++) {
							if ((dif & 1) == 1)
								tmp = parent[tmp][k];
							dif >>= 1;
						}

						sb.append((N - childs[tmp]) + "\n");

					} else {
						// 이 외의 경우는 모두 자식 노드의 수 출력
						sb.append(childs[x] + "\n");
					}
				}
			}
		}
		System.out.println(sb);
	}
}
