import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #2164 카드2
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1 ; i <= N ; i ++) {
			Q.offer(i);
		}
		while(Q.size() != 1) {
			Q.poll();
			Q.offer(Q.poll());
		}
		System.out.println(Q.poll());
	}
}
