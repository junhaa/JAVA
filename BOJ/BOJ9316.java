import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9316 Hello Judge
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			sb.append("Hello World, Judge " + i + "!" + "\n");
		}
		System.out.println(sb);
	}
}
