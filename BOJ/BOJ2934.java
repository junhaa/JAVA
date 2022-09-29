import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2934 LRH 식물
public class Main {
	static int[] arr;
	static int[] lazy;
	static int size = 2;
	
	static void propagation(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			else {
				arr[nodeNum] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R <nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] ++;
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static int findVal(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeL, nodeR, nodeNum);
		if(R <nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findVal(L, R, nodeNum * 2, nodeL, mid) + findVal(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int start, end;
		while(true) {
			if(size >= 100000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		lazy = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int answer = 0;
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			if(end - start > 1) {
				update(start + 1, end - 1, 1, 0, size / 2 - 1);
			}
			if(T.findVal(start, start, 1, 0, size / 2 - 1) != 0) {
				answer += arr[size / 2 + start];
				arr[size / 2 + start] = 0;
			}
			if(T.findVal(end, end, 1, 0, size / 2 - 1) != 0) {
				answer += arr[size / 2 + end];
				arr[size / 2 + end] = 0;
			}
			sb.append(answer + "\n");
		}  
		System.out.println(sb);
	}
}
