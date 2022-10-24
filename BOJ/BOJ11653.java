import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #11653 소인수분해
public class Main {
	static boolean ch[];
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		ch = new boolean[n + 1];
		ch[1] = true;
		for(int i = 2 ; i <= Math.sqrt(n) ; i ++) {
			for(int k = i * 2 ; k <= n ; k += i) {
				ch[k] = true;
			}
		}
		int tmp = n;
		while(true) {
			boolean isFinish = true;
			for(int i = 1 ; i <= n ; i ++) {
				if(!ch[i] && tmp % i == 0) {
					list.add(i);
					tmp /= i;
					isFinish = false;
				}
			}
			if(isFinish) break;
		}
		Collections.sort(list);
		for(int x : list) {
			sb.append(x + "\n");
		}
		System.out.println(sb);
	}
}
