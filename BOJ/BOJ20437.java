import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
// BOJ #20437 문자열 게임 2
public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			ArrayList<Integer>[] list = new ArrayList[26];
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0 ; i < 26 ; i ++) {
				list[i] = new ArrayList<>();
			}
			String tmp = br.readLine();
			int K = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < tmp.length() ; i ++) {
				list[tmp.charAt(i) - 'a'].add(i);
			}
			for(int i = 0 ; i < 26 ; i ++) {
				if(list[i].size() >= K) {
					for(int k = 0 ; k <= list[i].size() - K ; k ++) {
						int len = list[i].get(k + K - 1) - list[i].get(k) + 1;
						min = Math.min(len, min);
						max = Math.max(len, max);
					}
				}
			}
			if(min == Integer.MAX_VALUE) sb.append(-1 + "\n");
			else sb.append(min + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
