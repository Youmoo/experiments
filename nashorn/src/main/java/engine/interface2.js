#// Usage: jjs -scripting interface2.js

// Simple example demonstrating how to implement java interface
// whose methods are backed by script methods of a script object

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// eval code - too many script escapes?
// use heredoc !
engine.eval(<
    <CODE
    var obj = {
        run: function() {
        print("I am run method of 'obj'")
        }
        }
    CODE)

    // create Java interface object whose methods are
    // implemented by script methods of a script object
    // This is from javax.script.Invocable. But we are
    // in JS world, don't worry about types :)

var Runnable = Java.type("java.lang.Runnable")

var scriptObj = engine.get("obj")
var r = engine.getInterface(scriptObj, Runnable.class)
print(r.class)
r.run()
