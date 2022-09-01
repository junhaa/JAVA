import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11720 숫자의 합
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		String input = br.readLine();
		for(int i = 0 ; i < N ; i ++) {
			sum += input.charAt(i) - '0';
		}
		System.out.println(sum);
	}
}
