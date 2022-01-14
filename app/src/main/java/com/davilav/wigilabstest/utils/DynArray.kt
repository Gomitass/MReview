class DynArray (var data: Array<Any?> = Array<Any?>(10){null}, var size: Int = 0){

    fun isEmpty(): Boolean{
        return size == 0
    }

    fun add(d:Any?){
        if (size == data.size){
            data = data.copyOf((size * 1.618).toInt())
        }
        data[size] = d
        size++
    }

    fun remove(){
        if (!isEmpty()){
            size--
            if (size > 10 && 2 * size <= data.size){
                data = data.copyOf((data.size * 0.618).toInt())
            }
        }
    }

    fun get(index:Int): Any?{
        return data[index]
    }

    fun set(d: Any?, index: Int){
        data[index] = d
    }
} 
