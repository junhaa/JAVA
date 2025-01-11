import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #23300 웹 브라우저 2
public class Main {
    private static int N, M, curPage = -1;
    private static Stack<Integer> bs = new Stack<>(), fs = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()){
                case "B":
                    back();
                    break;
                case "F":
                    forward();
                    break;
                case "A":
                    access(Integer.parseInt(st.nextToken()));
                    break;
                case "C":
                    compress();
                    break;
            }
        }
        System.out.println(getResult());
    }

    private static String getResult(){
        StringBuilder result = new StringBuilder();
        result.append(curPage).append("\n");
        result.append(getBackStackList()).append("\n");
        result.append(getForwardStackList()).append("\n");
        return result.toString();
    }

    private static String getBackStackList(){
        if(bs.isEmpty()) return "-1";

        StringBuilder sb = new StringBuilder();

        sb.append(bs.pop());

        int len = bs.size();
        for(int i = 0 ; i < len ; i ++){
            sb.append(" " + bs.pop());
        }
        return sb.toString();
    }

    private static String getForwardStackList(){
        if(fs.isEmpty()) return "-1";

        StringBuilder sb = new StringBuilder();

        sb.append(fs.pop());

        int len = fs.size();
        for(int i = 0 ; i < len ; i ++){
            sb.append(" " + fs.pop());
        }
        return sb.toString();
    }



    private static void back(){
        if(bs.isEmpty()) return;

        fs.push(curPage);
        int lastPageNum = bs.pop();
        curPage = lastPageNum;
    }

    private static void forward(){
        if(fs.isEmpty()) return;

        bs.push(curPage);
        int lastPageNum = fs.pop();
        curPage = lastPageNum;
    }

    private static void access(int pageNum){
        fs.clear();

        if(curPage == -1){
            curPage = pageNum;
        }
        else{
            bs.push(curPage);
            curPage = pageNum;
        }
    }

    private static void compress() {
        Stack<Integer> tmpStack = new Stack<>();
        int len = bs.size();

        int last = -1;
        for(int i = 0 ; i < len ; i ++){
            int cur = bs.pop();
            if(cur == last) continue;
            tmpStack.push(cur);
            last = cur;
        }

        int tmpLen = tmpStack.size();
        for(int i = 0 ; i < tmpLen ; i ++){
            bs.push(tmpStack.pop());
        }
    }

}
