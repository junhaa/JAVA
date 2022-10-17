import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1978 소수 찾기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] ch = new boolean[1001];
		ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(1000) ; i ++) {
			for(int k = i * 2 ; k <= 1000 ; k += i) {
				ch[k] = true;
			}
		}
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(!ch[tmp]) answer ++;
		}
		System.out.println(answer);
	}
}
