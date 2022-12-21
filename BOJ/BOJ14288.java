import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14288 회사 문화 4
public class Main {

	static int[] startIdx, endIdx, lazy;
	static long[] uptree, downtree;
	static int size = 2, idx = -1;
	static ArrayList<Integer>[] list;
	
	static void DFS(int i) {
		startIdx[i] = ++idx;
		for(int next : list[i]) {
			DFS(next);
		}
		endIdx[i] = idx;
	}
	
	static void updateU(int i, int val) {
		i += size / 2;
		uptree[i] += val;
		while(i > 1) {
			i /= 2;
			uptree[i] = uptree[i * 2] + uptree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			downtree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	static void updateD(int L, int R, int nodeNum , int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] += val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		updateD(L, R, nodeNum * 2, nodeL, mid, val);
		updateD(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		downtree[nodeNum] = downtree[nodeNum * 2] + downtree[nodeNum * 2 + 1];
	}
	
	static long findNum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return downtree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid) + findNum(L, R, nodeNum * 2 + 1, mid + 1,	nodeR);
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return uptree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1,	nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int dir = 1;
		while(true) {
			if(size >= n) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		uptree = new long[size];
		downtree = new long[size];
		list = new ArrayList[n];
		for(int i = 0 ; i < n ; i ++) list[i] = new ArrayList<>();
		startIdx = new int[n];
		endIdx = new int[n];
		lazy = new int[size];
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i = 1; i < n ; i ++) {
			list[Integer.parseInt(st.nextToken()) - 1].add(i);
		}
		T.DFS(0);
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int menu = Integer.parseInt(st.nextToken());
			if(menu == 1) {
				if(dir % 2 == 1) {
					int tmp = Integer.parseInt(st.nextToken()) - 1;
					updateD(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
				}
				else {
					updateU(startIdx[Integer.parseInt(st.nextToken()) - 1], Integer.parseInt(st.nextToken()));
				}
			}
			else if(menu == 2) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findNum(startIdx[tmp], startIdx[tmp], 1, 0, size / 2 - 1) + T.findSum(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1) + "\n");
			}
			else {
				dir ++;
			}
		}
		System.out.println(sb);
	}
}
