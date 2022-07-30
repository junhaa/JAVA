import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #12852 1로 만들기 2
public class Main {
	public static StringBuilder solution(int N) {
		int[] ch = new int[N + 1];
		int[] from = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		int L = 0;
		boolean flag = false;
		Q.offer(N);
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp == 1) { 
					flag = true;
					break;
				}
				if(tmp % 3 == 0 && ch[tmp / 3] == 0) {
					Q.offer(tmp / 3);
					ch[tmp / 3] = 1;
					from[tmp / 3] = tmp;
				}
				if(tmp % 2 == 0 && ch[tmp / 2] == 0) {
					Q.offer(tmp / 2);
					ch[tmp / 2] = 1;
					from[tmp / 2] = tmp;
				}
				if(ch[tmp - 1] == 0) {
					Q.offer(tmp - 1);
					ch[tmp - 1] = 1;
					from[tmp - 1] = tmp;
				}
			}
			if(flag) break;
			L ++;
		}
		sb.append(L).append('\n');
		int num = 1;
		ArrayList<Integer> list = new ArrayList<>();
		while(num != N) {
			list.add(from[num]);
			num = from[num];
		}
		Collections.sort(list, Collections.reverseOrder());
		for(int x : list) {
			sb.append(x + " ");
		}
		sb.append(1);
		return sb;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(T.solution(N));
	}
}
