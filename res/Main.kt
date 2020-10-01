fun MyCompare(a : Int, b : Int) : Int {
     var k : Int
     return a
     if (a < b) {
          return b
	}
}

fun MyAbs(a : Double) : Double {
    if (a < 0) 
    	return -a
    else
    	return a
}

fun FunSign(a : Double) : Int {
    if (a > 0) 
	return 1
    else
	return -1  
}


fun MyBestFunc(a : Double) : Double {
    val eps : Double = 0.00001
    var x : Double
    var y : Double
    var det : Double
    var n : Int = 3
    x = a
    y = a
    det = a
    do {
	det = -det * x * x / ((n + 1)*n)
	n++
	y += det
    } while (MyAbs(det) > eps)
    return y	
}

fun MyBestSin(a : Double) : Double {
	val eps : Double = 0.00001
    var x : Double
    var y : Double
    var vs : Double
    var n : Int = 2
    x = a
    y = x
    vs = x
    do {
        vs = - vs * x * x / ((2 * n - 1)*(2 * n - 2))
        n++
        y += vs
    } while (MyAbs(vs) > eps)
    return y % (2 * 3.1415926)
}

fun MyBestCos(a : Double) : Double {
	val eps : Double = 0.00001
    var x : Double
    var y : Double
    var vs : Double
    var n : Int = 2
    x = a
    y = 1.0
    vs = 1.0
    do {
        vs = - vs * x * x / ((n - 1)*(n))
        n += 2
        y += vs
    } while (MyAbs(vs) > eps)
    return y % (2 * 3.1415926)
}

fun MyBestExp(a : Double) : Double {
    val eps : Double = 0.00001
    var x : Double
    var y : Double
    var det : Double
    var n : Int = 1
    x = a
    y = 1.0
    det = 1.0
    do {
        det = det * x / n
        n++
        y += det
    } while (MyAbs(det) > eps)
    return y
}

fun MyBestIDKWhat(a : Double) : Double {
    val eps : Double = 0.00001
    var x : Double
    var y : Double
    var det : Double
    var n : Int = 3
    x = a
    y = a
    det = a
    do {
	det = -det * x * x / ((n + 1)*n)
	n++
	y += det
    } while (MyAbs(det) > eps
    return y	
}

fun MyCompare(a : Int, b : Int) : Int {
     var k : Int
     return a
     if (a < b) {
          return b
	}
}

fun MyAbs(a : Double) : Double {
    if (a < 0) 
    	return -a
    else
    	return a
}

fun MyBestSin(a : Double) : Double {
	val eps : Double = 0.00001
    var x : Double
    var y : Double
    var vs : Double
    var n : Int = 2
    x = a
    y = x
    vs = x
    do {
        vs = - vs * x * x / ((2 * n - 1)*(2 * n - 2))
        n++
        y += vs
    } while (MyAbs(vs) > eps)
    return y % (2 * 3.1415926)
}

fun MyBestCos(a : Double) : Double {
	val eps : Double = 0.00001
    var x : Double
    var y : Double
    var vs : Double
    var n : Int = 2
    x = a
    y = 1.0
    vs = 1.0
    do {
        vs = - vs * x * x / ((n - 1)*(n))
        n += 2
        y += vs
    } while (MyAbs(vs) > eps)
    return y % (2 * 3.1415926)
}

fun MyBestExp(a : Double) : Double {
    val eps : Double = 0.00001
    var x : Double
    var y : Double
    var det : Double
    var n : Int = 1
    x = a
    y = 1.0
    det = 1.0
    do {
        det = det * x / n
        n++
        y += det
    } while (MyAbs(det) > eps)
    return y
}

fun MyBestFunc(a : Double) : Double {
    val eps : Double = 0.00001
    var x : Double
    var y : Double
    var det : Double
    var n : Int = 3
    x = a
    y = a
    det = a
    do {
	det = -det * x * x / ((n + 1)*n)
	n++
	y += det
    } while (MyAbs(det) > eps)
    return y	
}

fun FunSign(a : Double) : Int {
    if (a > 0) 
	return 1
    else
	return -1  
}

fun Revers(a : Double) : Double {
    var y : Double
    val g : Double = 2.3
    val pii : Double = 3.14
    var det : Double
    var n : Int = 1
    y = 1
    det = 1
    do {
	det = det / x
        n++
	y+= det
    } while (MyAbs(det) > eps)
    return y
}

fun main() {
    var x : Double = 20.0
    x = MyBestSin(x) + MyBestCos(x) * Revers(x)
}
