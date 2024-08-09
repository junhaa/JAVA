import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 17504 제리와 톰2
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long m = 1;
		long s = arr[0];

		for (int i = 1; i < N; i++) {
			m += s * arr[i];
			long tmp = m;
			m = s;
			s = tmp;
		}

		m = s - m;
		System.out.println(m + " " + s);
	}

}
