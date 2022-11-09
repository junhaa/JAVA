import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1849 순열
public class Main {
	
	static int[] arr, answer, tree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update(int idx) {
		idx += size / 2;
		tree[idx] = 0;
		while(idx > 1) {
			idx /= 2;
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1]; 
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum * 2 >= size / 2) {
			if(num == 0) {
				if(tree[nodeNum * 2] != 0) return nodeNum * 2 - size / 2;
				else return nodeNum * 2 + 1 - size / 2;
			}
			else return nodeNum * 2 + 1 - size / 2;
		}
		if(num >= tree[nodeNum * 2]) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else return findNum(num, nodeNum * 2);
		/*
		 * if(nodeNum >= size / 2) { return nodeNum - size / 2; } if(num == 0) { return
		 * findNum(num, nodeNum * 2); } if(tree[nodeNum * 2] <= num) { return
		 * findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1); } else { return
		 * findNum(num, nodeNum * 2); }
		 */
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		answer = new int[N];
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
		for(int i = 1 ; i <= N ; i ++) {
			int num = Integer.parseInt(br.readLine());
			int index = findNum(num, 1);
			answer[index] = i;
			T.update(index);
		}
		for(int i = 0 ; i < N ; i ++) {
			sb.append(answer[i] + "\n"); 
		}
		System.out.println(sb);
	}
}
