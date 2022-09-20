import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2212 ¼¾¼­
public class Main {
	
	static int[] arr;
	
	static int solution(int N, int K) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		Arrays.sort(arr);
		int tmp = arr[0];
		int sum = 0;
		for(int i = 1 ; i < N ; i ++) {
			int num = Math.abs(tmp - arr[i]);
			sum += num;
			pQ.offer(num);
			tmp = arr[i];
		}
		for(int i = 0 ; i < K - 1 ; i ++) {
			if(!pQ.isEmpty()) sum -= pQ.poll();
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(N ,K));
	}
}
