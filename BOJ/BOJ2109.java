import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2109 순회강연
class Lecture implements Comparable<Lecture>{
	int d;
	int p;
	public Lecture(int p, int d) {
		this.d = d;
		this.p = p;
	}
	@Override
	public int compareTo(Lecture o) {
		return o.d - this.d;
	}

}
public class Main {

	static PriorityQueue<Lecture> dQ = new PriorityQueue<>();
	static PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		
	static int solution(int max) {
		int answer = 0 ;
		while(max > 0) {
			while(true) {
				if(!dQ.isEmpty() && dQ.peek().d < max) break;
				else if(dQ.isEmpty()) break;
				else pQ.offer(dQ.poll().p);
			}
			if(pQ.isEmpty()) { 
				max --;
				continue;
			}
			answer += pQ.poll();
			max --;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int max = Integer.MIN_VALUE;
		int n = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			max = Math.max(max, d);
			dQ.offer(new Lecture(p, d));
		}
		System.out.println(T.solution(max));
	}
}
