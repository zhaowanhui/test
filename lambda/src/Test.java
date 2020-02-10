import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args){
        String[] strings1 = {"Ni", "Hao", "Lambda"};
        List<String> strings = Arrays.asList(strings1);
        int i =0;
        for (String string : strings1) {
            strings1[i]=string.toLowerCase();
            i++;
        }
        strings.forEach(s -> System.out.println(s));
        strings.stream().map(str -> str.toLowerCase()).collect(Collectors.toList()).forEach(s -> System.out.println(s));
    }
    public static List getd(){
        return new ArrayList();
    }
}
