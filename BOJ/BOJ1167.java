import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #1167 트리의 지름
class Edge{
	int num;
	int dis;
	public Edge(int num, int dis) {
		this.num = num;
		this.dis = dis;
	}
}
public class Main {

	static ArrayList<Edge>[] list;
	static boolean[] visited;
	static int maxD = Integer.MIN_VALUE, maxI;
	
	static void DFS(int idx, int sum) {
		visited[idx] = true;
		if(sum > maxD) {
			maxI = idx;
			maxD = sum;
		}
		for(Edge next : list[idx]) {
			if(!visited[next.num]) {
			DFS(next.num, sum + next.dis);
			}
		}
	}
	
	static int solution(int V) {
		DFS(1, 0);
		visited = new boolean[V + 1];
		maxD = Integer.MIN_VALUE;
		DFS(maxI, 0);
		return maxD;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		StringTokenizer st;
		list = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		for(int i = 1 ; i <= V ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < V ; i ++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			while(true) {
				int num = Integer.parseInt(st.nextToken());
				if(num == -1) break;
				int dis = Integer.parseInt(st.nextToken());
				list[idx].add(new Edge(num, dis));
			}
		}
		System.out.println(T.solution(V));
	}
}
