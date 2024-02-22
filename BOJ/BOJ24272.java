import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #24272 루트노드가 많은 트리일수록 좋은 트리이다
class Query {
	int a, b;

	public Query(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

public class Main {
	static ArrayList<Integer>[] adj;
	static HashSet<Integer>[] dirAdj;
	static long[] tree, cnt;
	static int size = 2, idx = -1, N;
	static int[] startIdx, endIdx;

	static void DFS(int cur) {
		startIdx[cur] = ++idx;
		for (int next : adj[cur]) {
			if (startIdx[next] == -1)
				DFS(next);
		}
		endIdx[cur] = idx;
	}

	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		if (R < nodeL || nodeR < L)
			return;
		// nodeL != nodeR에 주의 , 자식으로 propagation X
    // 특정 구간에 대해서만 update 가능 => [0,3]에 대해 +1 한 경우는 [0,3]만 -1 update 가능 다른 범위는 불가능 -> 좌표압축 생각해보기
		if (L <= nodeL && nodeR <= R) {
			tree[nodeNum] += val;
		} else {
			int mid = (nodeR + nodeL) >> 1;
			update(L, R, nodeNum << 1, nodeL, mid, val);
			update(L, R, nodeNum << 1 | 1, mid + 1, nodeR, val);
		}
		if (tree[nodeNum] > 0)
			cnt[nodeNum] = nodeR - nodeL + 1;
		else if (nodeL != nodeR)
			cnt[nodeNum] = cnt[nodeNum << 1] + cnt[nodeNum << 1 | 1];
		else
			cnt[nodeNum] = 0;
	}

	static void updateEdge(int a, int b, int val) {
		// a -> b에서 b가 부모 노드이면, a를 포함한 자식노드를 제외한 모든 노드 +-1
		if (startIdx[a] > startIdx[b]) {
			update(0, startIdx[a] - 1, 1, 0, size / 2 - 1, val);
			if (endIdx[a] + 1 <= N - 1) {
				update(endIdx[a] + 1, N - 1, 1, 0, size / 2 - 1, val);
			}
		} else {
			// 반대의 경우 b를 포함한 자식노드 모두 +-1
			update(startIdx[b], endIdx[b], 1, 0, size / 2 - 1, val);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (size < N << 1) {
			size <<= 1;
		}
		tree = new long[size];
		cnt = new long[size];

		adj = new ArrayList[N];
		dirAdj = new HashSet[N];

		ArrayList<Query> queries = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			dirAdj[i] = new HashSet<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 0 - based
			String dirStr = st.nextToken();
			int b = Integer.parseInt(st.nextToken()) - 1;
			if (dirStr.charAt(0) == '<') {
				dirAdj[b].add(a);
				queries.add(new Query(b, a));
			} else if (dirStr.charAt(1) == '>') {
				dirAdj[a].add(b);
				queries.add(new Query(a, b));
			}
			adj[a].add(b);
			adj[b].add(a);
		}

		startIdx = new int[N];
		endIdx = new int[N];

		Arrays.fill(startIdx, -1);

		DFS(0);

		// a -> b 일 경우 b는 모두 '루트노드'가 될 수 없으므로 +1
		for (Query q : queries) {
			updateEdge(q.a, q.b, 1);
		}

		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int dir = 0;
			String dirStr = st.nextToken();
			if (dirStr.charAt(0) == '<') {
				dir = -1;
			} else if (dirStr.charAt(1) == '>') {
				dir = 1;
			}

			int b = Integer.parseInt(st.nextToken()) - 1;
			// 방향 swap
			if (dir == -1) {
				int tmp = a;
				a = b;
				b = tmp;
				dir = 1;
			}

			// 현재 상태와 같으면 continue;
			if ((dir == 1 && (dirAdj[a].contains(b))) || (dir == 0 && (!dirAdj[a].contains(b) && !dirAdj[b].contains(
				a)))) {
				sb.append(N - cnt[1] + "\n");
				continue;
			}

			// 기존의 방향 간선이 있으면 제거 (-1) == 무방향 간선으로 변경
			if (dirAdj[a].contains(b)) {
				updateEdge(a, b, -1);
				dirAdj[a].remove(b);
			}
			if (dirAdj[b].contains(a)) {
				updateEdge(b, a, -1);
				dirAdj[b].remove(a);
			}

			// 방향 간선인 경우 추가
			if (dir == 1) {
				updateEdge(a, b, 1);
				dirAdj[a].add(b);
			}

			sb.append((N - cnt[1]) + "\n");

		}
		System.out.println(sb);
	}
}
