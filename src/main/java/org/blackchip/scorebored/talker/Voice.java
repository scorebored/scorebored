package org.blackchip.scorebored.talker;

import org.blackchip.scorebored.util.Check;

/**
 * Style of voice used by the speaker.
 */
public class Voice implements Comparable<Voice> {
    
    private String id;
    private String name;
    
    private Voice() {
    }
    
    /**
     * Creates a new voice by identifier and name.
     * 
     * @param id the identifier which is usually the argument necessary on the
     * command line to select this voice.
     * @param name a name suitable for display to the end user.
     * @throws IllegalArgumentException if any parameter is an empty string.
     */
    public Voice(String id, String name) { 
        this.id = Check.notEmpty(id);
        this.name = Check.notEmpty(name);
    }
    
    /**
     * Creates a new voice by identifier. The name will be set to the same
     * value as the identifier.
     *
     * @param id the identifier which is usually the argument necessary on the
     * command line to select this voice.
     * @throws IllegalArgumentException if the parameter is an empty string.    
     */
    public Voice(String id) {
        this(id, id);
    }
    
    /**
     * Gets the identifier for this voice. 
     * 
     * @return the identifier which is usually the argument necessary on the
     * command line to select this voice.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Gets the descriptive name.
     * 
     * @return a name suitable for display to the end user.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the string representation.
     * 
     * @return the descriptive name of this voice.
     */
    @Override
    public String toString() {
        return this.name;
    }
    
    /**
     * Indicates if this voice is equivalent.
     * 
     * @param o the object to check
     * @return true if the object is a Voice and the id values are the same.
     */
    @Override
    public boolean equals(Object o) { 
        if ( !(o instanceof Voice) ) {
            return false;
        }
        Voice voice = (Voice)o;
        return this.getId().equals(voice.getId());
    }

    /**
     * Automatically generated by NetBeans.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Compares by descriptive name.
     * 
     * @param voice the voice to compare to.
     */
    @Override
    public int compareTo(Voice voice) {
        return getName().compareTo(voice.getName());
    }
}
