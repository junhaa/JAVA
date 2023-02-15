import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #16432 떡장수와 호랑이
public class Main {

	static boolean flag;
	static boolean[][] ch;
	static int[] answer;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int N;
	
	static boolean solution(int idx, int last) {
		if(idx == N) {
			flag = true;
			return true;
		}
		if(ch[idx][last]) return false;
		ch[idx][last] = true;
		boolean tmp = false;
		for(int i = 0 ; i < list.get(idx).size() ; i ++) {
			int cur = list.get(idx).get(i);
			if(cur == last) continue;
			answer[idx] = cur;
			tmp = solution(idx + 1, cur);
			if(flag) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = new int[N];
		ch = new boolean[N][10];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int k = 0 ; k < m ; k ++) {
				tmp.add(Integer.parseInt(st.nextToken()));	
			}
			list.add(tmp);
		}
		for(int i = 0 ; i < list.get(0).size() ; i ++) {
			answer[0] = list.get(0).get(i);
			T.solution(1, list.get(0).get(i));
			if(flag) break;
		}
		if(flag) {
			for(int i = 0 ; i < N ; i ++) {
				sb.append(answer[i] + "\n");
			}
			System.out.println(sb);
		}
		else System.out.println(-1);
	}
}
