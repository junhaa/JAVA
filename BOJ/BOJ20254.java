import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #20254 Site Score
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		sum += 56 * Integer.parseInt(st.nextToken());
		sum += 24 * Integer.parseInt(st.nextToken());
		sum += 14 * Integer.parseInt(st.nextToken());
		sum += 6 * Integer.parseInt(st.nextToken());
		System.out.println(sum);

	}
}
