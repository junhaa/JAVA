import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #25193 곰곰이의 식단 관리 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		int[] ch = new int[2];
		for(int i = 0 ; i < input.length() ; i ++) {
			if(input.charAt(i) == 'C') {
				ch[0] ++;
			}
			else ch[1] ++;
		}
		double div = (double)ch[0] / (ch[1] + 1);
		if(div > (int)ch[0] / (ch[1] + 1)) { 
			System.out.println((int)ch[0] / (ch[1] + 1) + 1);
		}
		else System.out.println((int)div);
	}
}
