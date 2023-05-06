import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #12904 A와 B
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String E = br.readLine();
		while(S.length() != E.length()) {
			if(E.charAt(E.length() - 1) == 'A') { 
				E = E.substring(0, E.length() - 1);
			}
			else {
				E = E.substring(0, E.length() - 1);
				StringBuilder tmp = new StringBuilder(E);
				E = tmp.reverse().toString();
			}
		}
		if(S.equals(E)) System.out.println(1);
		else System.out.println(0);
	}
}
