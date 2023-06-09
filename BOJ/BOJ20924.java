import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #20924 트리의 기둥과 가지
class Edge{
	int end, cost;
	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
}
public class Main {

	static int gi = 0; // 기둥의 길이
	static int lastGi = -1;
	static ArrayList<Edge> adj[];
	
	static int getGa(int last, int cur, int sum) {
		int len = adj[cur].size();
		if(last == -1) len ++;
		if(len == 1) return sum;
		
		int max = sum;
		for(Edge next : adj[cur]) {
			if(next.end == last) continue;
			max = Math.max(max, getGa(cur, next.end, sum + next.cost));
		}
		return max;
	}
	
	static int getGi(int last, int cur) {
		int len = adj[cur].size();
		if(last == -1) len ++;
		if(len == 1) { 
			lastGi = last;
			return cur; // 리프가 기둥일 때
		}
		if(len > 2) { 
			lastGi = last;
			return cur; // 기가노드 일 때
		}
		int r = -1;
		for(Edge next : adj[cur]) {
			if(last == next.end) continue;
			gi += next.cost;
			r = getGi(cur, next.end);
		}
		return r;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		int start = Integer.parseInt(st.nextToken()) - 1;
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[s].add(new Edge(end, cost));
			adj[end].add(new Edge(s, cost));
		}
		
		int giNode = T.getGi(-1, start);
		System.out.println(gi + " " + T.getGa(lastGi, giNode, 0));
	}
}
