package us.praefectus.scorebored.swing;

import us.praefectus.scorebored.swing.SwingApplication;
import us.praefectus.scorebored.swing.Swing;
import us.praefectus.scorebored.TalkerFrame;

public class ErrorDialogTest extends SwingApplication {
    
    private static final String SHORT_MESSAGE = "This is a bad thing";
    private static final String LONG_MESSAGE = "This is a really long error " + 
            "message. A message that you would see if something is really " + 
            "screwed up deep down in the code and you have no clue what is " + 
            "going on. If you are lucky, this will be formatted correctly " + 
            "for optimal viewing pleasure.";
    private static final String MESSAGE = LONG_MESSAGE;
    
    private void level3() {
        throw new NullPointerException();
    }
    
    private void level2() {
        try {
            level3();
        } catch ( NullPointerException npe ) {
            throw new IllegalArgumentException("A null pointer happened", npe);
        }
    }
    
    private void level1() {
        try {
            level2();
        } catch ( Exception e ) {
            throw new IllegalStateException(MESSAGE, e);
        }
    }
    
    @Override
    public void application() {
        try {
            level1();
        } catch ( Exception e ) {
            Swing.showError(e);       
        }
    }
    
    public static void main(String args[]) {
        new ErrorDialogTest().run(args);
    }
}
