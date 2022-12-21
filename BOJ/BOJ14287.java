import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14287 회사 문화 3
public class Main {

	static int[] startIdx, endIdx;
	static long[] tree;
	static ArrayList<Integer>[] list;
	static int idx = -1, size = 2;
	
	static void DFS(int i) {
		startIdx[i] = ++idx;
		for(int next : list[i]) {
			DFS(next);
		}
		endIdx[i] = idx;
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer 
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n];
		for(int i = 0 ; i < n ; i ++) list[i] = new ArrayList<>();
		startIdx = new int[n];
		endIdx = new int[n];
		while(true) {
			if(size >= n) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i < n ; i ++) {
			list[Integer.parseInt(st.nextToken()) - 1].add(i);
		}
		T.DFS(0);
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				T.update(startIdx[Integer.parseInt(st.nextToken()) - 1], Integer.parseInt(st.nextToken()));
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findSum(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1) + "\n");
			}
			
		}
		System.out.println(sb);
	}
}
