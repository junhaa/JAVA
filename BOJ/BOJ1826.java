import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1826 연료 채우기
class Fuel implements Comparable<Fuel>{
	int node;
	int dis;
	public Fuel(int node, int dis) {
		this.node = node;
		this.dis = dis;
	}
	@Override
	public int compareTo(Fuel o) {
		return this.node - o.node;
	}
	
}
public class Main {
	static PriorityQueue<Fuel> station = new PriorityQueue<>();
	
	static int solution(int vil, int start) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		int answer = 0;
		int tmp = start;
		while(true) {
			if(!station.isEmpty() && station.peek().node <= tmp) pQ.offer(station.poll().dis); 
			else break;
		}
		if(tmp >= vil) return answer;
		while(!pQ.isEmpty()) {
			tmp += pQ.poll();
			answer ++;
			if(tmp >= vil) return answer;
			while(true) {
				if(!station.isEmpty() && station.peek().node <= tmp) pQ.offer(station.poll().dis); 
				else break;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 주유소의 개수
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()); // 주유소의 위치
			int n2 = Integer.parseInt(st.nextToken()); // 주유량
			station.offer(new Fuel(n1, n2));
		}
		st = new StringTokenizer(br.readLine());
		int vil = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(vil, start));
	}
}
