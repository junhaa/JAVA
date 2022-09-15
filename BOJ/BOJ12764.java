import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #12764 싸지방에 간 준하
class Computer implements Comparable<Computer>{
	int time;
	int num; //start > 끝나는 시간 end > 자리 번호 
	public Computer(int time, int num) {
		this.time = time;
		this.num = num;
	}
	@Override
	public int compareTo(Computer o) {
		return this.time - o.time;
	}
}
public class Main {
	static int[] use;
	static PriorityQueue<Computer> start = new PriorityQueue<>();
	static PriorityQueue<Computer> end = new PriorityQueue<>();
	static PriorityQueue<Integer> blank =  new PriorityQueue<>();

	static void solution() {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(true) {
			if(start.isEmpty()) break;
			if(end.isEmpty() || start.peek().time < end.peek().time) {
				if(blank.isEmpty()) {
					blank.offer(++cnt);
				}
				Computer tmpc = start.poll();
				int tmpb = blank.poll();
				use[tmpb] ++;
				end.offer(new Computer(tmpc.num, tmpb));
			}
			else if(start.isEmpty() || end.peek().time < start.peek().time) {
				Computer tmp = end.poll();
				blank.offer(tmp.num);
			}
			
		}
		sb.append(cnt + "\n");
		for(int i = 1 ; i <= cnt ; i ++) {
			sb.append(use[i] + " ");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		use = new int[N + 1];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int startT = Integer.parseInt(st.nextToken());
			int endT = Integer.parseInt(st.nextToken());
			start.offer(new Computer(startT, endT));
		}
		T.solution();
	}
}
