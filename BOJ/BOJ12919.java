import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// BOJ #12919 A와 B 2
public class Main {

	static HashSet<String> set = new HashSet<>();
	static String S;
	static boolean flag = false;
	static void solution(String str) {
		if(str.length() < S.length()) return;
		if(str.equals(S)) {
			flag = true;
			return;
		}
		if(str.charAt(str.length() - 1) == 'A') {
			String tmp = "";
			for(int i = 0 ; i < str.length() - 1 ; i ++) {
				tmp += str.charAt(i);
			}
			if(!set.contains(tmp)) {
				set.add(tmp);
				solution(tmp);
			}
		}
		if(str.charAt(0) == 'B') {
			String tmp = "";
			for(int i = str.length() - 1 ; i > 0 ; i --) {
				tmp += str.charAt(i);
			}
			if(!set.contains(tmp)) {
				set.add(tmp);
				solution(tmp);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		String str = br.readLine();
		T.solution(str);
		if(flag) {
			System.out.println(1);
		}
		else System.out.println(0);
	}
}
