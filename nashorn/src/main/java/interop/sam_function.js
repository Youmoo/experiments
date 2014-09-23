// nashorn supports passing script functions whenever
// a SAM (single abstract method) type object is expected

var System = Java.type("java.lang.System")
var Timer = Java.type("java.util.Timer")
var timer = new Timer()

// schedule method accepts java.util.TimerTask
// which is a single-abstract-method type. you
// can pass a script function and nashorn will
// wrap it as SAM implementor.

timer.schedule(function () {
    print("Hello World!")
}, 1000)

// wait for timer thread to print by
// reading from stdin. 
print("press any key to exit after message from timer...")
System.in.read()
