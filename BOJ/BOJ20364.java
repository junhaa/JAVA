import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #20364 부동산 다툼
public class Main {
	static HashSet<Integer> set = new HashSet<>();
	static int getFirst(int cur) {
		int tmp = cur;
		int result = 0;
		if(set.contains(cur)) result = cur;
		while(cur > 1) {
			if(cur % 2 == 1) cur --;
			cur /= 2;
			if(set.contains(cur)) result = cur;
		}
		if(result == 0) {
			set.add(tmp);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q ; i ++) {
			sb.append(T.getFirst(Integer.parseInt(br.readLine())) + "\n");
		}
		System.out.println(sb);
	}
}	
