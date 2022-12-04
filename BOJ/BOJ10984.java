import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10984 내 학점을 구해줘
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			int num = 0;
			double sum = 0;
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				int tmp  = Integer.parseInt(st.nextToken());
				num += tmp;
				sum += Double.parseDouble(st.nextToken()) * tmp;
			}
			sb.append(num + " " + String.format("%.1f", sum / num) + "\n");
		}
		System.out.println(sb);
	}
}
