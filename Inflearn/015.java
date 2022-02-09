package codingTest_015;

import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String numStr = sc.nextLine();
		int num = Integer.parseInt(numStr);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		
		String arr1[] = str1.split(" ");
		String arr2[] = str2.split(" ");
		int arrInt1[] = new int[num];
		int arrInt2[] = new int[num];
		char answer[] = new char[num];
		for(int i = 0 ; i < num ; i ++) {
			arrInt1[i] = Integer.parseInt(arr1[i]);
		}
		for(int i = 0 ; i < num ; i ++) {
			arrInt2[i] = Integer.parseInt(arr2[i]);
		}
		
		for(int i = 0 ; i < num ; i ++) {
			if(arrInt1[i] == 1 && arrInt2[i] == 3) {
				answer[i] = 'A';
			}
			else if(arrInt2[i] == 1 && arrInt1[i] == 3) {
				answer[i] = 'B';
				
			}
			else if(arrInt1[i] == arrInt2[i]) {
				answer[i] = 'D';
				
			}
			else if(arrInt1[i] > arrInt2[i]) {
				answer[i] = 'A';
			}
			else if(arrInt1[i] < arrInt2[i]) {
				answer[i] = 'B';
			}
			
			
		}
		for (int i = 0 ; i < num ; i ++) {
			System.out.println(answer[i]);
		}
		
		
	}
	
}
