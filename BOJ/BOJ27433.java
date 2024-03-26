import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #27433 팩토리얼 2
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long answer = 1;
		int N = Integer.parseInt(br.readLine());
		for (int i = N; i >= 1; i--) {
			answer *= i;
		}
		System.out.println(answer);
	}
}
15
