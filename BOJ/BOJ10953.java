import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10953 A + B - 6
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			String[] arr = br.readLine().split(",");
			sb.append(Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]) + "\n");
		}
		System.out.println(sb);
	}
}
