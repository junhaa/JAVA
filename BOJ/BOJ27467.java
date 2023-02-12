import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27467 수학 퀴즈
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int w0 = 0;
		int w1 = 0;
		int w2 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp >= 3) tmp %= 3;
			if(tmp == 0) w0 ++;
			else if(tmp == 1) w1 ++;
			else w2 ++;
		}
		double p, q;
		p = w1 - w2;
		q = w0 - w2;
		System.out.println(p + " " + q);
		
	}
}
