// Java List elements accessed/modified via
// array element access/update syntax

var ArrayList = Java.type("java.util.ArrayList")
var list = new ArrayList()

// add elements to list by List's add method calls
list.add("js")
list.add("ecmascript")
list.add("nashorn")

// get by List's get(int) method
print(list[0])
print(list[1])
print(list[2])

// access list elements by indexed access as well
print(list[0])
print(list[1])
print(list[2])

// assign to list elements by index as well
list[0] = list[0].toUpperCase()
list[1] = list[1].toUpperCase()
list[2] = list[2].toUpperCase()

print(list.get(0))
print(list.get(1))
print(list.get(2))
print(list[0])
print(list[1])
print(list[2])
