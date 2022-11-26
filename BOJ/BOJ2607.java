import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2607 비슷한 단어
public class Main {

	static int[] arr = new int[26];
	static int[] iarr;
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String first = br.readLine();
		int answer = 0;
		for(int i = 0 ; i < first.length() ; i ++) {
			arr[first.charAt(i) - 'A'] ++;
		}
		for(int i = 0 ; i < N - 1 ; i ++) {
			iarr = new int[26];
			String input = br.readLine();
			for(int k = 0 ; k < input.length() ; k ++) {
				iarr[input.charAt(k) - 'A'] ++;
			}
			int num = 0;
			int difNum = 0;
			for(int k = 0 ; k < 26 ; k ++) {
				int dif = arr[k] - iarr[k];
				if(dif < 0) difNum += Math.abs(dif);
				else num += Math.abs(dif);
			}
			if(difNum > 1) continue;
			if(num > 1) continue;
			answer ++;
		}
		System.out.println(answer);
	}
}
