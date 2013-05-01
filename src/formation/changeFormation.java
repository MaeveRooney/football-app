package formation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.commons.lang3.*;

public class ChangeFormation {
	Map<String, String> defenseMap = new HashMap<String, String>();
	Map<String, String> midMap = new HashMap<String, String>();
	Map<String, String> attackMap = new HashMap<String, String>();
	Map<String, String> goalieMap = new HashMap<String, String>();
    String submit = null;
    String footballer = null;
    String formName = null;
    String position = null;
    
    public ChangeFormation(String formName, String position, String footballer){
    	this.footballer = footballer;
    	this.formName = formName;
    	this.position = position;
    }
    
    public ChangeFormation(){
    }
    
    public ChangeFormation(String formName, String position){
    	this.position = position;
    	this.formName = formName;
    }

    private void addDefense(String pos, String name) {
        defenseMap.put(pos, name);
    }

    private void removeDefense(String pos) {
        defenseMap.remove(pos);
    }
    
    private void addMid(String pos, String name) {
    	midMap.put(pos, name);
    }

    private void removeMid(String pos) {
    	midMap.remove(pos);
    }
    
    private void addAttack(String pos, String name) {
        attackMap.put(pos, name);
    }

    private void removeAttack(String pos) {
        attackMap.remove(pos);
    }
    
    private void addGoalie(String pos, String name) {
    	goalieMap.put(pos, name);
    }

    private void removeGoalie(String pos) {
        goalieMap.remove(pos);
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

    public Map<String, String> getDefense() {
        return defenseMap;
    }

    public Map<String, String> getMid() {
        return midMap;
    }
    
    public Map<String, String> getAttack() {
        return attackMap;
    }
    
    public Map<String, String> getGoalie() {
        return goalieMap;
    }
    
    public String[] getAllPlayers() {
    	String[] goalie = new String[getGoalie().size()];
    	for (int i=0; i < getGoalie().size(); i++) {
    		String value = (String)getGoalie().values().toArray()[i];
    		goalie[i] = value;
    	}
    	String[] defense = new String[getDefense().size()];
    	for (int i=0; i < getDefense().size(); i++) {
    		String value = (String)getDefense().values().toArray()[i];
    		defense[i] = value;
    	}
    	String[] mid = new String[getMid().size()];
    	for (int i=0; i < getMid().size(); i++) {
    		String value = (String)getMid().values().toArray()[i];
    		mid[i] = value;
    	}
    	String[] attack = new String[getAttack().size()];
    	for (int i=0; i < getAttack().size(); i++) {
    		String value = (String)getAttack().values().toArray()[i];
    		attack[i] = value;
    	}
    	String[] both = ArrayUtils.addAll(goalie, defense);
    	String[] both2 = ArrayUtils.addAll(mid, attack);
    	String[] all = ArrayUtils.addAll(both, both2);
        return all;
    }
    
    public void processRequest() {
    	System.out.print(" footballer is "+ footballer+"\r\n");
    	System.out.print(" form is "+ formName+"\r\n");
        // "add" or "remove"
    	if (footballer != null){
	        if (formName.equals("add defense"))
	            addDefense(position, footballer);	        
	        else if (formName.equals("add midfield"))
	            addMid(position, footballer);
	        else if (formName.equals("add attack"))
	            addAttack(position, footballer);        
	        else if (formName.equals("add goalie"))
	            addGoalie(position, footballer);	        
	        // reset at the end of the request
    	} else if (position != null){
    		if (formName.equals("remove goalie"))
	            removeGoalie(position);
    		else if (formName.equals("remove attack"))
	            removeAttack(position);
    		else if (formName.equals("remove defense"))
	            removeDefense(position);
	        else if (formName.equals("remove midfield"))
	            removeMid(position);    		
    	}
        reset();
    }

    // reset
    private void reset() {
    	formName = null;
        footballer = null;
        position = null;
    }
}
