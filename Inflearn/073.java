package codingTest_073;

import java.util.Scanner;

public class Main {
	static int answer = 0 , N;
	public void DFS(int L) {
		if(L == N + 1) {
			answer++;
		}
		else if(L > N) return;
		else {
			for(int i = 1 ; i <= 2 ; i ++) {
				DFS(L+i);
			}
		}
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T.DFS(0);
		System.out.println(answer);                                            
	}
}
