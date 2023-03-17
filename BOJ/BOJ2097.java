import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2097 조약돌
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N <= 4) { 
			System.out.println(4);
			return;
		}
		int c, r;
		if(Math.sqrt(N) > (int)Math.sqrt(N)) {
			c = (int)Math.sqrt(N) + 1;
		}
		else c = (int)Math.sqrt(N);
		if((double)N / c > N / c) {
			r = N / c + 1;
		}
		else r = N / c;
		System.out.println(2 * (r - 1) + 2 * (c - 1));
	}
}
