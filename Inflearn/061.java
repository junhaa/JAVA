package codingTest_061;

import java.util.Scanner;

public class Main {
	static int answer = 0;
	static int map[][] ;
	public void DFS(int i, int k) {
		if(map[i][k] == 1) return;
		map[i][k] = 1;
		if(i == 6 && k == 6) {
			answer ++;
			map[i][k] = 0;
			return;
		}
		else {
			if(i != 0) DFS(i-1,k);
			if(k != 6) DFS(i,k+1);
			if(i != 6) DFS(i+1,k);
			if(k != 0) DFS(i,k-1);
			map[i][k] = 0;
			return;
		}
	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		map = new int[7][7];
		for(int i = 0 ; i < 7 ; i++) {
			for(int k = 0 ; k< 7 ; k++) {
				map[i][k] = sc.nextInt();
			}
		}
		T.DFS(0, 0);
		System.out.println(answer);
	}
	
}
