package codingTest_066;

import java.util.*;


class Applicant implements Comparable<Applicant>{
	public int h,w;
	Applicant(int h,int w){
		this.w = w;
		this.h = h; 
	}
	@Override
	public int compareTo(Applicant o) {
		return o.h - this.h;
	}
}

public class Main {
	public int solution(ArrayList<Applicant> arr, int N) {
		int cnt = 0;
		Collections.sort(arr);
		int max = Integer.MIN_VALUE;
		for(Applicant ob : arr) {
			if(ob.w > max) {
				max = ob.w;
				cnt++;
			}
		}
		return cnt;
		
	}
	
	public static void main(String[] args) {
		ArrayList<Applicant> arr = new ArrayList<>();
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 0 ; i < N ; i ++) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			arr.add(new Applicant(h,w));			
		}
		System.out.println(T.solution(arr, N));
	}
}
