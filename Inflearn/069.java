package codingTest_069;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
class Lecture implements Comparable<Lecture>{
	public int pay, day;
	Lecture(int pay, int day) {
		this.pay = pay;
		this.day = day;
	}
	@Override
	public int compareTo(Lecture o) {
		return o.day - this.day;
	}
}

public class Main {
	static int n, max = Integer.MIN_VALUE;
	public int solution(ArrayList<Lecture> arr, int D) {
		int answer = 0;
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		int j = 0 ;
		for(int i = max; i >= 1; i --) {
			for(; j< n ; j++) {
				if(arr.get(j).day < i )break;
				pQ.offer(arr.get(j).pay);
			}
			if(!pQ.isEmpty()) answer += pQ.poll();
		}
		return answer;
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		ArrayList<Lecture> arr = new ArrayList<>();
		n = sc.nextInt();
		int num = Integer.MIN_VALUE;
		for(int i = 0 ; i < n ; i ++) {
			int M = sc.nextInt();
			int D = sc.nextInt();
			if(D > max) max = D;
			arr.add(new Lecture(M,D));
		}
		Collections.sort(arr);
		System.out.println(T.solution(arr, num));
	}
	
}
