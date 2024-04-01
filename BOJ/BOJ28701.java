import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #28701 세제곱의 합
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum1 = 0;
		for (int i = 1; i <= N; i++) {
			sum1 += i;
		}
		System.out.println(sum1 + "\n" + (int)Math.pow(sum1, 2) + "\n" + (int)Math.pow(sum1, 2));
	}
}
