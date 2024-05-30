import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #26209 Intercepting Information
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 8; i++) {
			if (Integer.parseInt(st.nextToken()) == 9) {
				System.out.println("F");
				System.exit(0);
			}
		}
		System.out.println("S");
	}
}
