import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2075 N번째 큰 수
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(Integer.parseInt(st.nextToken()));
		}
		for(int i = 1 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(pQ.peek() < tmp) {
					pQ.poll();
					pQ.offer(tmp);
				}
			}
		}
		System.out.println(pQ.poll());
	}
}	
