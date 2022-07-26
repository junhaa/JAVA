import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1259 팰린드롬수
public class Main {
	static StringBuilder sb = new StringBuilder();
	public static String solution(String a) {
		boolean flag = true;
		int lt = 0, rt = a.length() - 1;
		while(lt < rt) {
			if(a.charAt(lt) != a.charAt(rt)) {
				flag = false;
				break;
			}
			lt ++;
			rt --;
		}
		if(flag) return "yes";
		else return "no";
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str = br.readLine();
			if(str.equals("0")) {
				System.out.println(sb);
				break;
			}
			else sb.append(T.solution(str)).append('\n');
		}
		
	}
}
