import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2747 피보나치 수 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		arr[0] = 0;
		if(n > 0)arr[1] = 1;
		for(int i = 2 ; i <= n ; i ++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		System.out.println(arr[n]);
	}
}
