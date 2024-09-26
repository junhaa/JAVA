import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #5217 쌍의 합
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T -- > 0){
			int n = Integer.parseInt(br.readLine());
			sb.append("Pairs for " + n + ":");
			int m = n % 2 == 0 ? (n - 1) / 2 : n / 2;
			for(int i = 1 ; i <= m ; i ++){
				if(i != 1){
					sb.append(",");
				}

				sb.append(" " + i + " " + (n - i));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
