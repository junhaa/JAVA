package codingTest_067;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time>{
	public int start,end;
	Time(int start, int end){
		this.start = start;
		this.end  = end;
	}
	@Override
	public int compareTo(Time o) {
		if(this.end == o.end) return this.start - o.start;
		else return this.end - o.end;
	}
	
}

public class Main {
	public int solution(ArrayList<Time> arr, int N) {
		int cnt = 0; 
		int endTime = Integer.MIN_VALUE;
		Collections.sort(arr);
		for(Time ob : arr) {
			if(ob.start >= endTime) {
				endTime = ob.end;
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Time> arr = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr.add(new Time(x,y));
		}
	
		System.out.println(T.solution(arr, N));
	}
}
