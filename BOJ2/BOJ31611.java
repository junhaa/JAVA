import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #31611 火曜日 (Tuesday) 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N % 7 == 2) {
			System.out.println(1);
			return;
		}
		System.out.println(0);
	}
}
