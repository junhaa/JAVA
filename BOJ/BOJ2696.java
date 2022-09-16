import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2696 중앙값 구하기
public class Main {
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static void solution(int N) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> dQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> uQ = new PriorityQueue<>();
		int mid = Integer.parseInt(st.nextToken());
		int cnt = 1;
		sb.append(mid + " ");
		for(int i = 2 ; i <= N ; i ++) {
			int num = Integer.parseInt(st.nextToken());
			if(num > mid) uQ.offer(num);
			else dQ.offer(num);
			if(i % 2 == 1) {
				if(dQ.size() > uQ.size()) {
					uQ.offer(mid);
					mid = dQ.poll();
				}
				else if(uQ.size() > dQ.size()) {
					dQ.offer(mid);
					mid = uQ.poll();
				}
				sb.append(mid + " ");
			}
			if(i % 10 == 0) {
				st = new StringTokenizer(br.readLine());
			}
			if(i % 20 == 0) sb.append('\n');
		}
		sb.append('\n');
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			if(N % 2 == 1) {
				sb.append((N + 1) / 2).append('\n');
			}
			else sb.append(N / 2).append('\n');
			T.solution(N);
		}
		System.out.println(sb);
	}
}
