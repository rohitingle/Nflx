import javax.sound.sampled.Line;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static List<LineObject> batch;
    public static LineObject prevElement;

    public static void main (String[] args) throws IOException {

        final int batchSize = 5;
        Path file = Paths.get("/Users/rohitingle/Documents/NetflixAssignment/testData/input.txt");

        System.out.println("\nBatch Size: " + batchSize);

        try (BufferedReader bfr = Files.newBufferedReader(file)) {
            int lineCount = 0;
            batch = new ArrayList<>();

            for (String line; (line = bfr.readLine()) != null; ) {
                LineObject obj = new LineObject(lineCount++, line);
                batch.add(obj);
                if (batch.size() == batchSize) {
                    if (process())
                        return;
                    // reset your batch
                    batch = new ArrayList<>();
                }
            }
            if (! batch.isEmpty()) {
                if (process())
                    return;
            }
        }

        System.out.println("Could not find a valid Solution: "+ new LineObject(-1, "").toString());
    }

    private static boolean process () {

        System.out.println("\nCurrent Batch: " + batch.toString());
        System.out.println("Prev Ele:" + prevElement);


        if (prevElement != null && prevElement.isGreaterThan(batch.get(0))) {
            System.out.println("Prev Ele:" + prevElement);
            System.out.println("\nFound (prevEle): " + batch.get(0).toString());
            return true;
        } else if (prevElement != null && batch.get(0).isGreaterThan(prevElement) && batch.get(0).isGreaterThan(batch.get(1))) {
            System.out.println("\nFound (prevEle + 1): " + batch.get(1).toString());
            return true;
        }

        prevElement = batch.get(batch.size() - 1);

        int low = 0, high = batch.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println("Low: " + low + ", High: " + high);
            if (isPivot(mid)) {
                System.out.println("\nResult: " + batch.get(mid + 1).toString());
                return true;
            }
            else if (isPivot(mid-1)) {
                System.out.println("\nResult: " + batch.get(mid).toString());
                return true;

            } else if (isPivot(mid+1)){
                System.out.println("\nResult: " + batch.get(mid + 2).toString());
                return true;
            } else {
                    if (mid > 0 && mid < batch.size() - 1
                            && batch.get(mid).isGreaterThan(batch.get(mid - 1))
                            && batch.get(mid + 1).isGreaterThan(batch.get(mid))) {
                        low = mid + 2;
                    } else if (mid > 0 && mid < batch.size() - 1
                            && batch.get(mid - 1).isGreaterThan(batch.get(mid))
                            && batch.get(mid).isGreaterThan(batch.get(mid + 1))) {
                        high = mid - 2;
                    }
                }
            }
        return false;
    }

    //Pivot index is an index where nums[index] > nums[index+1] && nums[index] > nums[index-1]
    private static boolean isPivot (int index){
        System.out.println("Pivot Index: " + index);

        if (index >= batch.size() - 1 || index == 0) {
            System.out.println("False Index");
            return false;
        }
        return batch.get(index).isGreaterThan(batch.get(index + 1)) && batch.get(index).isGreaterThan(batch.get(index-1));
    }
}
