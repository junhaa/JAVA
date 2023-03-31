import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #12933 오리
public class Main {

	static int[] duck = new int[500];
	static boolean[] finished = new boolean[500];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int answer = 0;
		for(int i = 0 ; i < input.length() ; i ++) {
			char cur = input.charAt(i);
			int curN = -1;
			switch (cur) {
			case 'q':
				curN = 0;
				break;
				
			case 'u':
				curN = 1;
				break;
				
			case 'a':
				curN = 2;
				break;
				
			case 'c':
				curN = 3;
				break;
				
			case 'k':
				curN = 4;
				break;
			}
			boolean flag = false;
			for(int j = 0 ; j < 500 ; j ++) {
				if(duck[j] == curN) {
					if(curN == 4) {
						if(!finished[j]) {
							finished[j] = true;
							answer ++;
						}
						duck[j] = 0;
					}
					else {
						duck[j] ++;
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println(-1);
				return;
			}
		}
		for(int i = 0 ; i < 500 ; i ++) {
			if(duck[i] != 0) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(answer);
	}
}
