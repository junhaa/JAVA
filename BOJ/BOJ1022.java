import java.util.Scanner;

// BOJ #1022 소용돌이 예쁘게 출력하기
public class Main {
	static int[][] map; 
	static int r1 ,c1 ,r2 ,c2, row, col;
	public int convX(int x) { // 기존의 좌표를 map에 대한 좌표로 변환
		if(c1 < 0) return x + Math.abs(c1);
		else return x - c1;
	}
	
	public int convY(int y) {
		if(r1 < 0) return y + Math.abs(r1);
		else return y - r1;
	}

	public boolean check(int y, int x) { // 배열 내에 있는 수인지
		if(convX(x) >= 0 && convX(x) < col && convY(y) >= 0 && convY(y) < row) return true;
		else return false;
	}
	
	public void solution() {
		int max = Math.abs(r1);
		if(max < Math.abs(c1)) max = Math.abs(c1);
		if(max < Math.abs(r2)) max = Math.abs(r2);
		if(max < Math.abs(c2)) max = Math.abs(c2);
	
		
		if(check(0,0)) map[convY(0)][convX(0)] = 1;
		int curNum = 1;
		int curX = 0;
		int curY = 0;
		
		for(int i = 2 ; i <= max + 1 ; i ++) {
			for(int k = 0 ; k < 2 * (i - 1); k ++) { // UP
				if(k == 0) curX ++;
				else curY --;
				curNum ++;
				if(check(curY,curX)) map[convY(curY)][convX(curX)] = curNum;
			}
			
			for(int k = 0 ; k < 2 * (i - 1); k ++) { // LEFT
				curX --;
				curNum ++;
				if(check(curY,curX)) map[convY(curY)][convX(curX)] = curNum;
			}
			
			for(int k = 0 ; k < 2 * (i - 1); k ++) { // DOWN
				curY ++;
				curNum ++;
				if(check(curY,curX)) map[convY(curY)][convX(curX)] = curNum;
			}
			
			for(int k = 0 ; k < 2 * (i - 1); k ++) { // RIGHT
				curX ++;
				curNum ++;
				if(check(curY,curX)) map[convY(curY)][convX(curX)] = curNum;
			}
		}
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		r1 = sc.nextInt();
		c1 = sc.nextInt();
		r2 = sc.nextInt();
		c2 = sc.nextInt();
		row = Math.abs(r2 - r1) + 1;
		col = Math.abs(c2 - c1) + 1;
		map = new int[row][col];
		T.solution();
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < row ; i ++) {
			for(int k = 0 ; k < col ; k ++) {
				if(max < map[i][k]) max = map[i][k];
			}
		}
		int cnt1 = 1;
		int cnt2 = 1;
		while(true) {
			if(max / cnt2 < 10) break;
			cnt1 ++;
			cnt2 *= 10;
		}
		
		for(int i = 0 ; i < row ; i ++) {
			for(int k = 0 ; k < col ; k ++) {
				int num1 = 1;
				int num2 = 1;
				while(true) {
					if(map[i][k] / num2 < 10) break;
					num1 ++;
					num2 *= 10;
				}
				for(int l = 0 ; l < cnt1 - num1 ; l ++) {
					System.out.print(" ");	
				}
				System.out.print(map[i][k] + " ");
			}
			System.out.println();
		}
	}
}
