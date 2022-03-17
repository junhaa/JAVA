package codingTest_057;

import java.util.Scanner;

public class Main {
	
	
	static int N,M,answer = Integer.MIN_VALUE;
	static int[] score;
	static int[] time;
	public void DFS(int L,int sumScore, int sumTime  ) {
		if(sumTime > M) return;
		else if(L == N) {
			if(sumScore > answer ) {
				answer = sumScore;
			}
		}
		else {
			sumScore += score[L];
			sumTime += time[L];
			DFS(L+1, sumScore, sumTime);
			sumScore -= score[L];
			sumTime -= time[L];
			DFS(L+1, sumScore, sumTime);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		score = new int[N];
		time = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			score[i] = sc.nextInt();
			time[i] = sc.nextInt();
		}
		
		T.DFS(0, 0, 0);
		System.out.println(answer);
		
	}
}
