fun main(){

    var nivel = 5
    var impacto = 0
    var avanza = 0
    var numEnemy: Int

    // Clases
    val enemy = Enemy()
    val player = Player(10,1000)
    val map1 = Map(10,10) // Mapa del jugador
    val map2 = Map(10,10) // Mapa del enemigo

    var mapPlayer = map1.createMap(nivel) // el mapa del jugador esta encima del mapa de los enemigos
    var mapEnemy = map2.createMap(nivel)

    do {
        numEnemy = enemy.randomEnemy(nivel,mapEnemy) // Se crean los enemigos aleatorios en mapEnemy
    }while (numEnemy<nivel-2)

    mapPlayer[0][0] = "[x]" // Jugador representado con "x"

    do {
        mapPlayer[nivel-1][nivel-1] = "[!]" // Ultima posicion en el mapa, representa la meta.

        println("NIVEL: ${nivel-4}")
        println("VIDAS: ${player.vidas}")
        println("PUNTAJE: ${player.puntaje}")

        // Dibujamos el mapa del jugador
        for (x in 0..nivel){
            for (y in 0..nivel){
                print(mapPlayer[x][y])
            }
            println()
        }

        println("8 -> Arriba")
        println("2 -> Abajo")
        println("4 -> Izquierda")
        println("6 -> Derecha")

        val opcion = readLine()?.toInt() // Pedimos el ingreso de un numero al usuario

        // Segun la opción, asignamos un metodo de movimiento
        when(opcion){

            8-> {mapPlayer = map1.createMap(nivel) // creamos el mapa nuevamente y movemos al jugador

                player.moveUp(mapPlayer)
            }

            2->{mapPlayer = map1.createMap(nivel)

                player.moveDown(nivel,mapPlayer)
            }

            4-> {mapPlayer = map1.createMap(nivel)

                player.moveLeft(mapPlayer)
            }

            6->{mapPlayer = map1.createMap(nivel)

                player.moveRight(nivel,mapPlayer)
            }

            else -> {println("MOVIMIENTO INVALIDO!!")}
        }

        // Evaluamos la colisión del jugador con los enemígos
        for (y in 0..nivel){
            for (x in 0..nivel){
                if (mapPlayer[y][x].equals("[x]") && mapEnemy[y][x].equals("[0]")){
                    println("IMPACTO, pierdes una vida")
                    player.vidas -= 1
                    player.puntaje -= 100
                    impacto += 1
                }
            }
        }

        // evaluamos si el jugador llega a la meta
        if (mapPlayer[nivel-1][nivel-1] == "[x]"){
            nivel +=1
            player.puntaje +=100
            avanza +=1
        }

        // si llega a la meta y avanza de nivel se crea el siguiente escenario y se reinician los parametros
        if (avanza == 1){
            enemy.numEnemys = 0
            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            avanza -=1

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)
        }

        // Si hay colisión se reinicia la posición del jugador y se crean nuevamente enemígos aleatorios
        if (impacto == 1){
            enemy.numEnemys = 0

            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            impacto -=1

        }

        if (nivel > 9){
            println("ERES GENIAL, PASASTE TODOS LOS NIVELES!!!")
            System.exit(0)
        }

    }while (player.vidas>0)

    println("Te quedaste sin vidas...FIN DEL JUEGO!!")

    var nivel = 5
    var impacto = 0
    var avanza = 0
    var numEnemy: Int

    // Clases
    val enemy = Enemy()
    val player = Player(10,1000)
    val map1 = Map(10,10) // Mapa del jugador
    val map2 = Map(10,10) // Mapa del enemigo

    var mapPlayer = map1.createMap(nivel) // el mapa del jugador esta encima del mapa de los enemigos
    var mapEnemy = map2.createMap(nivel)

    do {
        numEnemy = enemy.randomEnemy(nivel,mapEnemy) // Se crean los enemigos aleatorios en mapEnemy
    }while (numEnemy<nivel-2)

    mapPlayer[0][0] = "[x]" // Jugador representado con "x"

    do {
        mapPlayer[nivel-1][nivel-1] = "[!]" // Ultima posicion en el mapa, representa la meta.

        println("NIVEL: ${nivel-4}")
        println("VIDAS: ${player.vidas}")
        println("PUNTAJE: ${player.puntaje}")

        // Dibujamos el mapa del jugador
        for (x in 0..nivel){
            for (y in 0..nivel){
                print(mapPlayer[x][y])
            }
            println()
        }

        println("8 -> Arriba")
        println("2 -> Abajo")
        println("4 -> Izquierda")
        println("6 -> Derecha")

        val opcion = readLine()?.toInt() // Pedimos el ingreso de un numero al usuario

        // Segun la opción, asignamos un metodo de movimiento
        when(opcion){

            8-> {mapPlayer = map1.createMap(nivel) // creamos el mapa nuevamente y movemos al jugador

                player.moveUp(mapPlayer)
            }

            2->{mapPlayer = map1.createMap(nivel)

                player.moveDown(nivel,mapPlayer)
            }

            4-> {mapPlayer = map1.createMap(nivel)

                player.moveLeft(mapPlayer)
            }

            6->{mapPlayer = map1.createMap(nivel)

                player.moveRight(nivel,mapPlayer)
            }

            else -> println("MOVIMIENTO INVALIDO!!")
        }

        // Evaluamos la colisión del jugador con los enemígos
        for (y in 0..nivel){
            for (x in 0..nivel){
                if (mapPlayer[y][x].equals("[x]") && mapEnemy[y][x].equals("[0]")){
                    println("IMPACTO, pierdes una vida")
                    player.vidas -= 1
                    player.puntaje -= 100
                    impacto += 1
                }
            }
        }

        // evaluamos si el jugador llega a la meta
        if (mapPlayer[nivel-1][nivel-1] == "[x]"){
            nivel +=1
            player.puntaje +=100
            avanza +=1
        }

        // si llega a la meta y avanza de nivel se crea el siguiente escenario y se reinician los parametros
        if (avanza == 1){
            enemy.numEnemys = 0
            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            avanza -=1

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)
        }

        // Si hay colisión se reinicia la posición del jugador y se crean nuevamente enemígos aleatorios
        if (impacto == 1){
            enemy.numEnemys = 0

            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            impacto -=1

        }

        if (nivel > 9){
            println("ERES GENIAL, PASASTE TODOS LOS NIVELES!!!")
            System.exit(0)
        }

    }while (player.vidas>0)

    println("Te quedaste sin vidas...FIN DEL JUEGO!!")

    var nivel = 5
    var impacto = 0
    var avanza = 0
    var numEnemy: Int

    // Clases
    val enemy = Enemy()
    val player = Player(10,1000)
    val map1 = Map(10,10) // Mapa del jugador
    val map2 = Map(10,10) // Mapa del enemigo

    var mapPlayer = map1.createMap(nivel) // el mapa del jugador esta encima del mapa de los enemigos
    var mapEnemy = map2.createMap(nivel)

    do {
        numEnemy = enemy.randomEnemy(nivel,mapEnemy) // Se crean los enemigos aleatorios en mapEnemy
    }while (numEnemy<nivel-2)

    mapPlayer[0][0] = "[x]" // Jugador representado con "x"

    do {
        mapPlayer[nivel-1][nivel-1] = "[!]" // Ultima posicion en el mapa, representa la meta.

        println("NIVEL: ${nivel-4}")
        println("VIDAS: ${player.vidas}")
        println("PUNTAJE: ${player.puntaje}")

        // Dibujamos el mapa del jugador
        for (x in 0..nivel){
            for (y in 0..nivel){
                print(mapPlayer[x][y])
            }
            println()
        }

        println("8 -> Arriba")
        println("2 -> Abajo")
        println("4 -> Izquierda")
        println("6 -> Derecha")

        val opcion = readLine()?.toInt() // Pedimos el ingreso de un numero al usuario

        // Segun la opción, asignamos un metodo de movimiento
        when(opcion){

            8-> {mapPlayer = map1.createMap(nivel) // creamos el mapa nuevamente y movemos al jugador

                player.moveUp(mapPlayer)
            }

            2->{mapPlayer = map1.createMap(nivel)

                player.moveDown(nivel,mapPlayer)
            }

            4-> {mapPlayer = map1.createMap(nivel)

                player.moveLeft(mapPlayer)
            }

            6->{mapPlayer = map1.createMap(nivel)

                player.moveRight(nivel,mapPlayer)
            }

            else -> println("MOVIMIENTO INVALIDO!!")
        }

        // Evaluamos la colisión del jugador con los enemígos
        for (y in 0..nivel){
            for (x in 0..nivel){
                if (mapPlayer[y][x].equals("[x]") && mapEnemy[y][x].equals("[0]")){
                    println("IMPACTO, pierdes una vida")
                    player.vidas -= 1
                    player.puntaje -= 100
                    impacto += 1
                }
            }
        }

        // evaluamos si el jugador llega a la meta
        if (mapPlayer[nivel-1][nivel-1] == "[x]"){
            nivel +=1
            player.puntaje +=100
            avanza +=1
        }

        // si llega a la meta y avanza de nivel se crea el siguiente escenario y se reinician los parametros
        if (avanza == 1){
            enemy.numEnemys = 0
            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            avanza -=1

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)
        }

        // Si hay colisión se reinicia la posición del jugador y se crean nuevamente enemígos aleatorios
        if (impacto == 1){
            enemy.numEnemys = 0

            mapPlayer = map1.createMap(nivel)
            mapEnemy = map2.createMap(nivel)

            do {
                numEnemy = enemy.randomEnemy(nivel,mapEnemy)
            }while (numEnemy<nivel-2)

            mapPlayer[0][0] = "[x]"
            player.posY = 0
            player.posX = 0

            impacto -=1

        }

        if (nivel > 9){
            println("ERES GENIAL, PASASTE TODOS LOS NIVELES!!!")
            System.exit(0)
        }

    }while (player.vidas>0)

    println("Te quedaste sin vidas...FIN DEL JUEGO!!")

}