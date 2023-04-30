import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #12016 라운드 로빈 스케줄러
class RR implements Comparable<RR>{
	int time, seq;
	public RR(int time, int seq) {
		this.time = time;
		this.seq = seq;
	}
	@Override
	public int compareTo(RR o) {
		if(this.time == o.time) return this.seq - o.seq;
		return this.time - o.time;
		
	}
}
public class Main {

	static int[] tree;
	static long[] answer;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2 , nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int i) {
		i += size / 2;
		tree[i] = 0;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<RR> pQ = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		answer = new long[N];
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = 1;
		}
		T.construct();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(new RR(Integer.parseInt(st.nextToken()), i));
		}
		int cur = 1;
		long sumTime = 0;
		while(!pQ.isEmpty()) {
			while(!pQ.isEmpty() && pQ.peek().time == cur) {
				RR tmp = pQ.poll();
				answer[tmp.seq] = sumTime + getSum(0, tmp.seq, 1, 0, size / 2 - 1);
				T.update(tmp.seq);
				sumTime ++;
			}
			if(!pQ.isEmpty()) {
				sumTime += (long)tree[1] * (pQ.peek().time - cur);
				cur = pQ.peek().time;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++){
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
