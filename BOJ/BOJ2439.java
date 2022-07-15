import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2439 º° Âï±â - 2
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			for(int k = 1; k <= N - i ; k ++) {
				System.out.print(" ");				
			}
			for(int k = 0 ; k < i ; k ++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
