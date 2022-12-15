import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1572 중앙값
public class Main {

	static int[] tree;
	static int size = 2;
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum >= size / 2) {
			return nodeNum - size / 2;
		}
		if(num > tree[nodeNum * 2]) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else {
			return findNum(num, nodeNum * 2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		long answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> Q = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int i;
		while(true) {
			if(size >= 65537) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(i = 0 ; i < K ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			Q.offer(tmp);
			T.update(tmp, 1);
		}
		answer += T.findNum((K + 1) / 2, 1);
		for(; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			T.update(Q.poll(), -1);
			T.update(tmp, 1);
			Q.offer(tmp);
			answer += T.findNum((K + 1) / 2, 1);
		}
		System.out.println(answer);
	}
}
