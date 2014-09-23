// nashorn supports 'stack' property on ECMAScript
// error objects. This property's value is a string
// that shows script stack trace.

function g() {
    throw new Error("wrong");
}

function f() {
    g()
}

// Output looks something like:
//
//  Error: wrong
//	at g (stack.js:6)
//	at f (stack.js:10)
//	at <program> (stack.js:21)

try {
    f()
} catch (e) {
    print(e.stack)
}
