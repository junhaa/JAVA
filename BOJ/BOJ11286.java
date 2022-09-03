import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// BOJ #11286 Àý´ñ°ª Èü
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		PriorityQueue<Integer> nQ = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0){
				if(pQ.isEmpty() && nQ.isEmpty()) sb.append('0').append('\n');
				else {
					if(!pQ.isEmpty() && !nQ.isEmpty()) {
						if(pQ.peek() >= nQ.peek()) {
							sb.append(-1 * nQ.poll()).append('\n');
						}
						else sb.append(pQ.poll()).append('\n');
					}
					else if(pQ.isEmpty()) {
						sb.append(-1 * nQ.poll()).append('\n');
					}
					else sb.append(pQ.poll()).append('\n');
				}
			}
			else {
				if(num < 0) nQ.offer(num * -1);
				else pQ.offer(num);
			}
		}
		System.out.println(sb);
	}
}
