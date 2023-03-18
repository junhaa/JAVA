import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #7682 틱택토
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String tmp = br.readLine();
			if(tmp.equals("end")) break;
			if(T.check(tmp.toCharArray())) {
				sb.append("valid\n");
			}
			else sb.append("invalid\n");
		}
		System.out.println(sb);
	}
	
	static boolean check(char[] arr) {
		int cnto = 0;
		int cntx = 0;
		for(int i = 0 ; i < 9 ; i ++) {
			if(arr[i] == 'X') cntx ++;
			else if(arr[i] == 'O') cnto ++;
		}
		int bg = bingo(arr);
		if(cntx == 5 && cnto == 4) {
			if(bg == 2 || bg == -2) return true;
			else return false;
		}
		else if(cntx == cnto + 1) {
			if(bg == 2) return true;
			else return false;
		}
		else if(cntx == cnto) {
			if(bg == 1) return true;
			else return false;
		}
		else return false;
	}
	
	static int bingo(char[] arr) {
		boolean bino = false;
		boolean binx = false;
		
		for(int i = 0 ; i < 9 ; i += 3) {
			char cur = arr[i];
			for(int j = i + 1 ; j < i + 3 ; j ++) {
				if(cur != arr[j]) break;
				if(j == i + 2) {
					if(cur == 'O') bino = true;
					else if(cur == 'X') binx = true;
				}
			}
		}
		
		for(int i = 0 ; i < 3 ; i ++) {
			char cur = arr[i];
			for(int j = i + 3 ; j <= i + 6 ; j += 3) {
				if(cur != arr[j]) break;
				if(j == i + 6) {
					if(cur == 'O') bino = true;
					else if(cur == 'X') binx = true;
				}
			}
		}
		if(arr[0] == arr[4] && arr[4] == arr[8]) {
			if(arr[0] == 'O') bino = true;
			else if(arr[0] == 'X') binx = true;
		}
		if(arr[6] == arr[4] && arr[4] == arr[2]) { 
			if(arr[6] == 'O') bino = true;
			else if(arr[6] == 'X') binx = true;
		}
		if(bino && binx) return -1;
		else if(bino) return 1;
		else if(binx) return 2;
		else return -2;
	}
}
