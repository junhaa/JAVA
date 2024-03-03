import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #13623 Zero or One
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		for (int x : arr) {
			sum += x;
		}
		if (sum == 1) {
			for (int i = 0; i < 3; i++) {
				if (arr[i] == 1) {
					System.out.println((char)('A' + i));
				}
			}
		} else if (sum == 2) {
			for (int i = 0; i < 3; i++) {
				if (arr[i] == 0) {
					System.out.println((char)('A' + i));
				}
			}
		} else {
			System.out.println('*');
		}
	}
}
