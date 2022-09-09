import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// BOJ #11279 ÃÖ´ë Èü
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(pQ.isEmpty()) sb.append(0).append('\n');
				else sb.append(pQ.poll() * -1).append('\n');
			}
			else pQ.offer(-1 * num);
		}
		System.out.println(sb);
	}
}
