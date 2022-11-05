import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2810 컵홀더
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int L = 0;
		String input = br.readLine();
		for(int i = 0 ; i < N ; i ++) {
			if(input.charAt(i) == 'L') L ++;
		}
		int cup = N - (L / 2) + 1;
		System.out.println(Math.min(N, cup));
	}
}
