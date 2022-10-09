import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

// BOJ #5397 키로거
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			ArrayList<Character> Llist = new ArrayList<>();
			Stack<Character> Rlist = new Stack<>();
			String input = br.readLine();
			for(int i = 0 ; i < input.length() ; i ++) {
				char tmp = input.charAt(i);
				switch (tmp) {
				case '<': {
					if(Llist.size() > 0) { 
						Rlist.push(Llist.get(Llist.size() - 1));
						Llist.remove(Llist.size() - 1);
					}
					break;
				}
				case '>': {
					if(!Rlist.isEmpty()) { 
						Llist.add(Rlist.pop());
					}
					break;
				}
				case '-': {
					if(Llist.size() > 0) Llist.remove(Llist.size() - 1);
					break;
				}
				default:
					Llist.add(tmp);
				}
			}
			for(char x : Llist) {
				sb.append(x);
			}
			int length = Rlist.size();
			for(int i = 0 ; i < length ; i ++) {
				sb.append(Rlist.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
