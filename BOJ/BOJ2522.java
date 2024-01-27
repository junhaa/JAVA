import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2522 별 찍기 - 12
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (j >= i)
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j >= i)
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
