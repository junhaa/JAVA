import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11999 Milk Pails (Bronze)
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i * X <= M ; i ++) {
			for(int j = 0 ; j * Y <= M ; j ++) {
				if(i * X + j * Y <= M) {
					max = Math.max(max, i * X + j * Y);
				}
			}
		}
		System.out.println(max);
	}
}
