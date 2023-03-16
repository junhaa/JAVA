import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// BOJ #20301 반전 요세푸스
class DEQ{ // O(KN)
	
	private static StringBuilder solution(int N, int K, int M) {
		ArrayDeque<Integer> dQ = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		boolean rev = false;
		for(int i = 1 ; i <= N ; i ++) {
			dQ.offer(i);
		}
		int cntk = 0;
		int cntm = 0;
		while(!dQ.isEmpty()) {
			int cur;
			if(rev) cur = dQ.pollLast();
			else cur = dQ.pollFirst();
			if(++cntk % K == 0) {
				sb.append(cur + "\n");
				if(++cntm % M == 0) rev = !rev;
			}
			else {
				if(rev) dQ.offerFirst(cur);
				else dQ.offerLast(cur);
			}
		}
		return sb;
	}
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		return solution(N, K, M);
	}
}

class SEG{ // O(logN)
	
	private static int[] tree;
	private static int size = 2;
	
	private static StringBuilder solution(int N, int K, int M) {
		StringBuilder sb = new StringBuilder();
		boolean rev = false;
		int cntm = 0;
		if(++cntm % M == 0) rev = !rev;
		int cur = K;
		sb.append(K + "\n"); //
		update(K - 1 + size / 2, 0);
		while(tree[1] > 0) {
			if(!rev)cur += K - 1;
			else cur += tree[1] - (K % tree[1]);
			if(cur % tree[1] == 0) {
				cur = tree[1];
			}
			else if(cur > tree[1]) {
				cur %= tree[1];
			}
			if(tree[1] == 1) cur = 1;
			sb.append(findNum(1, cur) + "\n");  //
			if(++cntm % M == 0) {
				rev = !rev;
			}
		}
		return sb;
	}
	
	private static void construct() {
		for(int i = size / 2 - 1;  i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	private static void update(int i, int val) {
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	private static int findNum(int nodeNum, int num) {
		if(nodeNum >= size / 2) { 
			update(nodeNum, 0);
			return nodeNum - size / 2 + 1; // 0-based > 1-based
		}
		if(tree[nodeNum * 2] >= num) return findNum(nodeNum * 2, num);
		else return findNum(nodeNum * 2 + 1, num - tree[nodeNum * 2]);
	}
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size]; // 0-based
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] ++;
		}
		construct();
		return solution(N, K, M);
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		//DEQ deq = new DEQ();
		//System.out.println(deq.init());
		SEG seg = new SEG();
		System.out.println(seg.init());
	}
}
