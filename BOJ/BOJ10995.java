import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10995 별 찍기 - 20
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i % 2 == 0) {
					sb.append("* ");
				} else {
					sb.append(" *");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
