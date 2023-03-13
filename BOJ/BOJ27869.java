import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27869 aFan Event Planning
public class Main {

	static int size = 2;
	static int[] tree;
	static long[] prefix;
	
	static int findNum(int nodeNum, int val) {
		if(nodeNum >= size / 2) return nodeNum - size / 2;
		if(tree[nodeNum * 2] < val) {
			return findNum(nodeNum * 2 + 1, val - tree[nodeNum * 2]);
		}
		else {
			return findNum(nodeNum * 2, val);
		}
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int i) {
		i += size / 2;
		tree[i] = 1;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		prefix = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken()); 
		}
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == N) continue;
				T.update(tmp);
			}
			else {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int sum = T.findSum(0, e - 1, 1, 0, size / 2 - 1);
				int last;
				if(sum == 0) last = s;
				else last = Math.max(T.findNum(1, sum) + 1, s);
				sb.append(prefix[e] - prefix[last - 1] + "\n");
			}
		}
		System.out.println(sb);
	}
}
