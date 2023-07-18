import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #1254 팰린드롬 만들기
public class Main {

	static String S;
	
	static boolean isPelindrome(int lt) {
		int rt = S.length() - 1;
		while(lt < rt) {
			if(S.charAt(lt) != S.charAt(rt)) {
				return false;
			}
			lt ++;
			rt --;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		Stack<Character> stack = new Stack<>();
		int start = 0;
		while(!isPelindrome(start)) {
			stack.push(S.charAt(start));
			start ++;
		}
		while(!stack.isEmpty()) {
			S += stack.pop();
		}
		System.out.println(S.length());
	}
}
