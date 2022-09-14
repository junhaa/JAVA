import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2611 자동차경주
class Edge{
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.cost = cost;
		this.node = node;
	}
}
public class Main {

	static int[] id, pre, score;
	static ArrayList<Edge>[] list;
	
	static void solution() {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(1);
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			for(Edge e : list[tmp]) {
				if(e.cost + score[tmp] > score[e.node]) {
					score[e.node] = e.cost + score[tmp];
					pre[e.node] = tmp;
				}
				if(--id[e.node] == 0) { 
					if(e.node == 1) return;
					Q.offer(e.node);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine()); // 노드의 개수 
		int M = Integer.parseInt(br.readLine()); // 간선의 개수
		id = new int[N + 1];
		pre = new int[N + 1];
		score = new int[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, cost));
			id[end] ++;
		}
		T.solution();
		sb.append(score[1] + "\n");
		stack.add(1);
		int tmp = pre[1];
		while(true) {
			stack.add(tmp);
			if(tmp == 1) break;
			tmp = pre[tmp];
		}
		int length = stack.size();
		for(int i = 0 ; i < length ; i ++) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}
}
