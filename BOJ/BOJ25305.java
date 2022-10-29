import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #25305 커트라인
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(Integer.parseInt(st.nextToken()));
		}
		int idx = 0;
		while(true) {
			if(++idx == k) {
				System.out.println(pQ.poll());
				return;
			}
			else pQ.poll();
		}
}
}
