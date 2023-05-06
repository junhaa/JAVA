import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #5817 고통받는 난쟁이들 
class Node{
	int min, max;
	public Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
}
public class Main {

	static Node[] tree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			int tmpMin = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
			int tmpMax = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);
			tree[i] = new Node(tmpMin, tmpMax); 
		}
	}
	
	static int getMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].min;
		int mid = (nodeL + nodeR) / 2;
		return Math.min(getMin(L, R, nodeNum * 2, nodeL, mid), getMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	static int getMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].max;
		int mid = (nodeL + nodeR) / 2;
		return Math.max(getMax(L, R, nodeNum * 2, nodeL, mid), getMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i].min = val;
		tree[i].max = val;
		while(i > 1) {
			i /= 2;
			tree[i].min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
			tree[i].max = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new Node[size];
		int pos[] = new int[N];
		int idx[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken()) - 1;
			pos[tmp] = i;
			idx[i] = tmp;
		}
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				int tmp = pos[i];
				tree[size / 2 + i] = new Node(tmp, tmp);
			}
			else tree[size / 2 + i] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		T.construct();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				T.update(idx[start], end);
				T.update(idx[end], start);
				int tmp = idx[start];
				idx[start] = idx[end];
				idx[end] = tmp;
			}
			else {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int dif = T.getMax(start, end, 1, 0, size / 2 - 1) - T.getMin(start, end, 1, 0, size / 2 - 1);
				int difidx = end - start;
 				if(dif == difidx) {
 					sb.append("YES\n");
 				}
 				else sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}
}
