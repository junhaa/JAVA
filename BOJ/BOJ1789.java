import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1789 수들의 합
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Long.parseLong(br.readLine());
		int i = 1;
		long sum = 0;
		while(true) {
			sum += i;
			if(sum > S) {
				System.out.println(i - 1);
				return;
			}
			i ++;
		}
	}
}
