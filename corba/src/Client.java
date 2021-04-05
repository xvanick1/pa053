import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import tasks.*;

import static java.lang.Math.*;

public class Client {
    // Run this class as "java -cp . Client <server object IOR>"
    public static void main(String[] args) throws Exception {
        ORB orb = ORB.init(args, null);
        System.out.println("Hello, here's ORB.");
        AbstractTask nextTask;

        try{
            System.out.println("\n--InitTask start--");
            InitialTask initialTask = InitialTaskHelper.narrow(orb.string_to_object(args[0]));
            AbstractTask initTask = initialTask.init("506486@mail.muni.cz");
            //System.out.println(initTask.info());

            System.out.println("\n--SimpleTask start--");
            SimpleTask simpleTask = SimpleTaskHelper.narrow(initTask);
            while (true) {
                if (simpleTask.isReady()) break;
            }
            System.out.println("Simple task state: " + simpleTask.isReady());
            nextTask = simpleTask.next();

            //System.out.println(nextTask.info());

            System.out.println("\n--AdderTask start--");
            AdderTask adderTask = AdderTaskHelper.narrow(nextTask);
            adderTask.result(adderTask.a() + adderTask.b());
            System.out.println(adderTask.result());
            nextTask = adderTask.next();

            //System.out.println(nextTask.info());

            System.out.println("\n--MatrixTask start--");
            MatrixTask matrixTask = MatrixTaskHelper.narrow(nextTask);
            long a00 = matrixTask.getMatrixItem(0, 0);
            long a01 = matrixTask.getMatrixItem(0, 1);
            long a02 = matrixTask.getMatrixItem(0, 2);

            long a10 = matrixTask.getMatrixItem(1, 0);
            long a11 = matrixTask.getMatrixItem(1, 1);
            long a12 = matrixTask.getMatrixItem(1, 2);

            long a20 = matrixTask.getMatrixItem(2, 0);
            long a21 = matrixTask.getMatrixItem(2, 1);
            long a22 = matrixTask.getMatrixItem(2, 2);

            long determinant = a00*a11*a22 + a10*a21*a02 + a20*a01*a12 - a02*a11*a20 - a12*a21*a00 - a22*a01*a10;
            matrixTask.sendResult((int) determinant);
            nextTask = matrixTask.next();
            //System.out.println(nextTask.info());

            System.out.println("\n--PolygonTask start--");
            PolygonTask polygonTask = PolygonTaskHelper.narrow(nextTask);
            Point[] polygonLine = polygonTask.getPolyLine();

            double distance = 0;
            for (int i = 0; i<polygonLine.length-1; i++){
                distance += sqrt(pow(polygonLine[i+1].x - polygonLine[i].x, 2) + pow(polygonLine[i+1].y - polygonLine[i].y, 2)); //chyba? i - i+1?
            }
            polygonTask.sendResult((float)distance);
            nextTask = polygonTask.next();
            //System.out.println(nextTask.info());

            System.out.println("\n--FlipLineTask start--");
            IntHolder intHolder = new IntHolder();
            FlipLineTask flipLineTask = FlipLineTaskHelper.narrow(nextTask);
            flipLineTask.update(polygonLine, intHolder);


            for (int i = 0;i<polygonLine.length-intHolder.value;i++){
                Point[] flippedPolygonLine = new Point[i+1]; //edit the initialization
                for (int j = 0; j<i+1; j++){
                    flippedPolygonLine[j] = new Point(polygonLine[j].y,polygonLine[j].x);
                }
                flipLineTask.update(flippedPolygonLine, intHolder);
                if (intHolder.value == 0) {
                    break;
                }
                System.out.println(intHolder.value);
            }

            try {
                flipLineTask.next();
            }
            catch (Exception NoMoreTasksException){
                assert NoMoreTasksException instanceof TaskException;
                System.out.println(((TaskException)NoMoreTasksException).message);
            }

        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}
