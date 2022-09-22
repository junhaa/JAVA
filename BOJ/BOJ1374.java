import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1374 °­ÀÇ½Ç
class Room implements Comparable<Room>{
	int start;
	int end;
	public Room(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public int compareTo(Room o) {
		return this.start - o.start;
	}
}
public class Main {

	static PriorityQueue<Room> sQ = new PriorityQueue<>();
	
	static int solution() {
		PriorityQueue<Integer> eQ = new PriorityQueue<>();
		int answer = 1;
		int tmp = 0;
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
				eQ.offer(sQ.poll().end);
				tmp --;
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
			st.nextToken();
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sQ.offer(new Room(start, end));
		}
		System.out.println(T.solution());
	}
}
