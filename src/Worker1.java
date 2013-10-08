 
// Michael Dorin
// ICS-462
//

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class Worker1 {

        RandomAccessFile vector1MemoryMappedFile;
        RandomAccessFile vector2MemoryMappedFile;
        RandomAccessFile resultMemoryMappedFile;  
        
        MappedByteBuffer io1, io2, io3;


        public void myMain(String _size) throws Exception {
                int vectorSize = 0;

                try {
                        vectorSize = Integer.parseInt(_size);
                }
                catch (Exception e) {
                        System.exit(15);
                }
                try {
                        vector1MemoryMappedFile = new RandomAccessFile("vector1.io", "rw");
                        io1 = vector1MemoryMappedFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, 100*8);
                        vector2MemoryMappedFile = new RandomAccessFile("vector2.io", "rw");
                        io2 = vector2MemoryMappedFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, 100*8);
                        resultMemoryMappedFile = new RandomAccessFile("vector3.io", "rw");
                        io3 = resultMemoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 100*8);
               
                
                } catch (Exception e) {
                       System.out.println(e);
                }
                int[] v1_a = new int[vectorSize];
                int[] v2_a = new int[vectorSize];
                
                for(int i=0;i<vectorSize;i++) {
                	v1_a[i] = io1.getInt(i*4);
                	v2_a[i] = io2.getInt(i*4);
                }
                
                Vector v1 = new Vector(v1_a);
                Vector v2 = new Vector(v2_a);
                Vector result = v1.addVector(v2);
                
                int [] v3_a = result.getVector();
                
                for(int i=0;i<vectorSize;i++) {
                	io3.putInt(i*4, v3_a[i]);
                }
                io1.force();
                io2.force();
                io3.force();
                
                vector1MemoryMappedFile.close();
                vector2MemoryMappedFile.close();
                resultMemoryMappedFile.close();
                System.exit(0);

        }
        public static void main(String args[]) {
                Worker1 worker = new Worker1();

                if (args.length == 0) {
                        System.exit(12);
                }

                if (args.length != 1)
                        System.exit(9);  // my own definition


                try {
                        worker.myMain(args[0]);
                } catch (Exception e) {
                         System.exit(2);
                }
        }
}
