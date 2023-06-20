import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2083 럭비 클럽
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=  new StringBuilder() ;
		while(true) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			if(name.equals("#")) break;
			int age = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(age > 17 || weight >= 80) {
				sb.append(name + " Senior\n");
			}
			else {
				sb.append(name + " Junior\n");
			}
		}
		System.out.println(sb);
	}
}	
