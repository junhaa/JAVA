package codingTest_013;

import java.util.Scanner;

public class Main {

	
	public String result(int num, String str) {
		
		String answer = "";
	
		String[] arr = new String[num];
		int [] arrInt = new int[num];
		
		arr = str.split(" ");
		for(int i = 0; i < num ; i ++) {
			arrInt[i] = Integer.parseInt(arr[i]);
		}
		
		/*for(int x : arrInt) {
			System.out.println(x);
		}
		*/
		
		for(int i = 0; i < num ; i++) {
			if(i == 0) {
				answer +=arrInt[i];  
				answer +=" ";
			}
			else if (arrInt[i] > arrInt[i - 1]) {
				answer += arrInt[i];
				answer +=" ";
			}
		}
		
	return answer;
	}
	
	
	public static void main(String[] args) {
		
		Main T = new Main();;
		Scanner sc = new Scanner(System.in);
		
		String num = sc.nextLine();
		String str = sc.nextLine();
		
		int num1 = Integer.parseInt(num);
		
		System.out.println(T.result(num1, str));
		
		
		
		
	}
	
}
