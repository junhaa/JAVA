import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27940 가지 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cur = 0;
		boolean flag = false;
		int i;
		for(i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			cur += Integer.parseInt(st.nextToken());
			if(cur > K) { 
				flag = true;
				break;
			}
		}
		if(flag) System.out.println(++i + " 1");
		else System.out.println(-1);
	}
}
