import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1065 ÇÑ¼ö
public class Main {
	public boolean hansu(String input) {
		if(input.length() == 1 || input.length() == 2) return true;
		int d = input.charAt(1) - input.charAt(0);
		for(int i = 2 ; i < input.length() ; i ++) {
			if(input.charAt(i) - input.charAt(i - 1) != d) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		String input = "";
		int answer = 0;
		for(int i = 1 ; i <= Integer.parseInt(N) ; i ++) {
			boolean flag = T.hansu(input + i);
			if(flag) answer ++;
		}
		System.out.println(answer);
	}
}
