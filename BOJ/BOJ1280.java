import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1280 나무 심기
public class Main {

	static long tree[];
	static int cntTree[];
	static int size = 2;
	
	static void update(int i, int val, boolean isCnt) {
		if(isCnt) {
			i += size / 2;
			cntTree[i] += val;
			while(i > 1) {
				i /= 2;
				cntTree[i] = cntTree[i * 2] + cntTree[i * 2 + 1] ;
			}
		}
		else {
			i += size / 2;
			tree[i] += val;
			while(i > 1) {
				i /= 2;
				tree[i] = tree[i * 2] + tree[i * 2 + 1] ;
			}
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static long findCnt(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return cntTree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findCnt(L, R, nodeNum * 2, nodeL, mid) + findCnt(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= 200000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		cntTree = new int[size];
		int tmp = Integer.parseInt(br.readLine());
		T.update(tmp, 1, true);
		T.update(tmp, tmp, false);
		long answer = 1;
		for(int i = 1;  i < N ; i ++) {
			int cur = Integer.parseInt(br.readLine());
			T.update(cur, 1, true);
			T.update(cur, cur, false);
			answer = answer * ((((findCnt(0, cur - 1, 1, 0, size / 2 - 1) * cur) - findSum(0, cur - 1, 1, 0, size / 2 - 1)) + (findSum(cur + 1, size / 2, 1, 0, size / 2 - 1) - (findCnt(cur + 1, size / 2, 1, 0, size / 2 - 1) * cur))) % 1000000007) % 1000000007;
		}
		System.out.println(answer);
	}
}
