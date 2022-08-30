import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16947 서울 지하철 2호선
class Edge{
	int node;
	int preNode;
	public Edge(int node, int preNode) {
		this.node = node;
		this.preNode = preNode;
   	}
}
public class Main {

	static int[] length;
	static boolean[] ch;
	static ArrayList<Integer>[] list;
	static boolean flag = false; // 싸이클을 찾았는지 
	static int cNode = 0;
	
	static int DFS(int node, int pre) { // DFS를 이용하여 싸이클 찾기 
		ch[node] = true;
		int answer = -1;
		for(int next : list[node]) {
			if(pre == next) continue;
			if(ch[next] ) { 
				if(!flag) {
				cNode = next;
				flag = true;
				answer = 0;
				}
				else continue;
			}
			else answer = Math.max(answer, DFS(next, node));
		}
		length[node] = answer;
		if(node == cNode) return -1;
		else return answer;
	
	}
	
	static void BFS(int N) { // BFS를 이용하여 사이클로 부터 거리계산
		Queue<Integer> Q = new LinkedList<>();
		int cnt = 0;
		boolean[] visited = new boolean[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			if(length[i] == 0) { 
				Q.offer(i);
				visited[i] = true;
			}
		}
		while(!Q.isEmpty()) {
			int L = Q.size();
			for(int i = 0 ; i < L ; i ++) {
				int tmp = Q.poll();
				length[tmp] = cnt;
				for(int next : list[tmp]) {
					if(length[next] == 0 || visited[next]) continue;
					Q.offer(next);
					visited[next] = true;
				}
			}
			cnt ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		length = new int[N + 1];
		list = new ArrayList[N + 1];
		ch = new boolean[N + 1];
		for(int i = 1; i <= N ; i ++) {
			list[i] = new ArrayList<>();
 		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int nd1 = Integer.parseInt(st.nextToken());
			int nd2 = Integer.parseInt(st.nextToken());
			list[nd1].add(nd2);
			list[nd2].add(nd1);
		}
		T.DFS(1, 0);
		T.BFS(N);
		for(int i = 1 ; i <= N ; i ++) {
			sb.append(length[i] + " ");
		}
		System.out.println(sb);
	}
}
