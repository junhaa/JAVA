import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2446 별 찍기 - 9
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 1 ; k < 2 * N - i ; k ++) {
				if(k <= i) {
					sb.append(" ");
				}
				else sb.append("*");
			}
			sb.append("\n");
		}
		for(int i = N - 2 ; i >= 0 ; i --) {
			for(int k = 1 ; k < 2 * N - i ; k ++) {
				if(k <= i) {
					sb.append(" ");
				}
				else sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
