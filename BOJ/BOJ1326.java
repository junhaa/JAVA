import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1326 폴짝폴짝
public class Main {
	static int[] arr;
	public static int solution(int N, int a, int b) {
		int[] ch = new int[N + 1];
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(a);
		ch[a] = 1;
		int answer = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length; i ++) {
				int tmp = Q.poll();
				if(tmp == b) return answer;
				for(int k = arr[tmp] ; tmp + k <= N ; k += arr[tmp]) { // 양의 배수 
					if(ch[tmp + k] == 0) {
						ch[tmp + k] = 1;
						Q.offer(tmp + k);
					}
				}
				
				for(int k = -arr[tmp] ; tmp + k >= 1 ; k -= arr[tmp]) { // 음의 배수
					if(ch[tmp + k] == 0) {
						ch[tmp + k] = 1;
						Q.offer(tmp + k);
					}
				}
				
			}
			answer++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(N, a, b));
	}
}
