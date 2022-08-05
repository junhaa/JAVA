import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2887 행성 터널

class Planet{
	int x;
	int y; 
	int z;
	int idx; // 인덱스 값
	public Planet(int x, int y, int z, int idx) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.idx = idx;
	}
}
class Edge implements Comparable<Edge>{
	int end;
	int cost;
	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
public class Main {
	static ArrayList<Planet> pList;
	static int solution(int N) { // 최소 스패닝 트리 + 정렬
		boolean[]v = new boolean[N + 1];
		ArrayList<ArrayList<Edge>> eList = new ArrayList<>();
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		int answer = 0;
		for(int i = 0 ; i <= N ; i ++) {
			eList.add(new ArrayList<>());
		}
		Collections.sort(pList, new Comparator<Planet>() { // x에 대해 정렬
			@Override
			public int compare(Planet p1, Planet p2) { 
				return p1.x - p2.x;
			}
		});
		
		for(int i = 1 ; i < N ; i ++) {
			int cost = Math.abs(pList.get(i).x - pList.get(i - 1).x);
			eList.get(pList.get(i - 1).idx).add(new Edge(pList.get(i).idx, cost)); 
			eList.get(pList.get(i).idx).add(new Edge(pList.get(i - 1).idx, cost));
		}
		
		Collections.sort(pList, new Comparator<Planet>() { // y에 대해 정렬
			@Override
			public int compare(Planet p1, Planet p2) { 
				return p1.y - p2.y;
			}
		});
		
		for(int i = 1 ; i < N ; i ++) {
			int cost = Math.abs(pList.get(i).y - pList.get(i - 1).y);
			eList.get(pList.get(i - 1).idx).add(new Edge(pList.get(i).idx, cost)); 
			eList.get(pList.get(i).idx).add(new Edge(pList.get(i - 1).idx, cost));
		}
		Collections.sort(pList, new Comparator<Planet>() { // z에 대해 정렬
			@Override
			public int compare(Planet p1, Planet p2) { 
				return p1.z - p2.z;
			}
		});
		
		for(int i = 1 ; i < N ; i ++) {
			int cost = Math.abs(pList.get(i).z - pList.get(i - 1).z);
			eList.get(pList.get(i - 1).idx).add(new Edge(pList.get(i).idx, cost)); 
			eList.get(pList.get(i).idx).add(new Edge(pList.get(i - 1).idx, cost));
		}
		
		pQ.offer(new Edge(1, 0));
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(v[tmp.end]) continue;
			v[tmp.end] = true;
			answer += tmp.cost;
			for(Edge x : eList.get(tmp.end)) {
					pQ.offer(x);
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		pList = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int x, y, z;
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			pList.add(new Planet(x, y, z, i)); 
		}
		System.out.println(T.solution(N));
	}
}
