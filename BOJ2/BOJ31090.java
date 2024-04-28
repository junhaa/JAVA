import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #31090 2023은 무엇이 특별할까?
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(br.readLine());
			int rem = cur % 100;

			if ((cur + 1) % rem == 0) {
				sb.append("Good\n");
			} else {
				sb.append("Bye\n");
			}
		}
		System.out.println(sb);
	}
}
