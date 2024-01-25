import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10996 별 찍기 - 21
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String l1 = "";
		String l2 = "";
		for (int i = 1; i <= N; i++) {
			if (i % 2 == 1) {
				l1 += "* ";
			} else {
				l2 += " *";
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(l1 + "\n");
			sb.append(l2 + "\n");
		}
		System.out.println(sb);
	}
}
