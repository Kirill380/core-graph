package io.redkite

import java.util.*
import java.util.function.Predicate

class BiTree<T : Comparable<T>>(value: T) {

    private val root: BiNode<T>


    init {
        root = BiNode(value)
    }

    fun insert(value: T) {
        insert(root, value)
    }


    private fun insert(root: BiNode<T>?, value: T): BiNode<T>? {
        if (root != null) {
            val node = if (root.value > value) root.left else root.right
            val nodeSetter = if (root.value > value) root::left.setter else root::right.setter

            if (node != null) {
                return insert(node, value)
            }
            val newNode = BiNode(value)
            nodeSetter(newNode)
            return newNode
        }
        return null
    }

    fun bfs(predicate: Predicate<T>): BiNode<T>? {
        val queue: Queue<BiNode<T>> = LinkedList<BiNode<T>>()
        queue.offer(root)
        while (!queue.isEmpty()) {
            val node = queue.poll()
            if (predicate.test(node.value)) return node
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }
        }
        return null
    }

    fun dfs(predicate: Predicate<T>): BiNode<T>? {
        val deque: Deque<BiNode<T>> = LinkedList<BiNode<T>>()
        deque.push(root)
        while (!deque.isEmpty()) {
            val node = deque.pop()
            if (predicate.test(node.value)) return node
            node.left?.let { deque.push(it) }
            node.right?.let { deque.push(it) }
        }
        return null
    }

    override fun toString(): String {
        return build(root)
    }


    private fun build(node: BiNode<T>?): String {
        if (node == null) {
            return ""
        }
        return build(node.left) + node.value + build(node.right)
    }

    data class BiNode<T>(

            val value: T,

            var left: BiNode<T>? = null,

            var right: BiNode<T>? = null
    )
}
