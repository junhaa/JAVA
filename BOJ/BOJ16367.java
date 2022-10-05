import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #16367 TV Show Game
class Node implements Comparable<Node>{
	int snum;
	int idx;
	public Node(int snum, int idx) {
		this.snum = snum;
		this.idx = idx;
	}
	public int compareTo(Node o) {
		return o.snum - this.snum;
	}
}
public class Main {
	static int[] sn, dfsn;
	static ArrayList<Integer>[] edge;
	static boolean[] finished;
	static int cnt, SN;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<Node> seq = new ArrayList<>();
	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int getSCC(int i) {
		stack.push(i);
		dfsn[i] = ++cnt;
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next), result);
			else if(!finished[next]) result = Math.min(dfsn[next], result);
		}
		
		if(result == dfsn[i]) {
			while(true) {
				int tmp = stack.pop();
				finished[tmp] = true;
				sn[tmp] = SN;
				seq.add(new Node(sn[tmp], tmp));
				if(tmp == i) break;
			}
			SN ++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException{ // R -1 B 1
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		sn = new int[k * 2];
		finished = new boolean[k * 2];
		dfsn = new int[k * 2];
		edge = new ArrayList[k * 2];
		for(int i = 0 ; i < k * 2; i ++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("R")) tmp1 *= -1;
			int tmp2 = Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("R")) tmp2 *= -1;
			int tmp3 = Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("R")) tmp3 *= -1;
			tmp1 = (tmp1 < 0 ? -(tmp1 + 1) * 2 : tmp1 * 2 - 1);
			tmp2 = (tmp2 < 0 ? -(tmp2 + 1) * 2 : tmp2 * 2 - 1);
			tmp3 = (tmp3 < 0 ? -(tmp3 + 1) * 2 : tmp3 * 2 - 1);
			edge[oppo(tmp1)].add(tmp2);
			edge[oppo(tmp2)].add(tmp1);
			edge[oppo(tmp1)].add(tmp3);
			edge[oppo(tmp3)].add(tmp1);
			edge[oppo(tmp2)].add(tmp3);
			edge[oppo(tmp3)].add(tmp2);
		}
		cnt = 0;
		SN = 1;
		for(int i = 0 ; i < k * 2 ; i ++) {
			if(dfsn[i] == 0) T.getSCC(i);
		}
		
		for(int i = 0 ; i < k ; i ++) {
			if(sn[i * 2] == sn[i * 2 + 1]) {
				System.out.println("-1");
				return;
			}
		}
		Collections.sort(seq);
		int[] num = new int[k * 2];
		Arrays.fill(num, -1);
		for(Node node : seq) {
			if(num[node.idx] == -1) {
				num[node.idx] = 0;
				num[oppo(node.idx)] = 1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < k ; i ++) {
			if(num[i * 2 + 1] == 1) {
				sb.append("B");
			}
			else sb.append("R");
		}
		System.out.println(sb);
	}
}
