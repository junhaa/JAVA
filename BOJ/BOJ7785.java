import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #7785 회사에 있는 사람 	
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		ArrayList<String> list = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String status = st.nextToken();
			if(status.equals("leave")) {
				set.remove(name);
			}
			else {
				set.add(name);
			}
		}
		for(String s : set) {
			list.add(s);
		}
		Collections.sort(list, Collections.reverseOrder());
		for(String n : list) {
			sb.append(n + "\n");
		}
		System.out.println(sb);
	}
}
