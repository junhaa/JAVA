import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #18258 큐 2
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int last = 0;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()){
			case "push": {
				int tmp = Integer.parseInt(st.nextToken());
				Q.offer(tmp);
				last = tmp;
				break;
			}
			case "pop": {
				if(Q.isEmpty()) sb.append("-1" + "\n");
				else sb.append(Q.poll() + "\n");
				break;
			}
			case "size": {
				sb.append(Q.size() + "\n");
				break;
			}
			case "empty": {
				if(Q.isEmpty()) {
					sb.append("1" + "\n");
				}
				else {
					sb.append("0" + "\n");
				}
				break;
			}
			case "front": {
				if(Q.isEmpty()) sb.append("-1" + "\n");
				else sb.append(Q.peek() + "\n");
				break;
			}
			case "back": {
				if(Q.isEmpty()) sb.append("-1" + "\n");
				else sb.append(last + "\n");
				break;
			}
			}
		}
		System.out.println(sb);
	}
}
