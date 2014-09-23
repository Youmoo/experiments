#// Usage: jjs -scripting interface.js

// Example demonstrating how to implement a Java interface
// whose methods are backed by global script functions

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// eval code - too many script escapes?
// use heredoc !
engine.eval(<
    <CODE
    function run() {
        print("run global function called")
        }
    CODE)

    // create Java interface object whose methods are
    // implemented by script functions. This is from
    // javax.script.Invocable. But we are in JS world,
    // don't worry about types :)

var Runnable = Java.type("java.lang.Runnable")
var r = engine.getInterface(Runnable.class)
print(r.class)

r.run()
