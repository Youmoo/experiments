// Example showing how to expose a script global var from caller

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// expose variable to engine
engine.put("name", "Sundar")

// access it from script
engine.eval("print('Hello, ' + name)")
