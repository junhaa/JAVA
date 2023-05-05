import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #9935 문자열 폭발
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> stack = new Stack();
		String input = br.readLine();
		String cur = "";
		char[] arr = br.readLine().toCharArray();
		StringBuilder answer = new StringBuilder();
		for(int i = 0 ; i < input.length() ; i ++) {
			answer.append(input.charAt(i));
			if(answer.length() >= arr.length) {
				boolean flag = true;
				for(int j = 0 ; j < arr.length ; j ++) {
					if(answer.charAt(answer.length() - arr.length + j) != arr[j]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					answer.delete(answer.length() - arr.length, answer.length());
				}
			}
		}
//		for(int i = 0 ; i < input.length() ; i ++) {
//			char curCh = input.charAt(i);
//			if(curCh != arr[cur.length()]) {
//				if(curCh == arr[0]) {
//					stack.push(cur);
//					cur = "";
//					cur += curCh;
//				}
//				else {
//					Stack<String> tmpStack = new Stack<>();
//					while(!stack.isEmpty()) {
//						tmpStack.push(stack.pop());
//					}
//					while(!tmpStack.isEmpty()) {
//						answer += tmpStack.pop();
//					}
//					answer += cur;
//					cur = "";
//					answer += curCh;
//				}
//			}
//			else {
//				cur += curCh;
//				if(cur.length() == arr.length) {
//					cur = "";
//					if(!stack.isEmpty()) cur = stack.pop();
//				}
//			}
//		}
//		stack.push(cur);
//		Stack<String> tmpStack = new Stack<>();
//		while(!stack.isEmpty()) {
//			tmpStack.push(stack.pop());
//		}
//		while(!tmpStack.isEmpty()){
//			answer += tmpStack.pop();
//		}
//		if(answer == "") {
//			System.out.println("FRULA");
//			return;
//		}
//		System.out.println(answer);
		if(answer.isEmpty()) {
			System.out.println(answer);
			return;
		}
		System.out.println(answer);
	}
}
