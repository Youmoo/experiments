#// Usage: jjs -scripting callmethod.js

// simple example demonstrating calling a script object
// method from script engine user code

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
        func: function() {
        print("I am func of " + this)
        },

        toString: function() {
        return "Object 'obj'"
        }
        }
    CODE)

    // invoke methods of an object in script world
    // from javax.script.Invocable interface. But, hey,
    // calling code is JavaScript and don't worry about types :)

// get that script object on which to call a method
var scriptObj = engine.get("obj")
// call 'func' method on object 'obj'
engine.invokeMethod(scriptObj, "func")
