import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
// BOJ #1414 불우이웃돕기
class Edge implements Comparable<Edge> {
	int end, cost;

	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int N;

	static boolean[] visited;
	static ArrayList<Edge>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		edges = new ArrayList[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				int cost = -1;
				char cur = input.charAt(j);
				if (cur == '0')
					continue;

				if (Character.isUpperCase(cur)) {
					cost = cur - 'A' + 27;
				} else {
					cost = cur - 'a' + 1;
				}
				sum += cost;
				if (i == j)
					continue;

				edges[i].add(new Edge(j, cost));
				edges[j].add(new Edge(i, cost));
			}
		}
		visited[0] = true;

		for (Edge e : edges[0]) {
			pq.offer(e);
		}

		int answer = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visited[cur.end])
				continue;
			answer += cur.cost;
			visited[cur.end] = true;
			for (Edge e : edges[cur.end]) {
				pq.offer(e);
			}
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(sum - answer);
	}
}
