import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #7894 큰 수
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int m = Integer.parseInt(br.readLine());
			double answer = 0.0;
			for (int i = 1; i <= m; i++) {
				answer += Math.log10(i);
			}
			sb.append((int)answer + 1 + "\n");
		}
		System.out.println(sb);
	}
}
