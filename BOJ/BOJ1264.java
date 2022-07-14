import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// BOJ #1264 모음의 개수
public class Main {
	static ArrayList<Integer> arr = new ArrayList<>();
	public void solution(String str) {
		int answer = 0;
		str = str.toLowerCase();
		for(char x : str.toCharArray()) {
			if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
				answer ++;
			}
		}
		arr.add(answer);
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str = br.readLine();
			if(!str.equals("#")) T.solution(str);
			else break;
		}
		for(int x : arr) {
			System.out.println(x);
		}
	}
}
