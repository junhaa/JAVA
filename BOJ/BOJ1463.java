import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #1463 1로 만들기
class Node{
	int num;
	int cnt;
	public Node(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}
public class Main {

	static boolean ch[];
	
	static int solution(int N) {
		Queue<Integer> Q = new LinkedList<>();
		ch = new boolean[N + 1];
		Q.offer(N);
		int cnt = 0;
		while(true) {
			int length = Q.size();
			for(int i = 0 ; i < length; i ++) {
				int tmp = Q.poll();
				if(tmp == 1) return cnt;
				if(tmp % 3 == 0 && !ch[tmp / 3]) {
					Q.offer(tmp / 3);
					ch[tmp / 3] = true;
				}
				if(tmp % 2 == 0 && !ch[tmp / 2]) {
					Q.offer(tmp / 2);
					ch[tmp / 2] = true;
				}
				if(!ch[tmp - 1]) {
					Q.offer(tmp - 1);
					ch[tmp - 1] = true;
				}
			}
			cnt ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(T.solution(N));
	}
}
