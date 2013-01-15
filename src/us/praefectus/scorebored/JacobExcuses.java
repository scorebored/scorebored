package us.praefectus.scorebored;

/**
 *
 * @author ken
 */
public class JacobExcuses {
    
    private static final String[] jacobList = new String[] {
        "Your shirt is white or orange, cant see the ball when its in front of your shirt.",
        "My back hurts.",
        "I let him win",
        "The lighting was bad",
        "I was practicing my swing and was not playing serious.",
        "It is Tuesday.",
        "I was blowing my thing.",
        "These balls are too big.",
        "I thought he was Jeff.",
        "My wrist hurts.",
        "Was that interference?", 
        "Did that nick?"
    };
    
    public String getJacobExcuse() {
        int randomNumber = (int)(Math.random() * (jacobList.length));
        return jacobList[randomNumber];
    }

}
