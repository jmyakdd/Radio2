package crte.com.radio

import crte.com.radio.test.Node
import crte.com.radio.test.Tree
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var node = initNode()
        sunxuPrint(node)
        System.out.println()
        daoxuPrint(node)
        System.out.println()
        var nodeNew = fanzhuan(node)
        sunxuPrint(nodeNew!!)

        var tree: Tree<Int> = initTree()
    }

    private fun initTree(): Tree<Int> {
        var tree1 = Tree<Int>(1)
        return tree1
    }

    private fun initNode(): Node<Int> {
        var node1 = Node<Int>(1)
        var node2 = Node<Int>(2)
        node1.next = node2
        var node3 = Node<Int>(3)
        node2.next = node3
        var node4 = Node<Int>(4)
        node3.next = node4
        var node5 = Node<Int>(5)
        node4.next = node5
        var node6 = Node<Int>(6)
        node5.next = node6
        return node1
    }

    private fun daoxuPrint(node: Node<Int>?) {
        if (node != null) {
            daoxuPrint(node.next)
            System.out.print(node.e.toString())
        }
    }

    private fun sunxuPrint(node: Node<Int>) {
        var current: Node<Int>? = node
        while (current != null) {
            System.out.print(current.e.toString() + "")
            current = current.next
        }
    }

    private fun fanzhuan(node: Node<Int>): Node<Int>? {
        var current: Node<Int>? = node
        var nodePre: Node<Int>? = null
        var nodeResult: Node<Int>? = null
        while (current != null) {
            var nodeNext: Node<Int>? = current.next
            if (nodeNext == null) {
                nodeResult = current
            }
            current.next = nodePre
            nodePre = current
            current = nodeNext
        }
        return nodeResult
    }
}
