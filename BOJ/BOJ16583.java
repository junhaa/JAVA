import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #16583 Boomerangs
public class Main {
	static ArrayList<Integer>[] edges;
	static ArrayList<Integer>[] treeEdges;
	static int N;
	static final int MAX = 1_000_000_007;
	static int[] visited;
	static StringBuilder sb = new StringBuilder();

	static int toNodenum(int idx) {
		if (idx >= MAX)
			idx -= MAX;
		return idx + 1; // 0-based
	}

	// 자식이 홀수면 음수로 리턴
	static int makeTree(int cur, int last) {
		visited[cur] = 1;
		int sum = 0;
		if (treeEdges[cur] == null) {
			treeEdges[cur] = new ArrayList<>();
		}
		for (int next : edges[cur]) {
			if (next == last || visited[next] == 2)
				continue;

			int ret = visited[next] == 1 ? 0 : makeTree(next, cur);
			if (ret >= 0) {
				treeEdges[cur].add(next);
				sum += ret;
			} else {
				sum -= ret;
			}
		}

		int len = treeEdges[cur].size();

		ArrayList<Integer> list = treeEdges[cur];
		for (int i = 0; i < len / 2; i++) {
			sb.append(toNodenum(list.get(i * 2)) + " " + toNodenum(cur) + " " + toNodenum(list.get(i * 2 + 1)) + "\n");
		}
		if (len % 2 == 1 && last != -1) {
			sb.append(toNodenum(treeEdges[cur].get(len - 1)) + " " + toNodenum(cur) + " " + toNodenum(last) + "\n");
		}
		visited[cur] = 2;
		return len % 2 == 0 ? sum + len / 2 : (sum + len / 2 + 1) * -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edges = new ArrayList[N];
		treeEdges = new ArrayList[100001];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			edges[a].add(b);
			edges[b].add(a);
		}

		visited = new int[N];

		int answer = 0;

		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {
				int ret = makeTree(i, -1);
				if (ret < 0) {
					ret++;
					ret *= -1;
				}
				answer += ret;
			}
		}
		System.out.println(answer);
		System.out.println(sb);
	}
}
