fun calculateDay12a(input: List<String>) {
    for (s in input) {
        val record = s.split(" ")[0]
        val groups = s.split(" ")[1].split(",").map { it.toInt() }
        println("$record : $groups has ${countArrangements(record, groups)} arrangements")
    }
}

private val cache = hashMapOf<Pair<String, List<Int>>, Long>()
fun countArrangements(record: String, groups: List<Int>): Long {
    if (groups.isEmpty()) {
        return if ('#' in record) 0 else 1
    }
    if (record.isEmpty()) {
        return 0
    }
    return cache.getOrPut(record to groups) {
        var result = 0L
        if (record.first() in ".?") {
            result+=countArrangements(record.drop(1), groups)
        }
        if (record.first() in "#?" && groups.first() <= record.length && '.' !in record.take(groups.first()) && (groups.first() == record.length || record[groups.first()] != '#')) {
            result+= countArrangements(record.drop(groups.first()+1), groups.drop(1))
        }
        result
    }


}
