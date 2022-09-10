import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #3425 ∞ÌΩ∫≈√
public class Main {
	static ArrayList<String> list;
	static Stack<Integer> stack = new Stack<>();
	
	static String solution() {
		for(int i = 0 ; i < list.size() ; i ++) {
			int num = 0;
			String input = "";
			if(list.get(i).length() > 3) { // NUM
				StringTokenizer st = new StringTokenizer(list.get(i));
				input = st.nextToken();
				num = Integer.parseInt(st.nextToken());
			}
			else input = list.get(i);
			switch (input) {
			case "NUM": {
				stack.push(num);
				break;
			}
			case "POP" : {
				if(stack.isEmpty()) return "ERROR";
				stack.pop();
				break;
			}
			case "INV" : {
				if(stack.isEmpty()) return "ERROR";
				stack.push(-1 * stack.pop());
				break;
			}
			case "DUP" : {
				if(stack.isEmpty()) return "ERROR";
				stack.push(stack.peek());
				break;
			}
			case "SWP" : {
				if(stack.size() >= 2) {
					int num1 = stack.pop();
					int num2 = stack.pop();
					stack.push(num1);
					stack.push(num2);
				}
				else return "ERROR";
				break;
			}
			case "ADD" : {
				if(stack.size() >= 2) {
					int num1 = stack.pop();
					int num2 = stack.pop();
					long sum = (long)num1 + num2;
					if(Math.abs(sum) > 1000000000) return "ERROR";
					else stack.push((int)sum);
				}
				else return "ERROR";
				break;
			}
			case "SUB" : {
				if(stack.size() >= 2) {
					int num1 = stack.pop();
					int num2 = stack.pop();
					long sub = (long)num2 - num1;
					if(Math.abs(sub) > 1000000000) return "ERROR";
					else stack.push((int)sub);
				}
				else return "ERROR";
				break;
			}
			case "MUL" : {
				if(stack.size() >= 2) {
					int num1 = stack.pop();
					int num2 = stack.pop();
					long mul = (long)num1 * num2;
					if(Math.abs(mul) > 1000000000) return "ERROR";
					else stack.push((int)mul);
				}
				else return "ERROR";
				break;
			}
			case "DIV" : {
				if(stack.size() >= 2) {
					boolean neg = false;
					int num1 = stack.pop();
					int num2 = stack.pop();
					if(num1 == 0) return "ERROR";
					long mul = (long)num1 * num2;
					if(mul < 0) neg = true;
					int div = Math.abs(num2) / Math.abs(num1);
					if(neg) stack.push(-1 * div);
					else stack.push(div);
				}
				else return "ERROR";
				break;
			}
			case "MOD" : {
				if(stack.size() >= 2) {
					int num1 = stack.pop();
					int num2 = stack.pop();
					if(num1 == 0) return "ERROR";
					int mod = Math.abs(num2) % Math.abs(num1);
					if(num2 < 0) stack.push(-1 * mod);
					else stack.push(mod);
				}
				else return "ERROR";
				break;
			}
			}
		}
		if(stack.size() == 1) {
			return String.valueOf(stack.pop());
		}
		else return "ERROR";
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		while(true) {
			list = new ArrayList<>();
			while(true) {
				String input = br.readLine();
				if(input.equals("QUIT")) { 
					flag = true;
					break;
				}
				else if(input.equals("END")) break;
				else {
					list.add(input);
				}
			}
			if(flag) break;
			int N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				stack.push(Integer.parseInt(br.readLine()));
				sb.append(T.solution()).append('\n');
				stack.clear();
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
