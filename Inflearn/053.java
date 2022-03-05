package codingTest_053;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public int count(int mid, int[] xi, int N) {
		
		int cnt = 1 ; 
		int lastPoint = xi[0];
		
		for(int i = 1 ; i < N ; i++) {
			if(xi[i] - lastPoint >= mid) {
				cnt ++;
				lastPoint = xi[i];
			}
			
		}
		return cnt;
	}
	
	public int solution(int N, int C, int[] xi) {

		Arrays.sort(xi);
		int rt = Arrays.stream(xi).max().getAsInt();
		int lt = Arrays.stream(xi).min().getAsInt();
		int answer = 0;
		
		while(lt <=  rt) {
			int mid = (rt + lt) / 2;
			
			if(count(mid,xi,N) < C) {
				rt = mid - 1;
			}
			else if(count(mid,xi,N) >= C) {
				lt = mid + 1;
				answer = mid;
			}
		}
		return answer;
	}
	public static void main(String[] args) {
			
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		int[] xi = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			xi[i] = sc.nextInt();
		}
		
		System.out.println(T.solution(N, C, xi));
	}
}
