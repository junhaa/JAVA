import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27294 몇개고?
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		if (t <= 16 && t >= 12 && s == 0) {
			System.out.println(320);
		} else {
			System.out.println(280);
		}
	}
}
