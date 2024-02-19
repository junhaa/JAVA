import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #12963 달리기
class Edge {
	int a, b;
	long val;

	public Edge(int a, int b, long val) {
		this.a = a;
		this.b = b;
		this.val = val;
	}
}

public class Main {
	static int[] parent;
	static Edge[] edges;
	static final int mod = 1_000_000_007;

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa < fb) {
			parent[fb] = fa;
		} else {
			parent[fa] = fb;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		edges = new Edge[M];
		int answer = 0;

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		long w = 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), w);
			w *= 3;
			w %= mod;
		}

		// 가장 번호가 큰 도로부터 union -> 0 -> N - 1로 이동가능하면 (모두 union 상태이면) val값을 더해준다.
		// 가장 번호가 큰 도로로 가는것이 가장 많이 이동하는 방법 => 3^i

		for (int i = M - 1; i >= 0; i--) {
			Edge cur = edges[i];
			int fa = find(cur.a);
			int fb = find(cur.b);
			int fe = find(N - 1);
			if (fa == fe && fb == 0 || fa == 0 && fb == fe) {
				answer += cur.val;
				answer %= mod;
			} else {
				union(cur.a, cur.b);
			}
		}
		System.out.println(answer);
	}
}
