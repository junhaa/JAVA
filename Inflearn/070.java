package codingTest_070;

import java.util.Scanner;

public class Main {
	static int[] unf;
	public static int Find(int x) {
		if(x == unf[x]) return x;
		else return Find(unf[x]);
	}
	public static void Union(int a , int b) {
		int fa = Find(a);
		int fb = Find(b);
		if(fa != fb) unf[fa] = unf[fb];
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		unf = new int[N+1];
		for(int i = 1 ; i <= N ; i ++) {
			unf[i] = i;
		}
		for(int k = 0 ; k < M ; k ++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			T.Union(a, b);
		}
		int answer1 = sc.nextInt();
		int answer2 = sc.nextInt();
		System.out.print(Find(answer1)+ " ");
		System.out.println(Find(answer2));
		if(Find(answer1) == Find(answer2)) System.out.println("YES");
		else System.out.println("NO");
	}
}
