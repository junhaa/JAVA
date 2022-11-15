import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

// BOJ #1935 후위 표기식2
public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		Stack<Double> s = new Stack<>();
		HashMap<Character, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N ; i ++) {
			map.put((char)((int)'A' + i), Integer.parseInt(br.readLine()));
		}
		for(int i = 0 ; i < input.length() ; i ++) {
			if(Character.isAlphabetic(input.charAt(i))) {
				s.push((double)map.get(input.charAt(i)));
			}
			else {
				double a = s.pop();
				double b = s.pop();
				switch (input.charAt(i)) {
				case '*': {
					s.push(b * a);
					break;
				}
				case '+': {
					s.push(b + a);
					break;
				}
				case '/': {
					s.push(b / a);
					break;
				}
				case '-': {
					s.push(b - a);
					break;
				}
				}
			}
		}
		System.out.println(String.format("%.2f", s.pop()));
	}
}
