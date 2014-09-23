// nashorn supports Java interface implementation
// by script using anonymous class-like syntax

var Runnable = Java.type("java.lang.Runnable")
var Thread = Java.type("java.lang.Thread")
// use anonymous class-like new syntax
var r = new Runnable()
{
    run: function () {
        print("I am a runnable " + Thread.currentThread())
    }
}

r.run();

var t = new Thread(r)
t.start()
t.join()
