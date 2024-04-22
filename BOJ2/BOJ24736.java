import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #24736 Football Scoring
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			sum += Integer.parseInt(st.nextToken()) * 6;
			sum += Integer.parseInt(st.nextToken()) * 3;
			sum += Integer.parseInt(st.nextToken()) * 2;
			sum += Integer.parseInt(st.nextToken()) * 1;
			sum += Integer.parseInt(st.nextToken()) * 2;
			sb.append(sum + " ");
		}
		System.out.println(sb);
	}
}
