import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #19598 최소 회의실 개수
class Lecture implements Comparable<Lecture>{
	int start;
	int end;
	public Lecture(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Lecture o) {
		return this.start - o.start;
	}
}

public class Main {
	static PriorityQueue<Lecture> sQ = new PriorityQueue<>();
	
	static int solution() {
		PriorityQueue<Integer> eQ = new PriorityQueue<>();
		int tmp = 0;
		int answer = 1;
		eQ.offer(sQ.poll().end);
		while(!sQ.isEmpty()) {
			if(!eQ.isEmpty() && sQ.peek().start >= eQ.peek()) {
				eQ.poll();
				tmp ++;
			}
			else {
				if(tmp == 0) {
					answer ++;
					tmp ++;
				}
				tmp --;
				eQ.offer(sQ.poll().end);
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sQ.offer(new Lecture(start, end));
		}
		System.out.println(T.solution());
	}
	
}
