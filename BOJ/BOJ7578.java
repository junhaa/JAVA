import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #7578 공장
public class Main {

	static long[] tree;
	static int[] arr1;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}

	static void update(int idx) {
		idx += size / 2;
		tree[idx] ++;
		while(idx > 1) {
			idx /= 2;
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		HashMap<Integer, Integer> map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = 0 ;
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		arr1 = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			arr1[i] = tmp;
			map.put(tmp, i);
		}
		T.construct();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int idx = map.get(Integer.parseInt(st.nextToken()));
			answer += T.findSum(idx + 1, N - 1, 1, 0, size / 2 - 1);
			T.update(idx);
		}
		System.out.println(answer);
	}
}
