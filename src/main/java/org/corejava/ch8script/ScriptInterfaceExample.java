package org.corejava.ch8script;

import javax.script.*;

public class ScriptInterfaceExample
{
	public interface Greeter {
		void greet(String name);
	}

	public static void main(String[] args) throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

		// Define a script that implements the 'greet' function
		String script = "var greeter = { greet: function(name) { print('Hello, ' + name + '!'); } };";

		engine.eval(script);

		// Get the greeter object from the script
		Object greeterObj = engine.get("greeter");

		// Use Invocable to get a Java interface implementation
		Invocable invocable = (Invocable) engine;
		Greeter greeter = invocable.getInterface(greeterObj, Greeter.class);

		// Now you can call the method like regular Java
		greeter.greet("Alice");
	}
}
