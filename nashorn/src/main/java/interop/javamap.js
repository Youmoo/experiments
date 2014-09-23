// Java Map keys as properties

// Demonstrating Java Map key/value can be accessed
// as property/value from script.

var HashMap = Java.type("java.util.HashMap")
var map = new HashMap()

// map key-value access by java get/put method calls
map.put('js', 'nashorn')
print(map.get('js'))

// access keys of map as properties
print(map['js'])
print(map.js)

// also assign new key-value pair 
// as 'property-value'
map['language'] = 'java'
print(map.get("language"))
print(map.language)
print(map['language'])

map.answer = 42
print(map.get("answer"))
print(map.answer)
print(map['answer'])
