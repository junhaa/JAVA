import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #25372 성택이의 은밀한 비밀번호
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			if (input.length() <= 9 && input.length() >= 6) {
				sb.append("yes\n");
			} else
				sb.append("no\n");
		}
		System.out.println(sb);
	}
}
