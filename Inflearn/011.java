package codingTest_011;

import java.util.Scanner;

public class Main {

	public String result(String str){
		
		String answer = "";
		int len = str.length();
		
		char[] strInput = str.toCharArray();
		
		char tmp = 'c';
		int  num = 0;
		for(int i = 0; i < len ; i++){
			if(i == 0) {
			tmp = strInput[0];
			num = 1;
			}
			
			else if(strInput[i] == strInput[i-1]) {
			num ++;
			}
			else if(strInput[i] != strInput[i-1]) {
				answer += tmp;
				if(num != 1) {
					answer += num;
				}
				num = 1 ;
				tmp = strInput[i];
				
				
			}
			if(i == len - 1) {
				answer += tmp;
				if(num != 1) {
					answer += num;
				}
			}
			
			
		}
		
		return answer;
	}
	
	
	public static void main(String[] args) {
	
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		System.out.println(T.result(str));
		
	}
}
