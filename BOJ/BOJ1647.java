import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1647 도시 분할 계획
class Road implements Comparable<Road>{
	int end;
	int cost;
	public Road(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Road o) {
		return this.cost - o.cost;
	}
	
}
public class Main {
	static PriorityQueue<Road> pQ = new PriorityQueue<>();
	static ArrayList<ArrayList<Road>> list = new ArrayList<>();
	static int N, M;
	public static int solution() {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int[] ch = new int[N + 1];
		int cnt = 0;
		pQ.offer(new Road(1,0));
		while(cnt < N) {
			Road tmp = pQ.poll();
			if(ch[tmp.end] == 0) {
				sum += tmp.cost;
				max = Math.max(max, tmp.cost);
				ch[tmp.end] = 1;
				for(Road x :list.get(tmp.end)) pQ.offer(x);
				cnt ++;
			}
		}
		return sum - max;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 길의 개수
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<Road>());
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmpS = Integer.parseInt(st.nextToken());
			int tmpE = Integer.parseInt(st.nextToken());
			int tmpC = Integer.parseInt(st.nextToken());
			list.get(tmpS).add(new Road(tmpE,tmpC));
			list.get(tmpE).add(new Road(tmpS,tmpC));
		}
		System.out.println(T.solution());
	}
}
