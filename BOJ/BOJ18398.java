import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #18398 HOMWRK
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			while (N-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()); // a, b <= 20000
				sb.append((a + b) + " " + (a * b) + "\n");
			}
		}
		System.out.println(sb);
	}
}
