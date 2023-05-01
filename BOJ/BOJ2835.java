import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2835 인기도 조사 
public class Main {

	static int size = 2;
	static int[] lazy;
	static long[] tree;

	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	
	static long getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] ++;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeR + nodeL) / 2;
		update(L, R, nodeNum * 2, nodeL, mid);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			if(size >= 172800) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new int[size];
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st, startst, endst;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			startst = new StringTokenizer(st.nextToken(), ":");
			st.nextToken();
			endst = new StringTokenizer(st.nextToken(), ":");
			int start = 0, end = 0;
			start += Integer.parseInt(startst.nextToken()) * 3600;
			start += Integer.parseInt(startst.nextToken()) * 60;
			start += Integer.parseInt(startst.nextToken());
			end += Integer.parseInt(endst.nextToken()) * 3600;
			end += Integer.parseInt(endst.nextToken()) * 60;
			end += Integer.parseInt(endst.nextToken());
			if(end < start) {
				update(start, 86399, 1, 0, size / 2 - 1);
				update(0, end, 1, 0, size / 2 - 1);
			}
			else {
				update(start, end, 1, 0, size / 2 - 1);
			}
		}
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			startst = new StringTokenizer(st.nextToken(), ":");
			st.nextToken();
			endst = new StringTokenizer(st.nextToken(), ":");
			int start = 0, end = 0;
			start += Integer.parseInt(startst.nextToken()) * 3600;
			start += Integer.parseInt(startst.nextToken()) * 60;
			start += Integer.parseInt(startst.nextToken());
			end += Integer.parseInt(endst.nextToken()) * 3600;
			end += Integer.parseInt(endst.nextToken()) * 60;
			end += Integer.parseInt(endst.nextToken());
			double sum = 0;
			int range = 0;
			if(end < start) {
				range = end + 86401 - start;
				sum += getSum(start, 86399, 1, 0, size / 2 - 1);
				sum += getSum(0, end, 1, 0, size / 2 - 1);
			}
			else {
				range = end - start + 1;
				sum += getSum(start, end, 1, 0, size / 2 - 1);
			}
			sb.append(sum / (range) + "\n");
		}	
		System.out.println(sb);
	}
}
