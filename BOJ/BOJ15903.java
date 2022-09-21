import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #15903 카드 합체 놀이
public class Main {

	static PriorityQueue<Long> pQ = new PriorityQueue<>();
	
	static long solution(int m) {
		long answer = 0;
		for(int i = 0 ; i < m ; i ++) {
			long num1 = pQ.poll();
			long num2 = pQ.poll();
			pQ.offer(num1 + num2);
			pQ.offer(num1 + num2);
		}
		while(!pQ.isEmpty()) {
			answer += pQ.poll();
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			pQ.offer(Long.parseLong(st.nextToken()));
		}
		System.out.println(T.solution(m));
	}
}
