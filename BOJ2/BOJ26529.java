import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #26529 Bunnies
public class Main {
	static int[] fibo = new int[46];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		fibo[0] = 1;
		fibo[1] = 1;
		for(int i = 2 ; i <= 45 ; i ++){
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		}

		StringBuilder sb = new StringBuilder();

		while(t -- > 0){
			int n = Integer.parseInt(br.readLine());
			sb.append(fibo[n] + "\n");
		}
		System.out.println(sb);

	}
}
