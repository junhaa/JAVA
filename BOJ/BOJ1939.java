import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1939 중량제한 
class Bridge implements Comparable<Bridge>{ // 같은 두 섬에 다리가 여러개 가
	int node;
	int cost;
	public Bridge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	public int compareTo(Bridge o) {
		return o.cost - this.cost;
	}
}
public class Main {
	static int[] land;
	static ArrayList<Bridge>[] list; 

	static void solution(int startN, int endN) {
		PriorityQueue<Bridge> pQ = new PriorityQueue<>();
		land[startN] = Integer.MAX_VALUE;
		pQ.offer(new Bridge(startN, Integer.MAX_VALUE));
		while(!pQ.isEmpty()) {
			Bridge tmp = pQ.poll();
			if(land[tmp.node] > tmp.cost) continue;
			for(Bridge next : list[tmp.node]) {
				int weight = Math.min(next.cost, land[tmp.node]);
				if(land[next.node] < weight) {
					land[next.node] = weight;
					pQ.offer(new Bridge(next.node, weight));
				}
			}
		}
		System.out.println(land[endN]);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		land = new int[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Bridge(end, cost)); // 양방향 간선
			list[end].add(new Bridge(start, cost)); 
		}
		st = new StringTokenizer(br.readLine());
		T.solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
}
