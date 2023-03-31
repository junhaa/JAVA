import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
// BOJ #27920 카드 뒤집기
public class Main {

	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> dQ = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		seq = new int[N];
		for(int i = 1 ; i <= N ; i ++) {
			if(i % 2 == 1) {
				dQ.addFirst(i);
			}
			else {
				dQ.addLast(i);
			}
		}
		int len = dQ.size();
		for(int i = 0 ; i < len ; i ++) {
			int tmp = dQ.pollFirst();
			sb.append(tmp + " ");
			seq[tmp - 1] = i + 1; 
		}
		sb.append("\n");
		for(int i = 0 ; i < N ; i ++) {
			sb.append(seq[i] + " ");
		}
		System.out.println("YES");
		System.out.println(sb);
	}
}
