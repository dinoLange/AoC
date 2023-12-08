fun calculateDay5a(input: List<String>): Long {
    val seeds = input[0].removePrefix("seeds:").trim().split(" ").map { it.toLong() }
    val subList = input.subList(2, input.size).toMutableList()
    subList.add("")
    val mapMap = parseCategoryMaps(subList).toMap()

    val locations = mutableListOf<Long>()
    for (seed in seeds) {
        locations.add(findPath(seed, Category.SEED, mapMap))
    }

    return locations.min()
}

fun findPath(number: Long, currentCategory: Category, mapMap: Map<Category, SourceToDestinationMap>): Long {
    if (currentCategory == Category.LOCATION) {
        return number
    }
    val map = mapMap[currentCategory]?: return -1 //should not appear with proper input
    val nextCategory = map.destinationCategory
    val nextNumber = getCorrespondingNumber(number, map.ranges)
    return findPath(nextNumber, nextCategory, mapMap)
}

fun parseCategoryMaps(input: List<String>): MutableList<Pair<Category, SourceToDestinationMap>> {
    var sourceCategory = ""
    var destinationCategory = ""
    val ranges = mutableListOf<Range>()
    val maps = mutableListOf<Pair<Category, SourceToDestinationMap>>()
    for (line in input) {
        if (line.contains("map:")) {
            sourceCategory = line.split(' ')[0].split('-')[0].uppercase()
            destinationCategory = line.split(' ')[0].split('-')[2].uppercase()
            continue
        }
        if (line.isNotEmpty() && sourceCategory.isNotEmpty() && destinationCategory.isNotEmpty()) {
            val numbers = line.split(' ').map { it.toLong() }
            ranges.add(Range(numbers[0], numbers[1], numbers[2]))
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


fun getCorrespondingNumber(number: Long, ranges: List<Range>): Long {
    for (range in ranges) {
        if (number in range.sourceRangeStart..range.sourceRangeStart + range.rangeLength) {
            return number + (range.destinationRangeStart - range.sourceRangeStart)
        }
    }
    return number
}

data class SourceToDestinationMap(
    val ranges: List<Range>,
    val sourceCategory: Category,
    val destinationCategory: Category,
)

data class Range(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long,
)

enum class Category {
    SEED,
    SOIL,
    FERTILIZER,
    WATER,
    LIGHT,
    TEMPERATURE,
    HUMIDITY,
    LOCATION
}
