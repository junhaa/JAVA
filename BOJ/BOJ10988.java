import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10988 팰린드롬인지 확인하기 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int ch = 1;
		int lt = 0;
		int rt = input.length() - 1;
		while(lt < rt) {
			if(input.charAt(lt) != input.charAt(rt)) {
				ch = 0;
				break;
			}
			lt ++;
			rt --;
		}
		System.out.println(ch);
	}
}
