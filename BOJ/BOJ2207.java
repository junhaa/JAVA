import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2207 가위바위보
public class Main {

	static ArrayList<Integer>[] list;
	static int[] sn, dfsn;
	static Stack<Integer> stack = new Stack<>();
	static boolean[] finished;
	static int cnt, SN;
	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int getSCC(int i) {
		stack.push(i);
		dfsn[i] = ++cnt;
		
		int result = dfsn[i];
		for(int next : list[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next), result);
			else if(!finished[next]) result = Math.min(dfsn[next], result);
		}
		
		if(result == dfsn[i]) {
			while(true) {
				int tmp = stack.pop();
				sn[tmp] = SN;
				finished[tmp] = true;
				if(tmp == i) break;
			}
			SN ++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[M * 2];
		dfsn = new int[M * 2];
		sn = new int[M * 2];
		finished = new boolean[M * 2];
		for(int i = 0 ; i < M * 2 ; i ++) list[i] = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
			B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
			list[oppo(A)].add(B);
			list[oppo(B)].add(A);
		}
		cnt = 0;
		SN = 1;
		for(int i = 0 ; i < M * 2 ; i ++) {
			if(dfsn[i] == 0) T.getSCC(i);
		}
		for(int i = 0 ; i < M ; i ++) {
			if(sn[i * 2] == sn[i * 2 + 1]) { 
				System.out.println("OTL");
				return;
			}
		}
		System.out.println("^_^");
	}
}
