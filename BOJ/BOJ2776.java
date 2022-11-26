import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #2776 암기왕
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			int N = Integer.parseInt(br.readLine());
			HashSet<Integer> set = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				if(set.contains(Integer.parseInt(st.nextToken()))) {
					sb.append("1" + "\n");
				}
				else {
					sb.append("0" + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
