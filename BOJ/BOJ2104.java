import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// BOJ #2104 부분배열 고르기
class Node{
	long sum;
	int min;
	public Node(long sum, int min) {
		this.sum = sum;
		this.min = min;
	}
}
public class Main {
	
	static Node[] tree;
	static int size = 2;
	static long answer = Integer.MIN_VALUE;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = new Node(tree[i * 2].sum + tree[i * 2 + 1].sum, Math.min(tree[i * 2].min, tree[i * 2 + 1].min));
		}
	}
	
	static long divideConquer(int s, int e) {
		if(s == e) {
			return tree[size / 2 + s].sum * tree[size / 2 + s].sum;
		}
		long cur = 0; // 현재 합
		long max = Integer.MIN_VALUE;
		int minCur ; // 현재 최소값
		int lt = (s + e) / 2;
		int rt = (s + e) / 2; 
		minCur = tree[size / 2 + lt].min;
		cur = tree[size / 2 + lt].sum;
		while(true) {
			if(lt == s && rt == e) break;
			else if(lt > s && rt < e) {
				if(tree[size / 2 + (lt - 1)].min > tree[size / 2 + (rt + 1)].min) {
					minCur = Math.min(tree[size / 2 + (lt - 1)].min, minCur);
					cur += tree[size / 2 + (lt - 1)].sum;
					max = Math.max(max, cur * minCur);
					lt --;
				}
				else {
					minCur = Math.min(tree[size / 2 + (rt + 1)].min, minCur);
					cur += tree[size / 2 + (rt + 1)].sum;
					max = Math.max(max, cur * minCur);
					rt ++;
				}
			}
			else if(rt < e) {
				minCur = Math.min(tree[size / 2 + (rt + 1)].min, minCur);
				cur += tree[size / 2 + (rt + 1)].sum;
				max = Math.max(max, cur * minCur);
				rt ++;
			}
			else {
				minCur = Math.min(tree[size / 2 + (lt - 1)].min, minCur);
				cur += tree[size / 2 + (lt - 1)].sum;
				max = Math.max(max, cur * minCur);
				lt --;
			}
		}
		int mid = (s + e) / 2;
		max = Math.max(divideConquer(s, mid), max);
		max = Math.max(divideConquer(mid + 1, e), max);
		answer = Math.max(answer, max);
		return max;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
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
		tree = new Node[size];
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				int tmp = Integer.parseInt(st.nextToken());
				tree[size / 2 + i] = new Node((long)tmp, tmp);
			}
			else {
				tree[size / 2 + i] = new Node(0, Integer.MAX_VALUE);
			}
		}
		T.construct();
		if(N == 1) System.out.println(T.divideConquer(0, N - 1));
		else {
			T.divideConquer(0, N - 1);
			System.out.println(answer);
		}
	}
}
