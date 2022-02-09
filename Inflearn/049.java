	package codingTest_049;
	
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Scanner;
	
	public class Main {
	
		public ArrayList<Integer> solution(int num, int[] arr) {
	
			ArrayList<Integer> answer = new ArrayList<>();
	
			int[] tmp = arr.clone();
			Arrays.sort(arr);
			for (int i = 0; i < num; i++) {
				if (arr[i] != tmp[i]) {
					answer.add(i + 1);
				}
			}
			return answer;
	
		}
	
		public static void main(String[] args) {
	
			Main T = new Main();
			Scanner sc = new Scanner(System.in);
	
			int num = sc.nextInt();
			int arr[] = new int[num];
			for (int i = 0; i < num; i++) {
				arr[i] = sc.nextInt();
			}
			for (int x : T.solution(num, arr)) {
				System.out.print(x + " ");
			}
	
		}
	
	}
