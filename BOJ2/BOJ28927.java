import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #28927 Киноманы
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = 0;
		int b = 0;

		a += Integer.parseInt(st.nextToken()) * 3 + Integer.parseInt(st.nextToken()) * 20
			+ Integer.parseInt(st.nextToken()) * 120;
		st = new StringTokenizer(br.readLine());
		b += Integer.parseInt(st.nextToken()) * 3 + Integer.parseInt(st.nextToken()) * 20
			+ Integer.parseInt(st.nextToken()) * 120;

		if (a < b) {
			System.out.println("Mel");
		} else if (a > b) {
			System.out.println("Max");
		} else {
			System.out.println("Draw");
		}

	}
}
