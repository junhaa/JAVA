import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// BOJ #5522 카드 게임
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for(int i = 0 ; i < 5 ; i ++) {
			sum += Integer.parseInt(br.readLine());
		}
		System.out.println(sum);
	}
}
