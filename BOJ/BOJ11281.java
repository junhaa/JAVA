import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #11281 2-SAT - 4
class Node implements Comparable<Node>{
	int snum;
	int idx;
	public Node(int snum, int idx) {
		this.snum = snum;
		this.idx = idx;
	}
	@Override
	public int compareTo(Node o) {
		return o.snum - this.snum;
	}
}

public class Main {
	
	static boolean[] finished;
	static ArrayList<Integer>[] edge;
	static int[] sn, sccNum;
	static int[] p;
	static int cnt = 0, SN = 1;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<Node> list = new ArrayList<>();
	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int DFS(int i) {
		sn[i] = ++cnt;
		stack.push(i);
		
		int result = sn[i];
		for(int next : edge[i]) {
			if(sn[next] == 0) result = Math.min(DFS(next), result);
			else if(!finished[next]) result = Math.min(sn[next], result);
		}
		
		if(result == sn[i]) {
			while(true) {
				int tmp = stack.pop();
				finished[tmp] = true;
				sccNum[tmp] = SN;
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
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sn = new int[N * 2];
		sccNum = new int[N * 2];
		finished = new boolean[N * 2];
		edge = new ArrayList[N * 2];
		p = new int[N * 2];
		Arrays.fill(p, -1);
		for(int i = 0 ; i < N * 2 ; i ++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
			B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
			edge[oppo(A)].add(B);
			edge[oppo(B)].add(A);
		}
		for(int i = 0 ; i < N * 2 ; i ++) {
			if(sn[i] == 0) T.DFS(i);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(sccNum[i * 2] == sccNum[i * 2 + 1]) {
				System.out.println(0);
				return;
			}
		}
		for(int i = 0 ; i < N * 2 ; i ++) {
			list.add(new Node(sccNum[i], i));
		}
		Collections.sort(list);
		
		for(int i = 0 ; i < list.size() ; i ++) {
			int tmp = list.get(i).idx;
			if(p[tmp] == -1) {
				p[tmp] = 0 ;
				p[T.oppo(tmp)] = 1;
			}
		}
		sb.append("1" + "\n");
		for(int i = 0 ; i < N ; i ++) {
			sb.append(p[i * 2 + 1] + " ");
		}
		System.out.println(sb);
	}
}
