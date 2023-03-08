import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #2161 카드1
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1 ; i <= N ; i ++) {
			Q.offer(i);
		}
		StringBuilder sb = new StringBuilder();
		while(Q.size() != 1) {
			sb.append(Q.poll() + " ");
			Q.offer(Q.poll());
		}
		sb.append(Q.poll());
		System.out.println(sb);
	}
}
