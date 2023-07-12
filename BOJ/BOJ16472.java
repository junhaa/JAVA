import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #16472 고냥이
public class Main {
	static int[] ch = new int[26];

	static int add(int num) {
		ch[num] ++;
		if(ch[num] == 1) return 1;
		else return 0;
	}	
	
	static int delete(int num) {
		ch[num] --;
		if(ch[num] == 0) return -1;
		else return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		int cur = 0;
		int lt = 0;
		int rt = -1;
		
		int max = Integer.MIN_VALUE;
		while(rt < input.length()) {
			if(cur <= N) {
				if(rt == input.length() - 1) break;
				cur += T.add(input.charAt(++rt) - 'a');
			}
			else {
				cur += T.delete(input.charAt(lt++) - 'a');
			}
			if(cur <= N) max = Math.max(rt - lt + 1, max);
		}
		max = Math.max(rt - lt + 1, max);
		
		System.out.println(max);
	}
}
