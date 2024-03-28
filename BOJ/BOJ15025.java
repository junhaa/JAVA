import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15025 Judging Moose
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		if (l == r) {
			if (l == 0) {
				System.out.println("Not a moose");
				return;
			}
			System.out.println("Even " + (l + r));
		} else
			System.out.println("Odd " + Math.max(l, r) * 2);
	}
}
