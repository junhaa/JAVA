import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #15965 K번째 소수
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = 8000000;
		boolean[] ch = new boolean[N + 1];
		ch[0] = ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(N) ; i ++) {
			for(int k = i * i ; k <= N ; k += i) {
				ch[k] = true;
			}
		}
		int cnt = 0;
		for(int i = 2 ; i <= N ; i ++) {
			if(!ch[i]) {
				if(++cnt == K) {
					System.out.println(i);
					return;
				}
			}
		}
	}
}
