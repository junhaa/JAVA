import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1343 폴리오미노 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		int size = 0;
		for(int i = 0 ; i < input.length() ; i ++) {
			char cur = input.charAt(i);
			if(cur == 'X') size ++;
			else {
				if(size == 0) {
					sb.append(".");
				}
				else {
					if(size % 2 == 1) { 
						System.out.println(-1);
						return;
					}
					else {
						while(true) {
							if(size >= 4) {
								sb.append("AAAA");
								size -= 4;
								if(size == 0) break;
							}
							else {
								sb.append("BB");
								size -= 2;
								break;
							}
						}
					}
					sb.append(".");
				}
			}
		}
		if(size % 2 == 1) { 
			System.out.println(-1);
			return;
		}
		else {
			if(size != 0) {
				while(true) {
					if(size >= 4) {
						sb.append("AAAA");
						size -= 4;
						if(size == 0) break;
					}
					else {
						sb.append("BB");
						size -= 2;
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}
