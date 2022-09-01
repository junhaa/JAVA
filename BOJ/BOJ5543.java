import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// BOJ #5543 상근날드
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> hQ = new PriorityQueue<>();
		PriorityQueue<Integer> dQ = new PriorityQueue<>();
		for(int i = 0 ; i < 3 ; i ++) {
			hQ.offer(Integer.parseInt(br.readLine()));
		}
		for(int i = 0 ; i < 2 ; i ++) {
			dQ.offer(Integer.parseInt(br.readLine()));
		}
		System.out.println(hQ.poll() + dQ.poll() - 50);
	}
}
