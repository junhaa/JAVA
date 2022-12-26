import java.util.Scanner;

// BOJ #1041 
public class Main {
	public long solution(int N, int[] dice) {
		if(N == 1) {
			int max = Integer.MIN_VALUE;
			int sum = 0;
			for(int x : dice) {
				sum += x;
				if(x > max) max = x;
			}
			return sum - max;
		}
		else {
			int Min1 = Integer.MAX_VALUE;
			for(int i = 0 ; i < 6 ; i ++) {
				if(dice[i] < Min1) Min1 = dice[i];
			}
			int Min3 = Integer.MAX_VALUE, Min2 = Integer.MAX_VALUE;
			if(Min2 > dice[2] + dice[1]) Min2 = dice[2] + dice[1];
			if(Min2 > dice[2] + dice[4]) Min2 = dice[2] + dice[4];
			if(Min2 > dice[3] + dice[1]) Min2 = dice[3] + dice[1];
			if(Min2 > dice[3] + dice[4]) Min2 = dice[3] + dice[4];
			
			Min3 = Min2 + Math.min(dice[0], dice[5]);
			
			for(int i = 1 ; i < 5 ; i ++) {
				if(dice[0] + dice[i] < Min2) Min2 = dice[0] + dice[i];
			}
			for(int i = 1 ; i < 5 ; i ++) {
				if(dice[5] + dice[i] < Min2) Min2 = dice[5] + dice[i];
			}
			return 4 * Min3 + 4 * (N - 1) * Min2 + 4 * (N - 2) * Min2 + (long)4 * (N - 2) * (N - 1) * Min1 + (long)1 * (N - 2) * (N - 2) * Min1;
		}
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dice = new int[6]; // A = 0 , B = 1 ...
		for(int i = 0 ; i < 6 ; i ++) {
			dice[i] = sc.nextInt();
		}
		System.out.println(T.solution(N, dice));
	}
}
