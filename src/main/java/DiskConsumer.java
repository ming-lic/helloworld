import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class DiskConsumer {
    private static final Integer LENGTH= 1024*1024*100;


    public static void main(String[] args) throws IOException {
        int loop = 10000;
        String folder = "E:\\test\\";
        if (args != null &&args.length>0 &&  args[0] !=null && Integer.parseInt(args[0] ) > 0 ){
            loop = Integer.parseInt(args[0]);
            folder = args[1];
        }

        File file = new File(folder);
        if (!file.exists()){
            file.mkdir();
        }
        System.out.println(String.format("Directory=%s, loop =%s MB", folder,  loop*100));

        System.out.println("start consume:"+new Date());

        for (int i = 0; i < loop; i++) {
            long t1 = System.currentTimeMillis();
            String name = folder+"\\fileBlock"+i+".file";
            String content =  new String( new byte[LENGTH]);


            Files.write(Paths.get(name), content.getBytes());
            long t2 = System.currentTimeMillis();
            double sec = (1d*(t2-t1))/1000;
            double mbs =  ((LENGTH)/ (1024*1024)) / sec;
            System.out.println(String.format("Wrote %s MB as %s,  avg write speed is %s MB/S",LENGTH/(1024*1024), name ,mbs ));

        }


        System.out.println(String.format("complete write at %s,",new Date()));

    }


}
