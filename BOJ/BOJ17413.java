import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//BOJ #17413 단어 뒤집기 2
public class Main {
	static String input;
	static StringBuilder sb = new StringBuilder();
	static int reverse(int lt) {
		int rt = lt;
		int length = input.length();
		while(true) {
			if(rt + 1 >= length) break;
			else if(input.charAt(rt + 1) == ' ' || input.charAt(rt + 1) == '<') break;
			rt ++;
		}
		for (int i = rt; i >= lt; i--) {
			sb.append(input.charAt(i));
		}
		return rt;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		boolean isTag = false;
		for(int i = 0 ; i < input.length() ; i ++) {
			if(isTag) { 
				if(input.charAt(i) == '>') {
					isTag = false;
					sb.append(input.charAt(i));
				}
				else sb.append(input.charAt(i));
			}
			else {
				if(input.charAt(i) == '<') {
					isTag = true;
					sb.append(input.charAt(i));
				}	
				else if(input.charAt(i) == ' ') sb.append(' ');
				else {
					i = T.reverse(i);
				}
			}
			
		}
		System.out.println(sb);
	}
}
