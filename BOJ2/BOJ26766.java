import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #26766 Serca
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String heart = " @@@   @@@ \n"
			+ "@   @ @   @\n"
			+ "@    @    @\n"
			+ "@         @\n"
			+ " @       @ \n"
			+ "  @     @  \n"
			+ "   @   @   \n"
			+ "    @ @    \n"
			+ "     @     \n";

		for (int i = 0; i < N; i++) {
			sb.append(heart);
		}
		System.out.println(sb);

	}
}
