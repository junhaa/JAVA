import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2517 달리기 
public class Main {
	static int[] tree, seq;
	static ArrayList<Integer> comp = new ArrayList<>();
	static int size = 2;
	
	static void update(int i) {
		i += size / 2;
		tree[i]++;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
 	}

	static int findSum(int L, int R , int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			if(size >= 500000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		
		int N = Integer.parseInt(br.readLine());
		seq = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			comp.add(tmp);
			seq[i] = tmp;
		}
		Collections.sort(comp);
		for(int i = 0 ; i < N ; i ++) {
			int num = Collections.binarySearch(comp, seq[i]) + 1; // 좌표압축한 실력점수 
			T.update(num - 1);
			sb.append(T.findSum(num, size / 2 - 1, 1, 0, size / 2 - 1) + 1 + "\n");
		}
		System.out.println(sb);
	}
}
