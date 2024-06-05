import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #5341 Pyramids
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			sb.append((N * (N + 1) / 2) + "\n");
		}
		System.out.println(sb);
	}
}
