import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12899 데이터 구조 
public class Main {
	static int[] tree;
	static int size = 2;
	
	
	static void update(int val, boolean isAdd) {
		if(isAdd) {
			tree[size / 2 + val] ++;
		}
		else tree[size / 2 + val] --;
		int i = val + size / 2;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int p, int nodeNum) {
	
		while(true) {
			if(nodeNum >= size / 2) {
				update(nodeNum - size / 2, false);
				return nodeNum - size / 2;
			}
			if(p > tree[nodeNum * 2]) {
			 p -= tree[nodeNum * 2];
			 nodeNum = nodeNum * 2 + 1;
			}
			else {
				nodeNum *= 2;
			}
		}
		/*
		if(nodeNum >= size / 2) {
				return nodeNum - size / 2;
			}
			if(p > tree[nodeNum * 2]) {
				p -= tree[nodeNum * 2];
				findNum(p, nodeNum * 2 + 1);
			}
			else findNum(p, nodeNum * 2);
			return 0;
			*/
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= 2000000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				T.update(Integer.parseInt(st.nextToken()), true);
			}
			else {
				sb.append(T.findNum(Integer.parseInt(st.nextToken()), 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
