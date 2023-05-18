import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1817 짐 챙기는 숌 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		if(N == 0) {
			System.out.println(0);
			return;
		}
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int curSum = 0;
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(curSum + tmp > M) {
				answer ++;
				curSum = tmp;
			}
			else {
				curSum += tmp;
			}
		}
		System.out.println(answer + 1);
	}
}
