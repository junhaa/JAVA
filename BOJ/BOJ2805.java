import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2805 나무 자르기
public class Main {
	static int[] tree;
	
	public static long solution(int[] tree, int N, int M, int max) {
		long answer = Long.MIN_VALUE;
		int lt = 0;
		int rt = max;
		boolean flag;
		while(lt <= rt) {
			int mid = (lt + rt) / 2;
			long sum = 0;
			for(int i = 0 ; i < N ; i ++) {
				if(tree[i] > mid) sum += tree[i] - mid;
			}
			if(sum > M) {
				lt = mid + 1;
				answer = Math.max(answer, mid);
			}
			else if( sum < M)	rt = mid - 1;
			else return mid;
		}
		return answer;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			max = Math.max(max, tmp); 
			tree[i] = tmp;
		}
		System.out.println(T.solution(tree, N, M, max));
	}
}
