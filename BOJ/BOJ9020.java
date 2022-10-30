import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #9020 골드바흐의 추측
public class Main {

	static boolean[] ch = new boolean[10001];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(10000) ; i ++) {
			for(int k = i * 2 ; k <= 10000 ; k += i) {
				ch[k] = true;
			}
		}
		while(test -- > 0) {
			int n = Integer.parseInt(br.readLine());
			for(int i = n / 2 ; i > 1 ; i --) {
				if(!ch[i] && !ch[n - i]) {
					sb.append(i + " " + (n - i) + "\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
