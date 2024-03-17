import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #8872 빌라봉
class Edge {
	int node, cost;

	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}

public class Main {
	static int N, L;
	static ArrayList<Edge>[] edges;
	static int curMax = Integer.MIN_VALUE, curNode = -1;
	static int[] dp;
	static int flag = -1, treeNum = 0;
	static int[] treeIdx;

	static ArrayList<Integer> nodes;
	static int maxDiameter = Integer.MIN_VALUE;

	static int getRadius(int node) {
		dfs(node, -1);
		Edge ret = getDis(node, -1, 0);
		maxDiameter = Math.max(maxDiameter, ret.node);
		return ret.cost;
	}

	private static void init() {
		curMax = Integer.MIN_VALUE;
		curNode = -1;
	}

	static int dfs(int cur, int last) {
		treeIdx[cur] = treeNum;
		for (Edge next : edges[cur]) {
			if (next.node != last)
				dp[cur] = Math.max(dp[cur], dfs(next.node, cur) + next.cost);
		}
		return dp[cur];
	}

	static Edge getDis(int cur, int last, int sum) {

		int firstDis = sum;
		int secondDis = -1;

		Edge firstEdge = null;

		for (Edge next : edges[cur]) {
			if (next.node != last) {
				int nextDis = dp[next.node] + next.cost;
				if (firstDis <= nextDis) {
					secondDis = firstDis;
					firstDis = nextDis;
					firstEdge = next;
				} else if (nextDis >= secondDis) {
					secondDis = nextDis;
				}
			}
		}

		// 자식이 없으면
		if (firstEdge == null) {
			return new Edge(firstDis, firstDis);
		}
		if (firstDis < Math.max(firstDis - firstEdge.cost, secondDis + firstEdge.cost)) {
			return new Edge(firstDis + secondDis, firstDis);
		}

		Edge ret = getDis(firstEdge.node, cur, secondDis + firstEdge.cost);
		ret.node = Math.max(ret.node, firstDis + secondDis);
		return ret;
	}

/*
	static void findEndNode(int node, int sum, int lastNode) {
		if (treeIdx[node] == -1) {
			treeIdx[node] = treeNum;
			nodes.add(node);
		}
		if (flag >= 0) {
			dp[flag][node] = sum;
		}
		if (sum > curMax) {
			curMax = sum;
			curNode = node;
		}
		for (Edge next : edges[node]) {
			if (next.node == lastNode)
				continue;
			findEndNode(next.node, sum + next.cost, node);
		}
	}
*/

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N];
		dp = new int[N];
		treeIdx = new int[N];

		Arrays.fill(treeIdx, -1);

		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, cost));
			edges[b].add(new Edge(a, cost));
		}

		ArrayList<Integer> results = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (treeIdx[i] == -1) {
				results.add(getRadius(i));
				treeNum++;
			}
		}

		Collections.sort(results, Collections.reverseOrder());

		int res = maxDiameter;
		if (results.size() > 1) {
			res = Math.max(res, results.get(0) + results.get(1) + L);
			if (results.size() > 2) {
				res = Math.max(res, results.get(1) + L + results.get(2) + L);
			}
		}

		System.out.println(res);

	}
}
