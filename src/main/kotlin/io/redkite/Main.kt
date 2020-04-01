package io.redkite

class Main

fun main(args: Array<String>) {
    val tree = BiTree<String>("b")
    tree.insert("a")
    tree.insert("c")
    tree.insert("d")
    tree.insert("k")
    tree.insert("l")
    tree.insert("z")
    tree.insert("m")

    println(tree)

}