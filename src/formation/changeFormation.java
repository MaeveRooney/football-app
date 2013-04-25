package formation;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.apache.commons.lang3.*;

public class changeFormation {
	Map<String, String> defenseMap = new HashMap<String, String>();
    Vector<String> defenseVector = new Vector<String>();
    Vector<String> midVector = new Vector<String>();
    Vector<String> attackVector = new Vector<String>();
    Vector<String> goalieVector = new Vector<String>();
    String submit = null;
    String footballer = null;
    String formName = null;
    String position = null;

    private void addDefense(String position, String name) {
        defenseMap.put(position, name);
    }

    private void removeDefense(String pos) {
        defenseMap.remove(pos);
    }
    
    private void addMid(String name) {
        midVector.addElement(name);
    }

    private void removeMid(String name) {
        midVector.removeElement(name);
    }
    
    private void addAttack(String name) {
        attackVector.addElement(name);
    }

    private void removeAttack(String name) {
        attackVector.removeElement(name);
    }
    
    private void addGoalie(String name) {
    	goalieVector.addElement(name);
    }

    private void removeGoalie(String name) {
        goalieVector.removeElement(name);
    }
    
    public void setFootballer(String name) {
    	footballer = name;
    }
    
    public void setPosition(String name) {
    	position = name;
    }
    
    public void setFormName(String name) {
    	formName = name;
    }

    public void setSubmit(String s) {
        submit = s;
    }

    public Map<String, String> getDefense() {
        return defenseMap;
    }

    public String[] getMid() {
        String[] s = new String[midVector.size()];
        midVector.copyInto(s);
        return s;
    }
    
    public String[] getAttack() {
        String[] s = new String[attackVector.size()];
        attackVector.copyInto(s);
        return s;
    }
    
    public String[] getGoalie() {
        String[] s = new String[goalieVector.size()];
        goalieVector.copyInto(s);
        return s;
    }
    
    public Map<String, String> getAllPlayers() {
    	String[] goalie = getGoalie();
    	//String[] defense = getDefense();
    	String[] mid = getMid();
    	String[] attack = getAttack();
    	//String[] both = ArrayUtils.addAll(goalie, defense);
    	String[] both2 = ArrayUtils.addAll(mid, attack);
    	//String[] all = ArrayUtils.addAll(both, both2);
        return defenseMap;
    }
    
    public void processRequest() {
    	System.out.print(" footballer is "+ footballer+"\r\n");
    	System.out.print(" form is "+ formName+"\r\n");
        // "add" or "remove"
    	if (footballer != null){
	        if (formName.equals("add defense"))
	            addDefense(position, footballer);
	        else if (formName.equals("remove defense"))
	            removeDefense(footballer);
	        else if (formName.equals("add midfield"))
	            addMid(footballer);
	        else if (formName.equals("remove midfield"))
	            removeMid(footballer);
	        else if (formName.equals("add attack"))
	            addAttack(footballer);
	        else if (formName.equals("remove attack"))
	            removeAttack(footballer);
	        else if (formName.equals("add goalie"))
	            addGoalie(footballer);
	        else if (formName.equals("remove goalie"))
	            removeGoalie(footballer);
	        // reset at the end of the request
    	}
        //reset();
    }

    // reset
    private void reset() {
    	formName = null;
        submit = null;
        footballer = null;
    }
}
