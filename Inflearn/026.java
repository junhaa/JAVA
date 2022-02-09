package codingTest_026;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public ArrayList<Integer> result(int num1, int[] arr1, int num2 , int arr2[]){
		ArrayList<Integer> answer = new ArrayList<>();
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int p1 = 0, p2 = 0;
		while(p1 < num1 && p2 < num2) {
			if(arr1[p1] < arr2[p2]) {
				p1++;
			}
			else if(arr1[p1] > arr2[p2] ) {
				p2 ++;
			}
			else {
				answer.add(arr1[p1]);
				p1++;
				p2++;
			}
		}
		
		
		
		/*StringBuffer str = new StringBuffer("");
		
		for(int i = 0; i < num1; i ++) {
			for(int k = 0; k < num2 ; k++) {
				if(arr1[i] == arr2[k]) {
					str.append(arr1[i]);
					str.append(" ");
				}
			}
		}
		
		
		String[] strArr = str.toString().split(" ");
		Arrays.sort(strArr);
		
		
		for(String x : strArr) {
			answer.add(Integer.parseInt(x));
					
		}
		*/
		
	return answer;	
		
	}
	
	
	
	public static void main(String[] args) {
	
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int[] arr1 = new int[num1];
		for(int i = 0; i < num1 ; i ++) {
			arr1[i] = sc.nextInt();
		}
		
		int num2 = sc.nextInt();
		int[] arr2 = new int[num2];
		for(int i = 0; i < num2 ; i ++) {
			arr2[i] = sc.nextInt();
		}
			
		
		for(int x : T.result(num1, arr1, num2, arr2)) {
			System.out.print(x + " ");
		}
		
		
		
		
	}
	
}
