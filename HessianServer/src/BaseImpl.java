
public class BaseImpl implements IBase {

	private String _greeting = "Hello, World!";
	
	
	
	public void set_greeting(String _greeting) {
		this._greeting = _greeting;
	}

	public String greeting(String name) {		
		StringBuffer ret = new StringBuffer("Hello, ");
		ret.append(name);
		return ret.toString();
	}
	
	public String hello() {		
		return _greeting;
	}
}
