import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #15897 잘못 구현한 에라토스테네스의 체
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long answer = n;
		for(int i = 1 ; i <= (int)Math.sqrt(n - 1) ; i ++){
			int modN = n - 1;
			answer += (modN / i - (i - 1)) * 2 - 1;
		}
		System.out.println(answer);
	}
}
