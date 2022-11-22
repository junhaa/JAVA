import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12844 XOR
public class Main {
	static int[] tree, lazy;
	static int size = 2;
	

	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if((nodeR - nodeL + 1) % 2 == 1) { 
				tree[nodeNum] ^= lazy[nodeNum];
			}
			if(nodeNum < size / 2) {
				lazy[nodeNum * 2] ^= lazy[nodeNum];
				lazy[nodeNum * 2 + 1] ^= lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		tree[nodeNum] = tree[nodeNum * 2] ^ tree[nodeNum * 2 + 1];
	}
	
	static int findXOR(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findXOR(L, R, nodeNum * 2, nodeL, mid) ^ findXOR(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
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
		tree = new int[size];
		lazy = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[i + size / 2] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				T.update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(T.findXOR(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
