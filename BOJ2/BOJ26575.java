import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #26575 Pups
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double res = Double.parseDouble(st.nextToken()) * Double.parseDouble(st.nextToken()) * Double.parseDouble(
				st.nextToken());
			res = Math.round(res * 100) / 100.0;
			sb.append("$" + String.format("%.2f", res)).append("\n");
		}
		System.out.println(sb);
	}
}
