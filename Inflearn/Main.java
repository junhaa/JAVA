package codingTest_068;

import java.util.HashMap;
import java.util.Scanner;


public class Main {

	public int solution(HashMap<Integer, Integer> map){
		
		int answer = Integer.MIN_VALUE;
		for(int o : map.keySet()) {
			if(map.get(o) > answer) {
				answer = map.get(o);
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			for(int k = start; k < end; k++) {
				map.put(k, map.getOrDefault(k, 0) + 1);
			}
		}
		System.out.println(T.solution(map));
	}
}
