import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2577 숫자의 개수
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		String mul = String.valueOf(A * B * C);
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[10];
		for(int i = 0 ; i < mul.length() ; i ++) {
			arr[mul.charAt(i) - '0'] ++;
		}
		for(int i = 0 ; i < 10 ; i ++) {
			sb.append(arr[i] + "\n");
		}
		System.out.println(sb);
	}
}
