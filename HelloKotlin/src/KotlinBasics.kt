// Урок 1.2: Kotlin basics

// Операторы и типы
fun operatorsAndTypes() {
    // Шаг 1: числовые операторы. Тип результата совпадает с типом операндов
    println(1 + 1)      // 2
    println(53 - 3)     // 50
    println(50 / 10)    // 5
    println(1.0 / 2.0)  // 0.5
    println(2.0 * 3.5)  // 7.0

    println(6 * 50)     // 300
    println(6.0 * 50.0) // 300.0
    println(6.0 * 50)   // 300.0

    // Операторы — это методы
    println(2.times(3))   // 6
    println(3.5.plus(4))  // 7.5
    println(2.4.div(2))   // 1.2

    // Шаг 2: неявного приведения типов нет, только явное
    val i: Int = 6
    val b1 = i.toByte()
    println(b1) // 6

    val b2: Byte = 1 // литерал проверяется статически
    println(b2) // 1

    // val i1: Int = b2       // error: type mismatch: inferred type is Byte but Int was expected
    // val i2: String = b2    // error: type mismatch: inferred type is Byte but String was expected
    // val i3: Double = b2    // error: type mismatch: inferred type is Byte but Double was expected

    val i4: Int = b2.toInt()
    println(i4) // 1

    val i5: String = b2.toString()
    println(i5) // 1

    val i6: Double = b2.toDouble()
    println(i6) // 1.0

    // Подчёркивания в числовых литералах
    val oneMillion = 1_000_000
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
    println("$oneMillion $socialSecurityNumber $hexBytes $bytes")

    // Шаг 3: var можно переприсвоить, val — нет
    var fish = 1
    fish = 2
    val aquarium = 1
    // aquarium = 2           // error: val cannot be reassigned
    println("fish = $fish, aquarium = $aquarium")

    // Явное указание типа; после объявления тип не меняется
    var fishCount: Int = 12
    var lakes: Double = 2.5
    println("fishCount = $fishCount, lakes = $lakes")

    // Шаг 4: строковые шаблоны
    val numberOfFish = 5
    val numberOfPlants = 12
    println("I have $numberOfFish fish" + " and $numberOfPlants plants")
    // I have 5 fish and 12 plants

    println("I have ${numberOfFish + numberOfPlants} fish and plants")
    // I have 17 fish and plants
}

// Условия и булевы значения
fun conditionsAndBooleans() {
    val numberOfFish = 50
    val numberOfPlants = 23

    if (numberOfFish > numberOfPlants) {
        println("Good ratio!")
    } else {
        println("Unhealthy ratio")
    }
    // Good ratio!

    // Проверка вхождения в диапазон
    val fish = 50
    if (fish in 1..100) {
        println(fish)
    }
    // 50

    if (numberOfFish == 0) {
        println("Empty tank")
    } else if (numberOfFish < 40) {
        println("Got fish!")
    } else {
        println("That's a lot of fish!")
    }
    // That's a lot of fish!

    // when вместо цепочки if/else
    when (numberOfFish) {
        0 -> println("Empty tank")
        in 1..39 -> println("Got fish!")
        else -> println("That's a lot of fish!")
    }
    // That's a lot of fish!
}

// Nullability
fun nullability() {
    // Шаг 1: null допустим только для типов с ?
    // var rocks: Int = null  // error: null can not be a value of a non-null type Int
    var marbles: Int? = null
    println(marbles) // null

    // Шаг 2: операторы ? и ?:
    // Тип Int? вместо выводимого Int, иначе ?. и ?: не имеют смысла
    var fishFoodTreats: Int? = 6
    if (fishFoodTreats != null) {
        fishFoodTreats = fishFoodTreats.dec()
    }
    println(fishFoodTreats) // 5

    // Safe call: при null метод не вызывается, результат null
    fishFoodTreats = fishFoodTreats?.dec()
    println(fishFoodTreats) // 4

    // Elvis: значение по умолчанию при null
    fishFoodTreats = fishFoodTreats?.dec() ?: 0
    println(fishFoodTreats) // 3

    // Шаг 3: !! бросает NullPointerException, если значение null
    val s: String? = "salmon"
    val len = s!!.length
    println(len) // 6
}

// Массивы, списки и циклы
fun arraysListsAndLoops() {
    // Шаг 1: listOf — только чтение, mutableListOf — изменяемый
    val school = listOf("mackerel", "trout", "halibut")
    println(school) // [mackerel, trout, halibut]

    val myList = mutableListOf("tuna", "salmon", "shark")
    println(myList.remove("shark")) // true

    // Шаг 2: массивы фиксированного размера
    val schoolArray = arrayOf("shark", "salmon", "minnow")
    println(java.util.Arrays.toString(schoolArray)) // [shark, salmon, minnow]

    // С Kotlin 2.4 тип смешанного массива нужно указать явно
    val mix = arrayOf<Any>("fish", 2)
    println(java.util.Arrays.toString(mix)) // [fish, 2]

    // Массив примитивов без упаковки
    val numbers = intArrayOf(1, 2, 3)

    val numbers3 = intArrayOf(4, 5, 6)
    val foo2 = numbers3 + numbers
    println(foo2[5]) // 3

    // Вложенный массив печатается как ссылка
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(numbers, oceans, "salmon")
    println(oddList) // [[I@89178b4, [Atlantic, Pacific], salmon]

    // Инициализация через лямбду, it — индекс
    val array = Array(5) { it * 2 }
    println(java.util.Arrays.toString(array)) // [0, 2, 4, 6, 8]

    // Шаг 3: циклы
    for (element in schoolArray) {
        print(element + " ")
    }
    println()
    // shark salmon minnow

    for ((index, element) in schoolArray.withIndex()) {
        println("Item at $index is $element\n")
    }
    // Item at 0 is shark
    // Item at 1 is salmon
    // Item at 2 is minnow

    for (i in 1..5) print(i)
    println() // 12345

    for (i in 5 downTo 1) print(i)
    println() // 54321

    for (i in 3..6 step 2) print(i)
    println() // 35

    for (i in 'd'..'g') print(i)
    println() // defg

    var bubbles = 0
    while (bubbles < 50) {
        bubbles++
    }
    println("$bubbles bubbles in the water\n")

    do {
        bubbles--
    } while (bubbles > 50)
    println("$bubbles bubbles in the water\n")

    repeat(2) {
        println("A fish is swimming")
    }
    // 50 bubbles in the water
    // 49 bubbles in the water
    // A fish is swimming
    // A fish is swimming
}
