import kotlin.random.Random

interface Priority{
    fun get(d: Any?): Double
}

class PrQueue(var priority: Priority, var heap: DynArray = DynArray()){
    fun enQueue(d: Any?){
        var index = heap.size
        var parent = (index - 1)/2
        heap.add(d)
        while(index != 0 && priority.get(heap.get(index)) > priority.get(heap.get(parent)) ){
            var t = heap.get(index)
            heap.set(heap.get(parent), index)
            heap.set(t, parent)
            index = parent
            parent = (index - 1)/2
        }
    }

    fun deQueue(): Any? {
        if (!heap.isEmpty()) {
            var d = heap.get(0)
            heap.remove()
            var index = heap.size
            var t = heap.get(index)
            heap.set(t, 0)
            var parent = 0
            var child = bestChild(parent)
            var papr = priority.get(heap.get(parent))
            while (child >= 0) {
                var chpr = priority.get(heap.get(child))
                if(papr >= chpr) {
                    child = -1
                }else{
                    t = heap.get(parent)
                    heap.set(heap.get(child), parent)
                    heap.set(t, child)
                    parent = child
                    child = bestChild(parent)
                }

            }
            return d
        }
        return null
    }

    fun bestChild(parent: Int): Int{
        var left = (2 * parent) + 1
        var right = (2 * parent) + 2
        if(left >= heap.size){
            return -1
        }

        if(right >= heap.size){
            return left
        }

        var lepr = priority.get(heap.get(left))
        var ripr = priority.get(heap.get(right))

        if(lepr > ripr){
            return left
        }else{
            return right
        }
    }

    fun isEmpty(): Boolean{
        return heap.isEmpty()
    }
    fun print(){
        for(i in 0..(heap.size - 1)){
            print(heap.get(i))
            print(" / ")
        }
        print("\n")

    }

}

class InPriority(): Priority{
    override fun get(d: Any?): Double{
        return (d as Int).toDouble()
    }

}
// Main de prueba Cola de prioridad
//fun main(args: Array<String>){
//    var queue = PrQueue(InPriority())
//    for (i in 0..29){
//        queue.enQueue(Random.nextInt(0, 100))
//        queue.print()
//    }
//    print("\n")
//    print(" Se borran datos de la cola")
//    print("\n")

//    while(!queue.isEmpty()){
//        var x = queue.deQueue()
//        print(x)
//        print("\n")
//        queue.print()
//    }
//}
