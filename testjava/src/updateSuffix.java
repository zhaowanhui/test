import java.io.File;

public class updateSuffix {
    public static void main(String[] args){
        File file = new File("G:\\jobs\\sql");
        File[] files=file.listFiles();
        int i=0;
        for (String s : file.list()) {
            //s = s.replace(".",".sql");
            s += ".sql";
            System.out.println(s);
            files[i].renameTo(new File("G:\\jobs\\sql\\"+s));
            i++;
        }
    }
}
