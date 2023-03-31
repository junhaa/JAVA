import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #9536 여우는 어떻게 울지?
public class Main {

	static String[] str;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t -- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = st.countTokens();
			str = new String[len];
			HashSet<String> set = new HashSet<>();
			for(int i = 0 ; i < len ; i ++) {
				String tmp = st.nextToken();
				str[i] = tmp;
				set.add(tmp);
			}
			while(true) {
				String input = br.readLine();
				if(input.equals("what does the fox say?")) break;
				else {
					st = new StringTokenizer(input);
					st.nextToken();
					st.nextToken();
					String cur = st.nextToken();
					if(set.contains(cur)) set.remove(cur);
				}
			}
			for(int i = 0 ; i < len ; i ++) {
				if(set.contains(str[i])) sb.append(str[i] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
