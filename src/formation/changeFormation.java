package formation;

import java.util.Vector;

public class changeFormation {
    Vector<String> v = new Vector<String>();
    String submit = null;
    String footballer = null;

    private void addFootballer(String name) {
        v.addElement(name);
    }

    private void removeItem(String name) {
        v.removeElement(name);
    }

    public void setFootballer(String name) {
    	footballer = name;
    }

    public void setSubmit(String s) {
        submit = s;
    }

    public String[] getFootballers() {
        String[] s = new String[v.size()];
        v.copyInto(s);
        return s;
    }

    public void processRequest() {
    	System.out.print(" footballer is "+ footballer);
        // null value for submit - user hit enter instead of clicking on
        // "add" or "remove"
    	if (footballer != null){
	        if (submit == null || submit.equals("add"))
	            addFootballer(footballer);
	        else if (submit.equals("remove"))
	            removeItem(footballer);
	        // reset at the end of the request
    	}
        reset();
    }

    // reset
    private void reset() {
        submit = null;
        footballer = null;
    }
}
