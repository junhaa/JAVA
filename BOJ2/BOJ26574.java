import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #26574 Copier
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(br.readLine());
			sb.append(cur + " " + cur + "\n");
		}
		System.out.println(sb);
	}
}
