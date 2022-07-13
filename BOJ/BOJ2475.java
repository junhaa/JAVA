import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2475 °ËÁõ¼ö
public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		int tmp;
		for(int i = 0 ; i < 5 ; i ++) {
			tmp = Integer.parseInt(st.nextToken());
			sum += tmp * tmp;
		}
		System.out.println(sum % 10);
	}
}
