import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #17609 회문
public class Main {
	static int check(String str, int lt ,int rt) { // flag 가 1일때
		while(lt < rt) {
			if(str.charAt(lt) != str.charAt(rt)) return 2;
			lt ++;
			rt --;
		}
		return 1;
	}
	
	static int solution(String str) {
		int answer = 0;
		boolean flag = false;
		int lt = 0 , rt = str.length() - 1;
		while(lt < rt) {
			if(str.charAt(lt) != str.charAt(rt)) {
				flag = true;
				answer = Math.min(check(str, lt + 1, rt) , check(str, lt, rt - 1));
				break;
			}
			else {
				rt --;
				lt ++;
			}
		}
		if(!flag) return 0;
		else return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			String tmp = br.readLine();
			sb.append(T.solution(tmp)).append('\n');
		}
		System.out.println(sb);
	}
}
