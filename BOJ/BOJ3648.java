import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #3648 아이돌
public class Main {

	static int[] dfsn, sn;
	static ArrayList<Integer>[] edge;
	static boolean[] finished;
	static Stack<Integer> stack = new Stack<>();
	static int cnt = 0, SN = 1;
	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int getSCC(int i) {
		dfsn[i] = ++cnt;
		stack.push(i);
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next), result);
			else if(!finished[next]) result = Math.min(dfsn[next], result);
		}
		
		if(dfsn[i] == result) {
			while(true) {
				int tmp = stack.pop();
				finished[tmp] = true;
				sn[tmp] = SN;
				if(tmp == i) break;
			}
			SN ++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		String input;
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			boolean flag = true;
			dfsn = new int[n * 2];
			sn = new int[n * 2];
			edge = new ArrayList[n * 2];
			finished = new boolean[n * 2];
			for(int i = 0 ; i < n * 2 ; i ++) {
				edge[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < m ; i ++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
				B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
				edge[oppo(A)].add(B);
				edge[oppo(B)].add(A);
			}
			edge[oppo(1)].add(1);
			edge[oppo(1)].add(1);
			for(int i = 0 ; i < n * 2 ; i ++) {
				if(dfsn[i] == 0) T.getSCC(i);
			}
			for(int i = 0 ; i < n ; i ++) {
				if(sn[i * 2] == sn[i * 2 + 1]) {
					flag = false;
					break;
				}
			}
			if(flag) sb.append("yes" + "\n");
			else sb.append("no" + "\n");
		}
		System.out.println(sb);
	}
}
