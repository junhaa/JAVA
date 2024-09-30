import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #16202 MST 게임 
class Edge implements Comparable<Edge>{
	int sNode, eNode, cost;

	public Edge(int sNode, int eNode, int cost) {
		this.sNode = sNode;
		this.eNode = eNode;
		this.cost = cost;
	}


	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}


public class Main {
	static int N, M, K;
	static int[][] edge;
	static boolean canMST = true;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		edge = new int[N][N];
		main.initParent();
		for(int i = 1 ; i <= M ; i ++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 0-based
			int b = Integer.parseInt(st.nextToken()) - 1;
			edge[a][b] = i;
			edge[b][a] = i;
		}
		while(K -- > 0){
			if(!canMST){
				sb.append("0 ");
			}
			else sb.append(main.getMstCost() + " ");
		}
		System.out.println(sb);
	}

	private int getMstCost(){
		int minS = -1, minE = -1, minCost = Integer.MAX_VALUE;
		initParent();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i = 0 ; i < N ; i ++){
			if(edge[0][i] != 0){
				pq.add(new Edge(0, i, edge[0][i]));
			}
		}
		int sumCost = 0;
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			if(find(0) == find(cur.eNode)) continue;
			sumCost += cur.cost;

			if(cur.cost < minCost){
				minCost = cur.cost;
				minS = cur.sNode;
				minE = cur.eNode;
			}
			union(cur.sNode, cur.eNode);

			for(int j = 0 ; j < N ; j ++){
				if(edge[cur.eNode][j] != 0){
					pq.add(new Edge(cur.eNode, j, edge[cur.eNode][j]));
				}
			}
		}
		if(!isMst()){
			canMST = false;
			return 0;
		}
		if(minCost != Integer.MIN_VALUE) {
			edge[minS][minE] = 0;
			edge[minE][minS] = 0;
		}

		return sumCost;
	}

	private int find(int x){
		if(x == parent[x]){
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private void union(int a, int b){
		int fa = find(a);
		int fb = find(b);
		if(fa < fb){
			parent[fb] = fa;
		}
		else{
			parent[fa] = fb;
		}
	}

	private boolean isMst(){
		for(int i = 0 ; i < N ; i ++){
			if(parent[i] != 0) return false;
		}
		return true;
	}

	private void initParent(){
		parent = new int[N];
		for(int i = 1 ; i < N ; i ++){
			parent[i] = i;
		}
	}
}
