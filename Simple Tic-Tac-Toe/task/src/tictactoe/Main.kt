package tictactoe

fun winsX( str: String): Boolean {

    if (Regex("(XXX.{6})|(...XXX...)|(.{6}XXX)").containsMatchIn(str) ||
        Regex("X..X..X").containsMatchIn(str) ||
        Regex("(X...X...X)|(..X.X.X..)").containsMatchIn(str)) {
        return true
    }
    return false
}

fun main() {
    // write your code here
    val curState = readLine()!!
    if (curState.length == 9) {
        print("---------\n| ")
        for (cur in 0..curState.lastIndex){
            print(curState[cur] + " ")
            if (((cur + 1) % 3 == 0) && (cur > 0) && (cur < 8)){
                print("|\n| ")
            }
        }
        println("|\n---------")
    } else println("no correct")
    val res = Regex("(XXX)|(OOO)").containsMatchIn(curState)
}