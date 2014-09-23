// Simple example showing global variable access from caller

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// eval code!
engine.eval("x = 'hello'")

// access global var from engine
print(engine.get('x'))
