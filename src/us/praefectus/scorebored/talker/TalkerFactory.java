package us.praefectus.scorebored.talker;

import us.praefectus.scorebored.util.Check;

/**
 * Generates new instances of Talkers.
 */
public class TalkerFactory {
    
    private Class<? extends Talker> talkerClass;
    
    private TalkerFactory() {
    }
    
    /**
     * Creates a new factory which will create instances of the given class.
     * 
     * @param talkerClass the class used to create talker instances.
     */
    public TalkerFactory(Class<? extends Talker> talkerClass) {
        this.talkerClass = Check.notNull(talkerClass);
    }
    
    /**
     * Creates a new talker instance.
     * 
     * @return an instance of the talker class provided in the constructor.
     * @throws TalkException if the object could not be instantiated.
     */
    public Talker newTalker() throws TalkException {
        try {
            return talkerClass.newInstance();
        } catch ( InstantiationException ie ) {
            throw new TalkException("Unable to create talker: " + 
                    ie.getMessage(), ie);
        } catch ( IllegalAccessException iae ) {
            throw new TalkException("Unable to create talker: " + 
                    iae.getMessage(), iae);
        }
    }
    
    /**
     * Creates a new talker instance wrapped in a SwingTalker.
     * 
     * @return an instance of the talker class provided in the constructor and
     * wrapped by a SwingTalker
     * @throws TalkException if the object could not be instantiated.
     */
    public SwingTalker newSwingTalker() throws TalkException {
        return new SwingTalker(newTalker());
    }
}
