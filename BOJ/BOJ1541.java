import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1541 잃어버린 괄호
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] arr = input.split("\\-");
		int[] intArr = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i ++ ) {
			int sum = 0;
			String[] tmp = arr[i].split("\\+");
			for(int k = 0 ; k < tmp.length ; k ++) {
				sum += Integer.parseInt(tmp[k]);
			}
			intArr[i] = sum;
		}
		int answer = intArr[0];
		for(int i = 1 ; i < arr.length ; i ++) {
			answer -= intArr[i];
		}
		System.out.println(answer);
 	}
}
