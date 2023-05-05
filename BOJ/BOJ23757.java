import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #23757 아이들과 선물 상자
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(pQ.peek() < tmp) {
				System.out.println(0);
				return;
			}
			if(pQ.peek() == tmp) pQ.poll();
			else pQ.offer(pQ.poll() - tmp);
		}
		System.out.println(1);
	}
}
