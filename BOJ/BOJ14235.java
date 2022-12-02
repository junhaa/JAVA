import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #14235 크리스마스 선물
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1) {
				if(pQ.isEmpty()) {
					sb.append("-1" + "\n");
				}
				else sb.append(pQ.poll() + "\n");
			}
			else {
				int num = Integer.parseInt(st.nextToken());
				for(int k = 0 ; k < num ; k ++) {
					pQ.offer(Integer.parseInt(st.nextToken()));
				}
			}
		}
		System.out.println(sb);
	}
}
