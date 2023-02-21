import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #20164 홀수 홀릭 호석
class Answer{
	int max, min;
	public Answer(int max, int min) {
		this.max = max;
		this.min = min;
	}
}
public class Main {

	static Answer solution(String s) {
		int num = 0;
		for(int i = 0 ; i < s.length() ; i ++) {
			if((s.charAt(i) - '0') % 2 == 1) num ++;
		}
		if(s.length() == 1) {
			return new Answer(num, num);
		}
		else if(s.length() == 2) {
			Answer a = solution(String.valueOf(s.charAt(0) -'0' + s.charAt(1) - '0'));
			a.max += num;
			a.min += num;
			return a;
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 1 ; i <= s.length() - 2 ; i ++) {
			for(int j = i + 1 ; j <= s.length() - 1 ; j ++) {
				String tmp1 = s.substring(0, i);
				String tmp2 = s.substring(i, j);
				String tmp3 = s.substring(j);
				int next = Integer.parseInt(tmp1) + Integer.parseInt(tmp2) + Integer.parseInt(tmp3);
				Answer a = solution(String.valueOf(next));
				max = Math.max(max, a.max);
				min = Math.min(min, a.min);
			}
		}
		return new Answer(max + num, min + num);
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		Answer a = T.solution(tmp);
		System.out.println(a.min + " " + a.max);
	}
}
