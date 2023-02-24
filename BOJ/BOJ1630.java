import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1630 오민식
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = 1;
		int ch[] = new int[N + 1];
		if(N > 1) {
			for(int i = 2 ; i <= Math.sqrt(N) ; i ++) {
				if(ch[i] == 0) {
					for(int j = i ; j <= N ; j += i) {
						ch[j] ++;
					}
					int d = 1;
					while(Math.pow(i, d) <= N) {
						d ++;
					}
					ch[(int)Math.pow(i, --d)] = 0;
				}
			}
			for(int i = 2 ; i <= N ; i ++) {
				if(ch[i] == 0) {
					answer *= i;
					answer %= 987654321;
				}
			}
		}
		System.out.println(answer);
	}
}
