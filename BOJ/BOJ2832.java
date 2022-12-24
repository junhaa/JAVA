import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2832 정렬
public class Main {

	static int tree[], size = 2;
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= 100001) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int last = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();
		list.add(last);

		int answer = 0;
			
		for(int i = 1 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp > last) {
				last = tmp;
				if(list.size() != 1) {
					answer ++;
					Collections.sort(list);
				}
				for(int num : list) {
					answer += T.findSum(num + 1, 100000, 1, 0, size / 2 - 1);
					T.update(num, 1);
					
				}
				list.clear();
				list.add(tmp);
			}
			else {
				list.add(tmp);
				last = tmp;
			}
		}
		if(list.size() > 1) {
			answer ++;
			Collections.sort(list);
		}
		for(int num : list) {
			answer += T.findSum(num + 1, 100000, 1, 0, size / 2 - 1);
		}
		System.out.println(answer);
	}
}
