import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11365 !밀비 급일
public class Main {

	static String solution(String input) {
		String str = "";
		for(int i = input.length() - 1 ; i >= 0 ; i --) {
			str += input.charAt(i);
		}
		return str;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String input = br.readLine();
			if(input.equals("END")) break;
			sb.append(T.solution(input)).append('\n');
		}
		System.out.println(sb);
	}
}
