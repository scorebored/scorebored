package org.blackchip.scorebored;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.blackchip.scorebored.swing.Swing;
import org.blackchip.scorebored.swing.WindowManager;
import org.blackchip.scorebored.talker.Commentary;
import org.blackchip.scorebored.talker.SwingTalker;

// This gets the award for the worst, sloppiest, and chock-full of side effect
// code. I should be ashamed that I'm this lazy.

public class Actions {

    private static final WindowManager windowManager = WindowManager.getInstance();
    
    static class NewMatchAction extends AbstractAction {
        private Match match;
        
        public NewMatchAction(Match m) {
            super("New");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
            match = m;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( match.isActive() ) {
                int result = JOptionPane.showConfirmDialog(null, "Reset current match?", 
                        "Match Reset", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                if ( result == JOptionPane.YES_OPTION ) { 
                    match.reset();
                } else {
                    return;
                }               
            }
            JDialog dialog = new MatchSettingsDialog(match);
            Swing.centerOnScreen(dialog);
            dialog.setVisible(true);            
        }
    };
    
    static class ContinueMatchAction extends AbstractAction {
        
        public ContinueMatchAction(Match match) {
            super("Continue");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
            setEnabled(false);
            
            match.addListener(new MatchListener() {
                @Override
                public void matchStarted() {
                    setEnabled(true);
                }

                @Override
                public void matchEnded() {
                    setEnabled(false);
                }
            });
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            windowManager.getScoreboardFrame().setVisible(true);            
        }
    };

    static class AdjustSettingsAction extends AbstractAction {
        
        private Match match;
        
        public AdjustSettingsAction(Match m) {
            super("Adjust Settings");
            match = m;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
            setEnabled(false);
            
            match.addListener(new MatchListener() {
                @Override
                public void matchStarted() {
                    setEnabled(true);
                }

                @Override
                public void matchEnded() {
                    setEnabled(false);
                }
            });
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new MatchSettingsDialog(match);
            Swing.centerOnScreen(dialog);
            dialog.setVisible(true);         
        }
    };
    
    static class AdjustScoreAction extends AbstractAction {
        
        private Match match;
        
        public AdjustScoreAction(Match m) {
            super("Adjust Score");
            match = m;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
            setEnabled(false);
            
            match.addListener(new MatchListener() {
                @Override
                public void matchStarted() {
                    setEnabled(true);
                }

                @Override
                public void matchEnded() {
                    setEnabled(false);
                }
            });
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new AdjustmentDialog(match);
            Swing.centerOnScreen(dialog);
            dialog.setVisible(true);           
        }
    };
    
    static class YeahBabyAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public YeahBabyAction(SwingTalker t) {
            super("Yeah Baby!");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            talker.say("Yeah Baby!");
        }
    }

    static class NoBabyAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public NoBabyAction(SwingTalker t) {
            super("No Baby!");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            talker.say("No Baby!");
        }
    }

    static class NothingButNetAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public NothingButNetAction(SwingTalker t) {
            super("Nothing But Net");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            talker.say("Nothing but Net!");
        }
    }
        
    static class ShhAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public ShhAction(SwingTalker t) {
            super("Shh");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            talker.say("Shh");
        }
    }
        
    static class AnnounceScoreAction extends AbstractAction {
        
        private Match match;
        private SwingTalker talker;
        
        public AnnounceScoreAction(Match m) {
            super("Score");
            match = m;
            talker = m.getTalker();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            setEnabled(false);
            
            match.addListener(new MatchListener() {
                @Override
                public void matchStarted() {
                    setEnabled(true);
                }

                @Override
                public void matchEnded() {
                    setEnabled(false);
                }
            });
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            org.blackchip.scorebored.AnnounceScore announceScore = new org.blackchip.scorebored.AnnounceScore(match);
            talker.say(announceScore.getScore());
        }
    }
    
    static class AnnounceServerAction extends AbstractAction {
        
        private Match match;
        private SwingTalker talker;
        
        public AnnounceServerAction(Match m) {
            super("Server");
            match = m;
            talker = m.getTalker();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
            setEnabled(false);
            
            match.addListener(new MatchListener() {
                @Override
                public void matchStarted() {
                    setEnabled(true);
                }

                @Override
                public void matchEnded() {
                    setEnabled(false);
                }
            });
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( match.getServer() != null ) {
                talker.say(new Commentary()
                        .add(match.getTeam(match.getServer()).getName())
                        .add(" is serving"));
            } else {
                talker.say("Volley for serve");
            }
        }
    }
    
    static class RandomExcuseAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public RandomExcuseAction(SwingTalker t) {
            super("Random Excuse");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String excuse = JacobExcuses.getInstance().getRandom();
            talker.say(excuse);
        }
    };
    
    static class MuteAction extends AbstractAction {
        
        private SwingTalker talker;
        
        public MuteAction(SwingTalker t) {
            super("Mute");
            talker = t;
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            talker.toggleMute();
            if ( talker.isMute() ) {
                putValue(NAME, "Unmute");
            } else {
                putValue(NAME, "Mute");
            }
        }
    }
    
    static class CustomCommentaryAction extends AbstractAction {
        
        public CustomCommentaryAction() {
            super("Custom");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            windowManager.getTalkerFrame().setVisible(true);
        }
    };
    
    static class ExcuseEditorAction extends AbstractAction {
        public ExcuseEditorAction() {
            super("Jacob Excuse Editor");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            windowManager.getJacobExcusesFrame().setVisible(true);
        }     
    };
    
    static class ConsoleAction extends AbstractAction {
        public ConsoleAction() {
            super("Console");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            windowManager.getConsoleFrame().setVisible(true);
        }
    };
    
    public static Action NEW_MATCH;
    public static Action CONTINUE_MATCH;
    public static Action ADJUST_SETTINGS;
    public static Action ADJUST_SCORE;
    
    public static Action YEAH_BABY;
    public static Action NO_BABY;
    public static Action SHH;
    public static Action NOTHING_BUT_NET;
    public static Action SCORE;
    public static Action SERVER;
    public static Action MUTE;
    public static Action CUSTOM_COMMENTARY;
    
    public static Action RANDOM_EXCUSE;
    public static Action EXCUSE_EDITOR;
    
    public static Action CONSOLE;
    
    public static void createActions(Match match) {
        NEW_MATCH = new NewMatchAction(match);
        CONTINUE_MATCH = new ContinueMatchAction(match);
        ADJUST_SETTINGS = new AdjustSettingsAction(match);
        ADJUST_SCORE = new AdjustScoreAction(match);
        
        YEAH_BABY = new YeahBabyAction(match.getTalker());
        NO_BABY = new NoBabyAction(match.getTalker());
        SHH = new ShhAction(match.getTalker());
        NOTHING_BUT_NET = new NothingButNetAction(match.getTalker());
        SCORE = new AnnounceScoreAction(match);
        SERVER = new AnnounceServerAction(match);
        MUTE = new MuteAction(match.getTalker());
        CUSTOM_COMMENTARY = new CustomCommentaryAction();
        
        RANDOM_EXCUSE = new RandomExcuseAction(match.getTalker());
        
        EXCUSE_EDITOR = new ExcuseEditorAction();
        CONSOLE = new ConsoleAction();
    }
    
}
