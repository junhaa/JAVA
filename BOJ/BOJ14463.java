import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #14463 소가 길을 건너간 이유 9
class Cow implements Comparable<Cow>{
	int start, end;
	public Cow(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Cow o) {
		return this.start - o.start;
	}
}
public class Main {
	static Cow[] arr;
	static int size = 2, tree[];
	
	static void update(int i) {
		i += size / 2;
		tree[i] ++;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new Cow[N];
		while(true) {
			if(size >= N * 2) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < 2 * N ; i ++) {
			int cur = Integer.parseInt(br.readLine()) - 1;
			if(arr[cur] == null) {
				arr[cur] = new Cow(i, -1);
			}
			else {
				arr[cur].end = i;
			}
		}
		Arrays.sort(arr);
		long answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			Cow tmp = arr[i];
			answer += T.getSum(tmp.start, tmp.end, 1, 0, size / 2 - 1);
			T.update(tmp.end);
		}
		System.out.println(answer);
	}
}
