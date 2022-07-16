import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;

// BOJ #1213 팰린드롬 만들기
public class Main {
	public String solution(String str) {
		char[] chArr = new char[str.length()];
		char[] toChar = str.toCharArray();
		Arrays.sort(toChar);
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
		for(char x : toChar) {
			map.put(x, map.getOrDefault(x, 0) + 1);
		}
		int cnt = 0;
		char oddChar =' ';
		for(char x : map.keySet()) {
			if(map.get(x) % 2 == 1) {
				cnt ++;
				oddChar = x;
			}
		}
		if(cnt > 1) return "I'm Sorry Hansoo";
		if(cnt == 1) { // 홀수개가 있을 때 
			chArr[str.length() / 2] = oddChar;
			map.put(oddChar, map.get(oddChar) - 1);
			if(map.get(oddChar) == 0) map.remove(oddChar);
		}
		int index = 0;
		for(char x : map.keySet()) {
			for(int i = 0 ; i < map.get(x) / 2 ; i ++) {
				chArr[index] = x;
				chArr[str.length() - 1 - index] = x;
				index ++;
			}
		}
		return String.valueOf(chArr);
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(T.solution(str));
	}
}
