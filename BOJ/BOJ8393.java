import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #8393 гу
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int sum = 0;
		for(int i = 1 ; i <= n ; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
