import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #30224 Lucky 7
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		boolean flag = false;
		for (int i = 0; i < N.length(); i++) {
			if (N.charAt(i) == '7') {
				flag = true;
				break;
			}
		}
		if (flag) {
			if (Integer.parseInt(N) % 7 == 0) {
				System.out.println(3);
			} else {
				System.out.println(2);
			}
		} else {
			if (Integer.parseInt(N) % 7 == 0) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}
