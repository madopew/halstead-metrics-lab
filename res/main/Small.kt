fun main() {
    //This program outputs Fibonacci numbers
    var l = 1;
    var r = 1;
    for(i in 3..50) {
        val tmp = l
        l += r
        r = tmp
    }
    
    if(l > 1000)
        println("It's big!");
    else
        println(l)

    /*
    return a+b    
     */
}