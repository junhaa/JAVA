import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1197 최소 스패닝 트리
class Node implements Comparable<Node>{
	int end;
	int weight;
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}
public class Main {
	public long solution(int V, ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		int[] ch = new int[V + 1];
		long sum = 0;
		pQ.offer(new Node(1, 0));
		int cnt = 0;
		while(!pQ.isEmpty()) {
			Node tmp = pQ.poll();
			if(ch[tmp.end] == 1) continue;
			else {
				ch[tmp.end] = 1;
				sum += (long)tmp.weight;
				cnt ++;
				for(Node x : list.get(tmp.end)) {
					pQ.offer(x);
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i <= V ; i ++) {
			list.add(new ArrayList<Node>());
		}
		int start, end, weight;
		for(int i = 0 ; i < E ; i ++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			list.get(start).add(new Node(end,weight));
			list.get(end).add(new Node(start,weight));
		}
		System.out.println(T.solution(V, list));
	}
}
