import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
// 2023 아주머학교 프로그래딩 정시머힌
public class Main {
	
	static HashMap<Character, Character> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map.put('@', 'a');
		map.put('[', 'c');
		map.put('!', 'i');
		map.put(';', 'j');
		map.put('^', 'n');
		map.put('0', 'o');
		map.put('7', 't');
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			int cnt = 0;
			int change = 0;
			String tmp = "";
			for(int j = 0 ; j < input.length() ; j ++) {
				char cur = input.charAt(j);
				if(map.containsKey(cur)) {
					tmp += map.get(cur);
					change ++;
				}
				else {
					if(cur == '\\') {
						cnt ++;
					}
					else if(cur == '\'') {
						if(cnt == 1) {
							tmp += 'v';
							change ++;
						}
						else if(cnt == 2) {
							tmp += 'w';
							change ++;
						}
						cnt = 0;
					}
					else tmp += cur;
				}
			}
			if((double)tmp.length() / 2 <= (double)change) {
				sb.append("I don't understand\n");
			}
			else sb.append(tmp + "\n");
		}
		System.out.println(sb);
	}
}
