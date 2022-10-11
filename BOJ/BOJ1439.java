import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1439 뒤집기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] arr = new int[2];
		char last = input.charAt(0);
		for(int i = 1 ; i < input.length() ; i ++) {
			if(last != input.charAt(i)) {
				arr[last - '0'] ++;
				last = input.charAt(i);
			}
		}
		arr[last - '0'] ++;
		System.out.println(arr[0] < arr[1] ? arr[0] : arr[1]);
	}
}
