import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #1427 소트인사이드
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		char[] toArr = input.toCharArray();
		Arrays.sort(toArr);
		for(int i = toArr.length - 1 ; i >= 0 ; i --) {
			System.out.print(toArr[i]);
		}
	}
}
