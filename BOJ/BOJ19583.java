import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #19583 싸이버개강총회
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<String> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String time = st.nextToken();
		int S = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
		time = st.nextToken();
		int E = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
		time = st.nextToken();
		int Q = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
		String input;
		int answer = 0;
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			time = st.nextToken();
			int chatTime = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
			String next = st.nextToken();
			if(chatTime <= S) set.add(next);
			else if(chatTime >= E && chatTime <= Q && set.contains(next)) {
				set.remove(next);
				answer ++;
			}
		}
		System.out.println(answer);
	}
}
