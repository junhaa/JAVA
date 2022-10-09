import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #10845 큐
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> Q = new LinkedList<>();
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int back = 0;
		while(N -- > 0) {
			String input = br.readLine();
			st = new StringTokenizer(input);
			if(st.countTokens() > 1) {
				st.nextToken();
				back = Integer.parseInt(st.nextToken());
				Q.offer(back);
			}
			else {
				if(input.equals("front")) {
					if(Q.isEmpty()) sb.append("-1" + "\n");
					else sb.append(Q.peek() + "\n");
				}
				else if(input.equals("back")) {
					if(Q.isEmpty()) sb.append("-1" + "\n");
					else sb.append(back + "\n");
				}
				else if(input.equals("pop")) {
					if(Q.isEmpty()) sb.append("-1" + "\n");
					else sb.append(Q.poll() + "\n");
				}
				else if(input.equals("empty")) {
					if(Q.isEmpty()) sb.append("1" + "\n");
					else sb.append("0" + "\n");
				}
				else if(input.equals("size")) {
					sb.append(Q.size() + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
