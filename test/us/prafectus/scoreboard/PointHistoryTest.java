package us.prafectus.scoreboard;

import java.util.LinkedList;
import java.util.List;
import us.praefectus.scorebored.PointHistory;
import us.praefectus.scorebored.PointHistory.Type;
import us.praefectus.scorebored.Team.Side;
import static org.junit.Assert.*;
import org.junit.Test;

public class PointHistoryTest {
    
    public PointHistoryTest() {
    }
    
    @Test
    public void test_getOferCount_standard() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        assertEquals(5, PointHistory.getRunCount(histories));
    }

    @Test
    public void test_getOferCount_kenfer() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 10; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        assertEquals(10, PointHistory.getRunCount(histories));
    }
 
    @Test
    public void test_getOferCount_rollbackWithOfer() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        histories.add(0, new PointHistory(Side.LEFT, Type.DECREMENT));         
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        assertEquals(5, PointHistory.getRunCount(histories));
    }

    @Test
    public void test_getOferCount_rollbackWithKenfer() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 10; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        histories.add(0, new PointHistory(Side.LEFT, Type.DECREMENT));
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));        
        assertEquals(10, PointHistory.getRunCount(histories));
    }
    
    @Test
    public void test_getOferCount_fullRollbackWithOfer() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.DECREMENT));
        }
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        }       
        assertEquals(5, PointHistory.getRunCount(histories));
    }
    
    @Test
    public void test_noOfer() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 4; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        assertEquals(1, PointHistory.getRunCount(histories));
    }
    
    @Test
    public void test_noOfer_withOferRollback() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.DECREMENT));
        }   
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        for ( int i = 0; i < 4; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        assertEquals(4, PointHistory.getRunCount(histories));
    }

    @Test
    public void test_noOfer_takeaway() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        histories.add(0, new PointHistory(Side.LEFT, Type.DECREMENT));
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        assertEquals(1, PointHistory.getRunCount(histories));
    }
    
    @Test
    public void test_kenfer_dueToRollback() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 9; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        } 
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        histories.add(0, new PointHistory(Side.RIGHT, Type.DECREMENT));
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        assertEquals(10, PointHistory.getRunCount(histories));
    }
    
    @Test
    public void test_regressionError01() {
        List<PointHistory> histories = new LinkedList<PointHistory>();
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        }
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        histories.add(0, new PointHistory(Side.LEFT, Type.INCREMENT));
        for ( int i = 0; i < 5; i++ ) {
            histories.add(0, new PointHistory(Side.RIGHT, Type.INCREMENT));
        }
        assertEquals(5, PointHistory.getRunCount(histories));
    }

}
