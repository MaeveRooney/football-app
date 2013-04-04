package formation;

import java.util.Vector;

public class changeFormation {
    Vector<String> defenseVector = new Vector<String>();
    Vector<String> midVector = new Vector<String>();
    Vector<String> attackVector = new Vector<String>();
    String submit = null;
    String footballer = null;

    private void addDefense(String name) {
        defenseVector.addElement(name);
    }

    private void removeDefense(String name) {
        defenseVector.removeElement(name);
    }

    public void setFootballer(String name) {
    	footballer = name;
    }

    public void setSubmit(String s) {
        submit = s;
    }

    public String[] getDefense() {
        String[] s = new String[defenseVector.size()];
        defenseVector.copyInto(s);
        return s;
    }

    public void processRequest() {
    	System.out.print(" footballer is "+ footballer);
        // "add" or "remove"
    	if (footballer != null){
	        if (submit.equals("add defense"))
	            addDefense(footballer);
	        else if (submit.equals("remove defense"))
	            removeDefense(footballer);
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
