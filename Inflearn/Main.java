package codingTest_058;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public int BFS(int M, int[] type) {
		
		Queue<Integer> Q = new LinkedList<>();
		int L = 1;
		for(int i = 0; i < type.length; i ++) {
			Q.offer(type[i]);
		}
		
		while(!Q.isEmpty()) {
			int len = Q.size();
			
			for(int i = 0 ; i < len; i++) {
				int cur = Q.poll();
				if(cur == M) return L;
				else {
					for(int k = 0 ; k < type.length; k ++) {
						Q.offer(cur + type[k]);
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
		
		int N = sc.nextInt();
		int[] type = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			type[i] = sc.nextInt();
		}
		int M = sc.nextInt();
		System.out.println(T.BFS(M, type));
		
	}
}
