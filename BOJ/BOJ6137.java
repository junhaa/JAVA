import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #6137 문자열 생성 
public class Main {

	static char[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = br.readLine().charAt(0);
		}
		String answer = "";
		String ls = "";
		String rs = "";
		int lt = 0;
		int rt = N - 1;
		while(lt <= rt) {
//			if(!ls.equals("") && (arr[lt] > ls.charAt(ls.length() - 1) && arr[rt] > rs.charAt(rs.length() - 1))) {
//				answer += ls;
//				answer += rs;
//				ls = "";
//				rs = "";
//			}
			if(arr[lt] < arr[rt]) { 
//				if(!ls.equals("")) {
//					answer += ls;
//					ls = "";
//					rt += rs.length();
//					rs = "";
//				}
				answer += arr[lt++];
			}
			else if(arr[lt] > arr[rt]) { 
//				if(!rs.equals("")) {
//					answer += rs;
//					rs = "";
//					lt -= ls.length();
//					ls = "";
//				}
				answer += arr[rt--];
			}
			else {
//				ls += arr[lt ++];
//				rs += arr[rt --];
				int llt = lt;
				int rrt = rt;
				boolean isSame = true;
				while(llt <= rrt) {
					if(arr[llt] < arr[rrt]) {
						isSame = false;
						answer += arr[lt ++];
						break;
					}
					else if (arr[llt] > arr[rrt]) {
						isSame = false;
						answer += arr[rt --];
						break;
					}
					else {
						llt ++;
						rrt --;
					}
				}
				if(isSame) {
					answer += arr[lt++];
				}
			}
		}
		//lt == rt
//		if(!ls.equals("")) {
//			if(lt == rt) {
//				if(ls.charAt(ls.length() - 1) >= arr[lt]) {
//					answer += ls;
//					answer += arr[lt];
//					for(int i = ls.length() - 1 ; i >= 0 ; i --) {
//						answer += ls.charAt(i);
//					}
//				}
//				else {
//					if(arr[lt] > ls.charAt(0)) {
//						answer += ls;
//						answer += rs;
//						answer += arr[lt];
//					}
//					else {
//						answer += ls;
//						answer += arr[lt];
//						for(int i = ls.length() - 1 ; i >= 0 ; i --) {
//							answer += ls.charAt(i);
//						}
//					}
////					answer += ls;
////					ls = "";
////					rt += rs.length();
////					rs = "";
////					while(lt < rt) {
////						if(!ls.equals("") && (arr[lt] > ls.charAt(ls.length() - 1) && arr[rt] > rs.charAt(rs.length() - 1))) {
////							answer += ls;
////							answer += rs;
////							ls = "";
////							rs = "";
////						}
////						if(arr[lt] < arr[rt]) { 
////							if(!ls.equals("")) {
////								answer += ls;
////								ls = "";
////								rt += rs.length();
////								rs = "";
////							}
////							answer += arr[lt++];
////						}
////						else if(arr[lt] > arr[rt]) { 
////							if(!rs.equals("")) {
////								answer += rs;
////								rs = "";
////								lt -= ls.length();
////								ls = "";
////							}
////							answer += arr[rt--];
////						}
////						else {
////							ls += arr[lt ++];
////							rs += arr[rt --];
////						}
////					}
////					if(!ls.equals("")) {
////						answer += ls;
////						if(lt == rt) {
////							answer += arr[lt];
////						}
////						for(int i = ls.length() - 1; i >= 0 ; i --) {
////							answer += ls.charAt(i);
////						}
////					}
////					else {
////						answer += arr[lt];
////					}
//				}
//			}
//			else {
//				answer += ls;
//				for(int i = ls.length() - 1 ; i >= 0 ; i --) {
//					answer += ls.charAt(i);
//				}
//			}
//		}
//		else {
//			answer += arr[lt];
//		}
		if(answer.length() > 80) {
			String ranswer = "";
			for(int i = 1 ; i <= answer.length() ; i ++) {
				ranswer += answer.charAt(i - 1);
				if(i % 80 == 0) ranswer += "\n";
			}
			System.out.println(ranswer);
			return;
		}
		System.out.println(answer);
	}
}
