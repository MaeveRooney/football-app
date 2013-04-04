package formation;

import java.util.Vector;
import org.apache.commons.lang3.*;

public class changeFormation {
    Vector<String> defenseVector = new Vector<String>();
    Vector<String> midVector = new Vector<String>();
    Vector<String> attackVector = new Vector<String>();
    Vector<String> goalieVector = new Vector<String>();
    String submit = null;
    String footballer = null;

    private void addDefense(String name) {
        defenseVector.addElement(name);
    }

    private void removeDefense(String name) {
        defenseVector.removeElement(name);
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

    public void setSubmit(String s) {
        submit = s;
    }

    public String[] getDefense() {
        String[] s = new String[defenseVector.size()];
        defenseVector.copyInto(s);
        return s;
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
    
    public String[] getAllPlayers() {
    	/**String[] s = null;
    	if (getGoalie().length>0){
    		s = new String[goalieVector.size()];
    	}
    	if (getDefense().length>0){
    		if (s != null){
    			String[] both = ArrayUtils.addAll(s, getDefense());
    			s = both;   			
    		} else{
    			s = new String[defenseVector.size()];
    		}
    	}
    	if (getMid().length>0){
    		if (s != null){
    			String[] both = ArrayUtils.addAll(s, getMid());
    			s = both;   			
    		} else{
    			s = new String[midVector.size()];
    		}
    	}
    	if (getAttack().length>0){
    		if (s != null){
    			String[] both = ArrayUtils.addAll(s, getAttack());
    			s = both;   			
    		} else{
    			s = new String[attackVector.size()];
    		}
    	}
        System.out.print("player list\r\n");
        if (s != null){
        	for (int i=0;i<s.length;i++){
        		System.out.print(s[i]+"\r\n");
        	}
        }
        return s;**/
    	
    	String[] goalie = getGoalie();
    	String[] defense = getDefense();
    	String[] mid = getMid();
    	String[] attack = getAttack();
    	String[] both = ArrayUtils.addAll(goalie, defense);
    	String[] both2 = ArrayUtils.addAll(mid, attack);
    	String[] all = ArrayUtils.addAll(both, both2);
    	if (all != null){
        	for (int i=0;i<all.length;i++){
        		System.out.print(all[i]+"\r\n");
        	}
        }
        return all;
    }
    
    public void processRequest() {
    	System.out.print(" footballer is "+ footballer+"\r\n");
        // "add" or "remove"
    	if (footballer != null){
	        if (submit.equals("add defense"))
	            addDefense(footballer);
	        else if (submit.equals("remove defense"))
	            removeDefense(footballer);
	        else if (submit.equals("add midfield"))
	            addMid(footballer);
	        else if (submit.equals("remove midfield"))
	            removeMid(footballer);
	        else if (submit.equals("add attack"))
	            addAttack(footballer);
	        else if (submit.equals("remove attack"))
	            removeAttack(footballer);
	        else if (submit.equals("add goalie"))
	            addGoalie(footballer);
	        else if (submit.equals("remove goalie"))
	            removeGoalie(footballer);
	        // reset at the end of the request
    	}
        //reset();
    }

    // reset
    private void reset() {
        submit = null;
        footballer = null;
    }
}
