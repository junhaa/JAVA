import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #27919 UDPC파티
public class Main {

	static boolean[] flag = new boolean[4];
	static char[] ch = { 'U','D','P','C'};
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		int u = 0;
		int d = 0;
		int p = 0;
		int c = 0;
		for(int i = 0 ; i < input.length() ; i ++) {
			if(input.charAt(i) == 'U') {
				u ++;
			}
			else if(input.charAt(i) == 'D') {
				d ++;
			}
			else if(input.charAt(i) == 'P') {
				p ++;
			}
			else {
				c ++;
			}
		}
		if((d + p) % 2 == 1) {
			if(u + c > (d + p + 1) / 2) {
				flag[0] = true;
			}
		}
		else {
			if(u + c > (d + p) / 2) {
				flag[0] = true;
			}
		}
		if(d + p != 0) {
			flag[1] = true;
			flag[2] = true;
		}
		
		boolean canc = true;
		for(int i = 0 ; i < 4 ; i ++) {
			if(flag[i]) {
				canc = false;
			}
		}
		if(canc) {
			System.out.println('C');
			return;
		}
		for(int i = 0 ; i < 4; i ++) {
			if(flag[i]) {
				sb.append(ch[i]);
			}
		}
		System.out.println(sb);
	}
}
