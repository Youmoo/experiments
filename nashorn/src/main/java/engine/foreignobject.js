#// Usage: jjs -scripting foreignobject.js

// cross nashorn engine scripting
// access script objects from other engines in natural syntax

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// eval code!
engine.eval(<
    <CODE
    var obj = {
        foo: 42,
        func: function() {
        print("func: " + this.foo)
        }
        }
    CODE);

    // Nashorn engine returns script objects as instance of
    // the class jdk.nashorn.api.scripting.ScriptObjectMirror
    // But nashorn's dynalink linker can treat these objects
// specially to support natural script syntax to access..
// In Java code, you need to use ScriptObjectMirror's
    // methods though. Once again, script world is simpler :-)

    var foreignObj = engine.get("obj")
// access properties, functions of it
// with natural syntax
print(foreignObj.foo)
foreignObj.func()
print(typeof foreignObj.func)

// access engine's global
var foreignGlobal = engine.eval("this")
// create objects in engine's world from here!
print(new foreignGlobal.Object())
print(new foreignGlobal.Date());
