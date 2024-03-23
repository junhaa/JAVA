import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #5357 Dedupe
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			char last = ' ';
			for (int j = 0; j < input.length(); j++) {
				if (last != input.charAt(j)) {
					sb.append(input.charAt(j));
					last = input.charAt(j);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
