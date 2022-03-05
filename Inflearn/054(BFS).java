package codingTest_054_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	int answer = 0;
	int[] dis = {1, -1, 5};
	int[] ch;
	Queue<Integer> Q = new LinkedList<>();
	public int BFS(int s, int e) {
		ch = new int[10001];
		ch[s] = 1;
		Q.offer(s);
		int L=0;	
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int k = 0 ; k < len; k ++) {
				int cur = Q.poll();
				if(cur == e) return L;
				for(int i = 0 ; i < 3 ; i ++) {
					int nx = cur +dis[i];
					if( nx > 0 && nx < 10001 && ch[nx] == 0) {
						ch[nx] = 1;
						Q.offer(nx);
			}
			}
			}
			
			L++;
		}
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		int e = sc.nextInt();
		System.out.println(T.BFS(s, e));
		
	}
	
}
