package com.sanju.jetpackclass

fun main(){
    var str: String? = "hello"
    println("safe operator ?. "+str?.length)
    var str_length = str?.length ?: 0
    println("elvis operator ?: $str_length")
    println("Non null assertion ?: "+str!!.length)

//    println("Hello world")
//    val name : String = "sanju"
//    var age: Int = 20
//    var square = 20.0
//    age = 17
//    val number : Long = age.toLong()
//    var arr1 = arrayOf(1,1,1,1)
//
//    var i=0
//    while (i<2){
//        println(i)
//        i++
//    }
//
//    do {
//        println(i)
//        i++
//    }while (i<3)
//
//    for (i in 1..4){
//        println(i)
//    }

//    println("Square of the $square is ${sqrt(square)}")

    myFunction("welcome home", "Sanju", 1,1,2,3,4)

    var obj = Employee()
    println("employee name is ${obj.name}")
    obj.insuranceDetail()

    Employee.nestedClass().nestedDetail()
    obj.innerClass().innerDetail()

    val objec = Insurance("Ram", "Sita")
    val objecextended = extendFamilyInsurance("Ram", "Sita", "hffff", "hiii")

    var map1 = mapOf<Int, String>(100 to "amar", 102 to "helo", 103 to "ooo")
    var map2 = hashMapOf<Int, String>(100 to "amar", 102 to "helo", 103 to "ooo")
    var map3 = mutableMapOf<Int, String>(100 to "amar", 102 to "helo", 103 to "ooo")
    var map4 = HashMap<Int, String>()

    for(element in map1){
        println(element)
    }
    map2.put(104, "raggg")
    println("${map2.values}")    //[101,102,103]
    map4.put(100, "one")
    println("${map4.keys}")      //[amar,helo,ooo]

    var set1 = setOf<Int>(1,3,1,9)
    var set2 = hashSetOf<Int>(1,3,1,9)
    var set3 = mutableSetOf<Int>(1,3,1,9)

    set2.add(7)
    set2.remove(0)
    set3.remove(0)
    for (element in set1)
        println(element)

    //filter
    var name = listOf<String>("hhh", "kkk", "lll")
    var filteredList = name.filter { it.length < 5 }
    println(filteredList)

    val list = name.groupBy(keySelector = {it.first()}, valueTransform = {it.toUpperCase()})
    println(list)
    val list1 = name.groupByTo(LinkedHashMap(), keySelector = {it.first()}, valueTransform = {it.toUpperCase()})
    list1['a']?.add("mmm")
    println(list1)
    val list2 = name.groupingBy { it.first() }.eachCount()
    println(list2)

}

class extendFamilyInsurance(name: String, spouchName: String, father: String): Insurance(name, spouchName){
    init {
        println("with $name , father is $father also included")
    }
    constructor(name: String, spouchName: String, father: String, fatherInlaw: String): this(name, spouchName, father){
        println("$fatherInlaw is father of $spouseName for $name")
    }
}

open class Insurance(var name: String){
    var spouseName : String = "unknown"
    init {
        println("$spouseName has an isurance of 40000")
    }
    // secondary constructor
    constructor(name: String, spouchName: String): this(name){
        this.spouseName = spouchName
        println("$spouseName has also insurance")
    }
}

class Employee{
    var name: String = "sanju"
    var insuranceAmount: Int = 40000
    fun insuranceDetail(){
        println("$name has an insurance of $insuranceAmount")
    }
    // nested class
    class nestedClass(){
        var nestedName : String = "Manju"
        fun nestedDetail(){
            println("$nestedName is also eligible for insurance")
        }
    }
    // inner class
    inner class innerClass(){
        var innerName : String = "Manju"
        fun innerDetail(){
            println("$innerName is also eligible for insurance of $insuranceAmount")
        }
    }
}

fun myFunction(message: String, name: String, vararg number: Int) {
    println("message is $message and name is $name")
    for (i in number){
        println(i)
    }
}

/* Kotlin is developed by JetBrains in 2010
An open source, statically types programming language
Object-oriented and functional capability */

/* ---------Features
---No NullPointer Exception
---Interoperable with Java (java to kotlin and vice-versa)
---Minimizing Boilerplate Code

// val - Immutable reference
// var - Mutable reference

continue - skips the current iteration of the control to end of the loop
-unlabeled continue
-labeled continue

Nested class -
allow you to define a class within another class
it's static by default
data member and member function - (.) dot notation
can't access data member of outer class
similar to static nested class in java
inner class -
using inner keyword
helps in accessing the outer class property
similar to non-static nested class in java
*/