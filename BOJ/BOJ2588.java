import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ #2588 °ö¼À
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int num1 = Integer.parseInt(br.readLine());
		int num2 = Integer.parseInt(br.readLine());
		int tmp = num2;
		for(int i = 0 ; i < 3 ; i ++) {
			sb.append(num1 * (tmp % 10)).append('\n');
			tmp /= 10;
		}
		sb.append(num1 * num2);
		System.out.println(sb);
	}
}
