package us.praefectus.scorebored;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import us.praefectus.scorebored.swing.Swing;
import us.praefectus.scorebored.util.IO;

/**
 *
 * @author ken
 */
public final class JacobExcuses {
   
    private static final Logger logger = Logger.getLogger(JacobExcuses.class);
    private static JacobExcuses instance = null;
    
    private static final File excuseFile = new File(Scorebored.CONFIG_DIR, 
            "JacobExcuses.txt");
    private static final File tempFile = new File(Scorebored.CONFIG_DIR, 
            "JacobExcuses.tmp");
    
    private SortedSet<String> jacobExcuses = new TreeSet<String>();
    private static final String[] DEFAULT_JACOB_LIST = new String[] {
        "I am over trained.",
        "Did that nick?",
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
        "Was that interference?"
    };
    
    public static JacobExcuses getInstance() {
        if ( instance == null ) {
            instance = new JacobExcuses();
            try {
                instance.load();
            } catch ( IOException ie ) {
                Swing.showError("Unable to load Jacob excuses, using defaults", ie);
            }
        }
        return instance;
    }
    
    private JacobExcuses() {
    }
        
    public String getRandom() {
        int randomNumber = (int)(Math.random() * (jacobExcuses.size()));
        return getList().get(randomNumber);
    }
    
    public List<String> getList() {
        return Collections.unmodifiableList(new ArrayList(jacobExcuses));
    }
    
    public void load() throws IOException {
        if(!excuseFile.isFile()) {
            logger.info("Using default Jacob excuses");
            jacobExcuses.addAll(Arrays.asList(DEFAULT_JACOB_LIST));
        } else {
            logger.info("Loading Jacob excuse file: " + excuseFile);        
            BufferedReader in = null;

            jacobExcuses.clear();
            try {
                in = new BufferedReader(new FileReader(excuseFile));
                String line;
                while ( (line = in.readLine()) != null ) {
                    jacobExcuses.add(line);
                }
                in.close();
            } finally {
                IO.release(in);
            }
        }
    }
    
    public void save() throws IOException {
        BufferedWriter out = null;
        
        logger.info("Saving Jacob excuses");
        
        try {
            Scorebored.CONFIG_DIR.mkdirs();
            out = new BufferedWriter(new FileWriter(tempFile));

            for ( String excuse: jacobExcuses ) {
                out.write(excuse + "\n");
            }
            out.close();          
            tempFile.renameTo(excuseFile); 
        } finally { 
            IO.release(out);
            if ( tempFile.exists() ) { 
                tempFile.delete();
            }
        }
    }
    
    public boolean remove(String removeExcuse) {
        boolean result = jacobExcuses.remove(removeExcuse);
        if ( jacobExcuses.isEmpty() ) {
            add("Jacob is not impressed!");
        }
        return result;
    }
    
    public boolean add(String newExcuse) {
        return jacobExcuses.add(newExcuse);
    }
    
    public boolean modify(String oldExcuse, String newExcuse) {
        if ( jacobExcuses.contains(newExcuse) ) {
            return false;
        }
        jacobExcuses.remove(oldExcuse);
        jacobExcuses.add(newExcuse);
        return true;
    }
    
    
}
