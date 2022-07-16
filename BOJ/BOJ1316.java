import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//BOJ #1316 그룹 단어 체커
public class Main {
	public static boolean solution(String str) {
		char last = ' ';
		ArrayList<Character> list = new ArrayList<>();
		for(int i = 0 ; i < str.length() ; i ++) {
			if(str.charAt(i) != last ) {
				if(!list.contains(str.charAt(i))){
					list.add(str.charAt(i));
				}
				else return false;
			}
			last = str.charAt(i);
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			boolean flag = true;
			String str = br.readLine();
			flag = T.solution(str);
			if(flag) answer ++;
		}
		System.out.println(answer);
	}
}
