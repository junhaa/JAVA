import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1777 순열복원 
public class Main {
	
	static int[] tree, arr, answer;
	static int size = 2;

	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	
	static void update(int i) {
		i += size / 2;
		tree[i] = 0;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum * 2 >= size / 2) {
			if(num == 0) {
				if(tree[nodeNum * 2] == 0) {
					return  nodeNum * 2 + 1 - size / 2;
				}
				else return nodeNum * 2 - size / 2;
			}
			return nodeNum * 2 + 1 - size / 2;
				
		}
		if(tree[nodeNum * 2] <= num) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else return findNum(num, nodeNum * 2);
	}
	
	public static void main(String[] args) throws IOException{
		Main T =  new Main();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		arr = new int[N];
		answer = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = 1;
		}
		T.construct();
		for(int i = N ; i > 0 ; i --) {
			int idx = findNum(arr[i - 1], 1);
			T.update(idx);
			 answer[idx] = i;
		}
		for(int i = N - 1 ; i >= 0 ; i --) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb);
	}
}
