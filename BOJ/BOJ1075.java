import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1075 나누기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());
		int num = N / 100 * 100;
		int i = 0;
		for(i = 0 ; i < 100 ; i ++) {
			if((num + i) % F == 0){
					break;
			}
		}
		System.out.println(String.format("%02d", i));
	}
}
