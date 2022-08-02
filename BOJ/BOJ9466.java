import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #9466 텀 프로젝트
public class Main {
	static int[] choice ;
	static boolean[] ch; 
	static int answer, flag = -1; // flag -1 찾지 못함
	static HashSet<Integer> set;
	static void DFS(int x) {
		set.add(x);
		if(set.contains(choice[x])) {
			if(x == choice[x]) flag = -1;
			else flag = choice[x];
			answer --;
			ch[x] = true;
			return;
		}
		else if(!ch[choice[x]]) DFS(choice[x]);
		if(flag != -1) answer --;
		if(flag == x) flag = -1;
		ch[x] = true;
		return;
	}
	
	static void solution(int n) {
		for(int i = 1; i <= n ; i ++) {
			if(!ch[i]) {
				set = new HashSet<>();
				DFS(i);
				flag = -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			int n = Integer.parseInt(br.readLine());
			choice = new int[n + 1];
			ch = new boolean[n + 1];
			answer = n;
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= n ; i ++) {
				choice[i] = Integer.parseInt(st.nextToken());
			}
			T.solution(n);
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
}
