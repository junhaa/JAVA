import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1697 ¼û¹Ù²ÀÁú

public class Main {
	public static int solution(int N , int K) {
		int[] ch = new int [200001];
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(N);
		int answer = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp == K) return answer;
				if(ch[tmp + 1] == 0 && tmp + 1 < 200000) {
					Q.offer(tmp + 1);
					ch[tmp + 1] = 1;
				}
				if(tmp > 0 && tmp * 2 < 200000 && ch[tmp * 2] == 0) {
					Q.offer(tmp * 2);
					ch[tmp * 2] = 1;
				}
				if(tmp > 0 && ch[tmp - 1] == 0) {
					Q.offer(tmp - 1);
					if(tmp <= 200000) ch[tmp - 1] = 1;
				}
			}
			answer ++;
		}
		return -1; 
	}

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(N, K));
	}
}
