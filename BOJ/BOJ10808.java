import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10808 ¾ËÆÄºª °³¼ö
public class Main {
	static int[] arr = new int[26];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		for(int i = 0 ; i < input.length(); i ++) {
			arr[input.charAt(i) - 'a'] ++;
		}
		for(int i = 0 ; i < 26 ; i ++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}
}
