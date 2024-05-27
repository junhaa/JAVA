import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #30087 진흥원 세미나
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			if (input.equals("Algorithm")) {
				sb.append("204\n");
			} else if (input.equals("ArtificialIntelligence")) {
				sb.append("302\n");
			} else if (input.equals("CyberSecurity")) {
				sb.append("B101\n");
			} else if (input.equals("Network")) {
				sb.append("303\n");
			} else if (input.equals("Startup")) {
				sb.append("501\n");
			} else if (input.equals("TestStrategy")) {
				sb.append("105\n");
			}
		}
		System.out.println(sb);
	}
}
