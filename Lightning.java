import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Lightning  {
    
    private static final double VAR_FACTOR = 0.40;
    private static final double VAR_DECREASE = 0.55;
    private static final int MIN_LENGTH = 50;

   
    public static ArrayList<Point> buildBolt(Point start, Point end) {
        ArrayList<Point> bolt = new ArrayList<Point>();
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();
        double length = Math.sqrt(dx*dx + dy*dy);
        double variance = length * VAR_FACTOR;
        bolt.add(start);
        buildBolt(start, end, bolt, variance);
        return bolt;
    }
   
    private static void buildBolt(Point start, Point end,
                                  List<Point> bolt, double variance) {
        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();
        double length = Math.sqrt(dx*dx + dy*dy);
        
        if (length > MIN_LENGTH) {        
            int midX = (start.x + end.x)/2;
            int midY = (start.y + end.y)/2;
            int varX = (int) ((Math.random() * variance * 2) - variance);
            midX = midX + varX;
            Point mid = new Point(midX, midY);
            buildBolt(start, mid, bolt, variance * VAR_DECREASE);
            buildBolt(mid, end, bolt, variance * VAR_DECREASE);
        } else {
            bolt.add(end);
        }
        return;      
    }

}