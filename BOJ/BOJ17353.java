import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17353 하늘에서 떨어지는 1, 2, ..., R-L+1 개의 별
public class Main {

	static int[] arr = new int[100001], dif = new int[100001];
	static long[] tree, lazy;
	static int size = 2;
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] += val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		
		if(nodeL != nodeR) {
			int mid = (nodeL + nodeR) / 2;
			update(L, R, nodeNum * 2, nodeL, mid, val);
			update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
			tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	static void updateM(int i, long val) { // lazy의 끝 다음 노드는 [1,n]구간합을 빼줘야 함
		i += size / 2;
		tree[i] -= val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new long[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				T.update(start, end, 1, 0, size / 2 - 1, 1);
				T.update(end + 1, end + 1, 1, 0, size / 2 - 1, -(end - start + 1));
			}
			else {
				int end = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findSum(0, end, 1, 0, size / 2 - 1) + arr[end] + "\n");
			}
		}
		System.out.println(sb);
	}
}
