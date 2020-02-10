import com.sun.jmx.snmp.tasks.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Test1 {
    public static void main(String[] args) throws ParseException, IOException, InterruptedException {



        Thread th=new Thread(()->{
            Demo5 demo2= Demo5.getInstance();
            System.out.println(demo2);
        });
        Thread th1=new Thread(()->{
            Demo5 demo21 = Demo5.getInstance();
            System.out.println(demo21);
        });
        th.start();
        th1.start();

        /*Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-06-24 22:31:10"), 10 * 1000);
*/
       /* FileInputStream fis = new FileInputStream("F:\\资料（全部拷贝）\\训练营\\fastDFS\\assets\\1557026198723.png");
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("F:\\资料（全部拷贝）\\a.png");
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        while(true){
            allocate.clear();
            int read = fisChannel.read(allocate);
            if(read==-1){
                break;
            }
            allocate.flip();
            fosChannel.write(allocate);
        }
        fos.close();
        fis.close();*/

       String str1 = new String("hel");
       String str2 = "hello";
       str2.equals(str1);
       String str3 = str1+"lo";
        System.out.println(str2==str3);
        System.out.println(str2);
        System.out.println(str3);


    }

}
