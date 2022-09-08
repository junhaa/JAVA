import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2562 ÃÖ´ñ°ª
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MIN_VALUE;
		int idx = 0;
		for(int i = 1 ; i <= 9 ; i ++) {
			int num = Integer.parseInt(br.readLine());
			if(num > answer) { 
				answer = num;
				idx = i;
			}
		}
		System.out.println(answer);
		System.out.println(idx);
	}
}
