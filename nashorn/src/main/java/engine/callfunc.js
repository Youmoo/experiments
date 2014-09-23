// simple example showing how to call a global script 
// function from caller

var ScriptEngineManager = Java.type("javax.script.ScriptEngineManager")
// create manager
var manager = new ScriptEngineManager()
// create engine
var engine = manager.getEngineByName("js")

// eval code!
engine.eval("function func(name) { print('I am func, hello ' + name) }")

// invoke functions, methods of code evaluated by engine
// from javax.script.Invocable interface. But, hey, 
// calling code is JavaScript and don't worry about types :)

engine.invokeFunction("func", "Sundar")
