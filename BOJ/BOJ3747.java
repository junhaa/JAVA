import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #3747 완벽한 선거!
public class Main {

	static int[] sn, dfsn;
	static boolean[] finished;
	static int cnt = 0, SN = 1;
	static ArrayList<Integer>[] edge;
	static Stack<Integer> stack = new Stack<>();
	
 	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int getSCC(int i) {
		dfsn[i] = ++cnt;
		stack.add(i);
		
		int result = dfsn[i];
		for(int next : edge[i]) {
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
	
	public static void main(String[] args) throws IOException { // TC 이상함
		Main T = new Main(); 
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String input;
		while(sc.hasNextInt()) {
			//st = new StringTokenizer(input);
			int N = sc.nextInt();
			int M = sc.nextInt();
			boolean flag = true;
			sn = new int[N * 2];
			dfsn = new int[N * 2];
			finished = new boolean[N * 2];
			edge = new ArrayList[N * 2];
			for(int i = 0 ; i < N * 2 ; i ++) {
				edge[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < M ; i ++) {
				int A = sc.nextInt();
				int B = sc.nextInt();
				A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
				B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
				edge[oppo(A)].add(B);
				edge[oppo(B)].add(A);
			}
			for(int i = 0 ; i < N * 2 ; i ++) {
				if(dfsn[i] == 0) T.getSCC(i);
			}
			for(int i = 0 ; i < N ; i ++) {
				if(sn[i * 2] == sn[i * 2 + 1]) { 
					flag = false;
					break;
				}
			}
			if(flag) sb.append(1 + "\n");
			else sb.append(0 + "\n");
		}
		System.out.println(sb);
	}
}
