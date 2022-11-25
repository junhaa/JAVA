import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// BOJ #1157 단어 공부
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		HashMap<Character, Integer> map = new HashMap<>();
		input = input.toUpperCase();
		for(int i = 0 ; i < input.length() ; i ++) {
			map.put(input.charAt(i), map.getOrDefault(input.charAt(i), 0) + 1);
		}
		char maxC = '?';
		int max = Integer.MIN_VALUE;
		for(char x : map.keySet()) {
			if(map.get(x) > max) {
				max = map.get(x);
				maxC = x;
			}
			else if(map.get(x) == max) {
				maxC = '?';
			}
		}
		System.out.println(maxC);
	}
}
