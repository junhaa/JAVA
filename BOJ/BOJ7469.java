import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #7469 K번째 수
public class Main {

	static ArrayList<Integer>[] tree;
	static int size = 2;
	static ArrayList<Integer> result = new ArrayList<>();
	
	static int findNum(int L, int R, int nodeNum, int nodeL, int nodeR, int num) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return upperBound(tree[nodeNum], num);
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid, num) + findNum(L, R, nodeNum * 2 + 1, mid + 1, nodeR, num);
	}
	
	static int upperBound(ArrayList<Integer> node, int num) {
		int length = node.size();
		int lt = 0, rt = length - 1, mid = 0;
		while(lt < rt) {
			if(node.get(mid) <= num) lt = mid + 1;
			else rt = mid;
			mid = (lt + rt) / 2;
			if(rt == mid) {
				if(node.get(mid) <= num) return length;
				else return rt;
			}
		}
		if(node.get(lt) > num) return 0;
		return lt + 1;
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			ArrayList<Integer> c = tree[i], lt = tree[i * 2], rt = tree[i * 2 + 1];
			for(int j = 0, p = 0, q = 0 ; j < lt.size() + rt.size() ; j ++) {
				if(p >= lt.size() || q < rt.size() && rt.get(q) < lt.get(p)) {
					c.add(rt.get(q++));
				}
				else c.add(lt.get(p++));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new ArrayList[size];
		for(int i = 1; i < size ; i ++) {
			tree[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[i + size / 2].add(Integer.parseInt(st.nextToken()));
		}
		T.construct();
		
		for(int l = 0 ; l < M ; l ++) {
			int ltV = (int)-1e9, rtV = (int)1e9;
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());
			while(ltV <= rtV) {
				int mid = (ltV + rtV) / 2;
				int res = T.findNum(i, j, 1, 0, size / 2 - 1, mid);
				if(res < k) ltV = mid + 1;
				else rtV = mid - 1;
			}
			sb.append(ltV + "\n");
		}
		System.out.println(sb);
	}
}
