import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #18227 성대나라의 물탱크
public class Main {

	static int[] startIdx, endIdx;
	static long[] tree, levelIdx;
	static int size = 2;
	static ArrayList<Integer>[] list;
	static int idx = -1;
	
	static void DFS(int L, int j) {
		startIdx[j] = ++idx;
		levelIdx[j] = L;
		for(int next : list[j]) {
			if(levelIdx[next] == 0) {
				DFS(L + 1, next);
			}
		}
		endIdx[j] = idx;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken()) - 1;
		StringBuilder sb = new StringBuilder();
	
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		list = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
		startIdx = new int[N];
		endIdx = new int[N];
		levelIdx = new long[N];
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			list[start].add(end);
			list[end].add(start);
		}
		T.DFS(1, C);
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				T.update(startIdx[Integer.parseInt(st.nextToken()) - 1], 1);
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append((T.findSum(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1) * levelIdx[tmp]) + "\n");
			}
		}
		System.out.println(sb);
	}
}