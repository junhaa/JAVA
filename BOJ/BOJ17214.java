import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #17214 다항 함수의 적분
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		if(str.equals("0")) {
			System.out.println("W");
			return;
		}
		int a = 1; // 1 + 2 - 
		String n = "";
		int c = 0;
		boolean first = false;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < str.length() ; i ++) {
			if(str.charAt(i) == '+' || str.charAt(i) == '-') {
				if(i != 0) {
					int num = Integer.parseInt(n) / (c + 1);
					if(!first) {
						if(a == 2) sb.append("-");
						first = true;
					}
					else {
						if(a == 2) sb.append("-");
						else sb.append("+");
					}
					if(num != 1) sb.append(num);
					for(int j = 0 ; j < c + 1 ; j ++) {
						sb.append("x");
					}
					n = "";
					c = 0;
				}
				if(str.charAt(i) == '+') {
					a = 1;
				}
				else a = 2;
			}
			else if(str.charAt(i) - '0' >= 0 && str.charAt(i) - '0' < 10) {
				n += str.charAt(i);
			}
			else {
				c ++;
			}
		}
		int num = Integer.parseInt(n) / (c + 1);
		if(!first) {
			if(a == 2) sb.append("-");
			first = true;
		}
		else {
			if(a == 2) sb.append("-");
			else sb.append("+");
		}
		if(num != 1) sb.append(num);
		for(int j = 0 ; j < c + 1 ; j ++) {
			sb.append("x");
		}
		sb.append("+W");
		System.out.println(sb);
	}
}
