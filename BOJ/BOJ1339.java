import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #1339 단어 수학
public class Main {
	static int alpha[] = new int[27];
	
	static int solution() {
		Arrays.sort(alpha);
		int num = 9;
		int answer = 0;
		for(int i = 26 ; i >= 0 ; i --) {
			if(alpha[i] == 0) break;
			answer += alpha[i] * num--;
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			int length = input.length();
			for(int k = 0 ; k < length ; k ++) {
			alpha[input.charAt(k) - 'A'] += Math.pow(10, length - 1 - k);
			}
		}
		System.out.println(T.solution());
	}
}
