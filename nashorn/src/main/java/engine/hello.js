// Simple hello world example showing create engine
// and eval simple script

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")
// eval code!
engine.eval("print('hello world')")

