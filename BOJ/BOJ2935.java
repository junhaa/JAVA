import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2935 공
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		boolean isMul = false;
		if (br.readLine().charAt(0) == '*') {
			isMul = true;
		} else {
			isMul = false;
		}
		int B = Integer.parseInt(br.readLine());

		System.out.println(isMul ? A * B : A + B);
	}
}
