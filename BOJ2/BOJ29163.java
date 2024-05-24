import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #29163 Счастье Мистера Бина
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int a = 0;
		int b = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(st.nextToken()) % 2 == 0) {
				a++;
			} else
				b++;
		}
		if (a > b)
			System.out.println("Happy");
		else
			System.out.println("Sad");
	}
}
