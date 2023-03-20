import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11576 Base Conversion
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int num = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i = m - 1 ; i >= 0 ; i --) {
			num += Math.pow(A, i) * Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		while(true) {
			if(Math.pow(B, cnt) > num) break;
			cnt ++;
		}
		int r = num;
		for(int i = cnt - 1 ; i >= 1 ; i --) {
			int tmp = (int)Math.pow(B, i);
			sb.append(r / tmp + " ");
			r %= tmp;
		}
		sb.append(r);
		System.out.println(sb);
	}
}
