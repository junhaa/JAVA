import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #16398 행성 연결
class Edge implements Comparable<Edge>{
	int start, end, cost;
	public Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {

	static ArrayList<Edge> list = new ArrayList<>();
	static int[] unf;
	
	static int find(int x) {
		if(x == unf[x]) return x;
		else return unf[x] = find(unf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) unf[fb] = fa;
		else unf[fa] = fb;
	}
	
	static long kruscal() {
		long answer = 0;
		Collections.sort(list);
		for(Edge x : list) {
			if(find(x.start) == find(x.end)) continue;
			union(x.start, x.end);
			answer += x.cost;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		unf = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) unf[i] = i;
		StringTokenizer st ;
		for(int i = 1 ; i <= N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int k = 1 ; k <= N ; k ++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i == k) continue;
				list.add(new Edge(i, k, cost));
			}
		}
		System.out.println(T.kruscal());
	}
}
