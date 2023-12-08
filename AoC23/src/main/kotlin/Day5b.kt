fun calculateDay5b(input: List<String>): Long {
    val seeds = input[0].removePrefix("seeds:").trim().split(" ").map { it.toLong() }
    val seedRangePairs = mutableListOf<Pair<Long, Long>>()
    for (index in seeds.indices step 2) {
        val seedRange = seeds[index] to seeds[index+1]
        seedRangePairs.add(seedRange)
    }

    val subList = input.subList(2, input.size).toMutableList()
    subList.add("")
    val mapMap = parseReverseCategoryMaps(subList).toMap()

    for (location in 0..1000000000L) {
        val seed = findReversePath(location, Category.LOCATION, mapMap)
        for (seedRange in seedRangePairs) {
            if (seed in seedRange.first..seedRange.first+seedRange.second){
                return location
            }
        }
    }
    return -1 //no valid location found
}


fun findReversePath(number: Long, currentCategory: Category, mapMap: Map<Category, SourceToDestinationMap>): Long {
    if (currentCategory == Category.SEED) {
        return number
    }
    val map = mapMap[currentCategory] ?: return -1 //should not appear with proper input
    val nextCategory = map.destinationCategory
    val nextNumber = getCorrespondingNumber(number, map.ranges)
    return findReversePath(nextNumber, nextCategory, mapMap)
}


fun parseReverseCategoryMaps(input: List<String>): MutableList<Pair<Category, SourceToDestinationMap>> {
    var sourceCategory = ""
    var destinationCategory = ""
    val ranges = mutableListOf<Range>()
    val maps = mutableListOf<Pair<Category, SourceToDestinationMap>>()
    for (line in input) {
        if (line.contains("map:")) {
            sourceCategory = line.split(' ')[0].split('-')[2].uppercase()
            destinationCategory = line.split(' ')[0].split('-')[0].uppercase()
            continue
        }
        if (line.isNotEmpty() && sourceCategory.isNotEmpty() && destinationCategory.isNotEmpty()) {
            val numbers = line.split(' ').map { it.toLong() }
            ranges.add(Range(numbers[1], numbers[0], numbers[2]))
            continue
        }
        if (line.isEmpty()) {
            val sourceToDestinationMap = SourceToDestinationMap(
                ranges.toList(),
                Category.valueOf(sourceCategory),
                Category.valueOf(destinationCategory)
            );
            maps.add(Pair(Category.valueOf(sourceCategory), sourceToDestinationMap))
            sourceCategory = ""
            destinationCategory = ""
            ranges.clear()
        }
    }
    return maps
}

