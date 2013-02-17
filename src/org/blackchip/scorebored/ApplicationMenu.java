/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blackchip.scorebored;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.blackchip.scorebored.swing.WindowManager;

/**
 *
 * @author mcgann
 */
public class ApplicationMenu extends JMenuBar {
    
    private static WindowManager windowManager = WindowManager.getInstance();
    
    public ApplicationMenu(final Match match) {        

        JMenu menuMatch = new JMenu("Match");
        this.add(menuMatch);
        
        menuMatch.add(new JMenuItem(Actions.NEW_MATCH));        
        menuMatch.add(new JMenuItem(Actions.CONTINUE_MATCH));
        menuMatch.add(new JMenuItem(Actions.ADJUST_SETTINGS));
        menuMatch.add(new JMenuItem(Actions.ADJUST_SCORE));
        
        JMenu menuCommentator = new JMenu("Commentator");
        this.add(menuCommentator);
        
        menuCommentator.add(new JMenuItem(Actions.SCORE));
        menuCommentator.add(new JMenuItem(Actions.SERVER));
        menuCommentator.addSeparator();
        menuCommentator.add(new JMenuItem(Actions.RANDOM_EXCUSE));
        menuCommentator.add(new JMenuItem(Actions.YEAH_BABY));
        menuCommentator.add(new JMenuItem(Actions.NO_BABY));
        menuCommentator.add(new JMenuItem(Actions.SHH));
        menuCommentator.add(new JMenuItem(Actions.NOTHING_BUT_NET));
        menuCommentator.addSeparator();
        menuCommentator.add(new JMenuItem(Actions.CUSTOM_COMMENTARY));
        menuCommentator.add(new JMenuItem(Actions.MUTE));
        
        JMenu menuTools = new JMenu("Tools");
        this.add(menuTools);
        
        menuTools.add(new JMenuItem(Actions.EXCUSE_EDITOR));
        menuTools.add(new JMenuItem(Actions.CONSOLE));
    }

    
}
