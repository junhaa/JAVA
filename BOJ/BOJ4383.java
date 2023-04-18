import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #4383 점프는 즐거워 
public class Main {
	static boolean ch[];
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			if(n == 1) {
				sb.append("Jolly\n");
				continue;
			}
			ch = new boolean[n];
			int cur = Integer.parseInt(st.nextToken());
			for(int i = 1 ; i < n ; i ++) {
				int next = Integer.parseInt(st.nextToken());
				ch[Math.abs(cur - next) > (n - 1) ? 0 : Math.abs(cur - next)] = true;
				cur = next;
			}
			boolean flag = false;
			for(int i = 1 ; i < n ; i ++) {
				if(!ch[i]) {
					flag = true;
					break;
				}
			}
			if(flag) {
				sb.append("Not jolly\n");
				
			}
			else {
				sb.append("Jolly\n");
			}
		}
		System.out.println(sb);
	}
}
