import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2553 마지막 팩토리얼 수
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long cur = 1;
		int N = Integer.parseInt(br.readLine());
		for(int i = 2 ; i <= N ; i ++) {
			cur *= i;
			while(cur % 10 == 0) {
				cur /= 10;
			}
			cur %= 1000000;
		}
		System.out.println(cur % 10);
	}
}
