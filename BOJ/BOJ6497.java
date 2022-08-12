import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #6497 전력난
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
	static ArrayList<Edge> list;
	static int[] unf;
	
	static int find(int x) {
		if(unf[x] == x) return x;
		else return unf[x] = find(unf[x]);
	}
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) unf[fb] = fa;
		else unf[fa] = fb;
	}
	
	static int kruscal(int sum) {
		int min = 0;
		Collections.sort(list);
		for(Edge x : list) {
			if(find(x.start) == find(x.end)) continue;
			union(x.start, x.end);
			min += x.cost;
		}
		return sum - min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int m, n;
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 집의 수
			n = Integer.parseInt(st.nextToken()); // 길의 수
			if(m == 0 && n == 0) break;
			list = new ArrayList<>();
			unf = new int[m];
			int sum = 0;
			for(int i = 1 ; i < m ; i ++) unf[i] = i;
			for(int i = 0 ; i < n ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				sum += cost;
				list.add(new Edge(start, end, cost));
			}
			sb.append(T.kruscal(sum)).append('\n');
		}
		System.out.println(sb);
	}
}
