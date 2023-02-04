import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #13537 수열과 쿼리 1
public class Main {

	static ArrayList<Integer>[] tree;
	static int size = 2;
	
	public static int upperBound(ArrayList<Integer> arr, int start, int end, int key) {
		int low = start;
		int high = end;

		while (low <= high) {
			int mid = (low + high) >> 1;
			int midVal = arr.get(mid);
			if(midVal > key)	high = mid - 1;
			else	low = mid + 1;
		}
		return high;
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			ArrayList<Integer> c = tree[i], lt = tree[i * 2], rt = tree[i * 2 + 1];
			for(int j = 0, p = 0, q = 0 ; j < lt.size() + rt.size() ; j ++) {
				if(q == rt.size() || (p < lt.size() && lt.get(p) < rt.get(q))) c.add(lt.get(p++));
				else c.add(rt.get(q++));
			}
		}
	}
	
	static int greater(int L, int R, int nodeNum, int nodeL, int nodeR, int k) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) {
			int idx = upperBound(tree[nodeNum], 0, tree[nodeNum].size() - 1, k) + 1;
			return tree[nodeNum].size() - idx;
		}
		int mid = (nodeL + nodeR) / 2;
		return greater(L, R, nodeNum * 2, nodeL, mid, k) + greater(L, R, nodeNum * 2 + 1, mid + 1, nodeR, k);
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
		tree = new ArrayList[size];
		for(int i = 1 ; i < size ; i ++) {
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i].add(Integer.parseInt(st.nextToken()));
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			sb.append(T.greater(a - 1, b - 1, 1, 0, size / 2 - 1, c) + "\n");
		}
		System.out.println(sb);
	}
}
