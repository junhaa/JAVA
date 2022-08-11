import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #14621 나만 안되는 연애
class Edge implements Comparable<Edge>{
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static int[] univ;
	static ArrayList<Edge>[] list;
	static boolean ch[];
	static int MST(int N) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		int answer = 0;
		pQ.offer(new Edge(1,0));
		
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(!ch[tmp.node]) {
				answer += tmp.cost;
				ch[tmp.node] = true;
				for(Edge x : list[tmp.node]) pQ.offer(x);
			}
		}
		for(int i = 1 ; i <= N ; i ++) {
			if(!ch[i]) return -1;
		}
		return answer;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학교의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수 (양방향)
		univ = new int[N + 1];
		list = new ArrayList[N + 1];
		ch = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			char ch = st.nextToken().charAt(0);
			if(ch == 'W') univ[i] = 0;
			else univ[i] = 1;
		}
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(univ[start] == univ[end]) continue;
			list[start].add(new Edge(end, cost));
			list[end].add(new Edge(start, cost));
		}
		bw.write(String.valueOf(T.MST(N)));
		bw.flush();
		bw.close();
		br.close();
	}
}
