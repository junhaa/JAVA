import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1011 Fly me to the Alpha Centauri
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			long length = Long.parseLong(st.nextToken()) - Long.parseLong(st.nextToken());
			length *= -1;
			long num = 1;
			while(true) {
				if(num * num >= length) break;
				num ++;
			}
			if((((num -1) * (num - 1) + 1) + (num * num)) / 2 <= length) sb.append(2 * num - 1).append('\n');
			else sb.append(2 * num - 2).append('\n');
		}
		System.out.println(sb);
	}
}
