package tictactoe

import java.lang.Math.abs

fun winsX( str: String): Boolean {

    if (Regex("(XXX.{6})|(...XXX...)|(.{6}XXX)").containsMatchIn(str) ||
        Regex("X..X..X").containsMatchIn(str) ||
        Regex("(X...X...X)|(..X.X.X..)").containsMatchIn(str)) {
        return true
    }
    return false
}
fun winsO( str: String): Boolean {

    if (Regex("(OOO.{6})|(...OOO...)|(.{6}OOO)").containsMatchIn(str) ||
        Regex("O..O..O").containsMatchIn(str) ||
        Regex("(O...O...O)|(..O.O.O..)").containsMatchIn(str)) {
        return true
    }
    return false
}
fun resStr( str: String): String {
    val countX = Regex("X").findAll(str).count()
    val countO = Regex("O").findAll(str).count()
    val countE = Regex("-").findAll(str).count()
    if (abs(countX - countO) > 1) { return "Impossible " }
    if (winsO(str) && winsX(str)) { return "Impossible" }
    if (winsX(str)) { return "X wins" }
    if (winsO(str)) { return "O wins" }
    if (countE == 0) { return "Draw" }
    return "Game not finished"
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
    println(resStr(curState))
}