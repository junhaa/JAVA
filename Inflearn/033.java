package codingTest_033;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public ArrayList<Integer> solution(int num1, int num2, int[] arr) {
		
		ArrayList<Integer> answer = new ArrayList<>(); // ArrayList가 시간줄일 수 있음 String으로 더하는 것 보다 짧게 걸림
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		
		
		for(int i = 0 ; i < num2; i ++) {
			map.put(arr[i],map.getOrDefault(arr[i],0)+1);
		}
		
	
		
		answer.add(map.size());
		
		
		int rt = num2 , lt = 0 ;
		
		
		for(int i = 0 ; rt < num1 ; i ++) {
			map.put(arr[lt], map.get(arr[lt])-1);
			
			if(map.get(arr[lt]) == 0 ) {
				map.remove(arr[lt]);
			}
						
			map.put(arr[rt],map.getOrDefault(arr[rt],0)+1);
					
			rt ++;
			lt ++;
				
			answer.add(map.size());
			
			
		}
		
		
		return answer;
	}
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		int[] arr = new int[num1];
		for(int i = 0 ; i < num1 ; i ++) {
			arr[i] = sc.nextInt();
		}
		
		for(int x : T.solution(num1, num2, arr)) System.out.print(x + " ");
		
	}
	
	
}
