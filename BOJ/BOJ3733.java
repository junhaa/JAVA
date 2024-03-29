import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #3733 Shares
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while ((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			sb.append(S / (N + 1) + "\n");
		}
		System.out.println(sb);

	}
}
