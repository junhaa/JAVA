// BOJ #1072 게임

import Foundation

func getMin(_ a : Int64, _ b : Int64) -> Int64{
    if(a < b){
        return a;
    }
    else{
        return b;   
    }
}


func binarySearch(_ start : Int64, _ x: Int64, _ y: Int64) -> Int64{
    var lt : Int64 = 0
    var rt : Int64 = 1000000000000
    var min = Int64.max
    while(lt <= rt){
        var mid = (lt + rt) / 2
        let cur =  (y + mid) * 100 / (x + mid)
        // print("cur = \(cur) @@@@@  mid = \(mid)")
        if cur != start{
            min = getMin(min, mid)
            rt = mid - 1;
        }
        else{
            lt = mid + 1;
        }
    }
    return min;
}


let input:[Int64] = readLine()!.split(separator: " ").map { Int64(String($0))! }

let x = input[0]
let y = input[1]

let start : Int64 =  y * 100 / x
let ret = binarySearch(start, x, y);
if(ret == Int64.max){
    print(-1)
}
else{
    print(ret)
}
