import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #8545 Zadanie próbne
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		char tmp = arr[0];
		arr[0] = arr[2];
		arr[2] = tmp;
		for (char c : arr) {
			System.out.print(c);
		}
	}
}
