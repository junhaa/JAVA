import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #11000 강의실 배정
class Time implements Comparable<Time>{
	int start;
	int end;
	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Time o) {
		return this.start - o.start;
	}
}
public class Main {

	static PriorityQueue<Time> sQ = new PriorityQueue<>();
	
	static int solution() {
		PriorityQueue<Integer> eQ = new PriorityQueue<>();
		int answer = 1;
		int blank = 0;
		eQ.offer(sQ.poll().end);
		while(!sQ.isEmpty()) {
			if(sQ.peek().start >= eQ.peek()) {
				eQ.poll();
				blank ++;
			}
			else {
				if(blank == 0) {
					answer ++;
					blank ++;
				}
				Time tmp = sQ.poll();
				blank --;
				eQ.offer(tmp.end);
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
			sQ.offer(new Time(start, end));
		}
		System.out.println(T.solution());
	}
}
