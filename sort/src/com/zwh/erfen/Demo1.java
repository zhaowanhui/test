package com.zwh.erfen;

public class Demo1 {
    public static int binSearch(int srcArray[], int key) {
        int mid;
        int start = 0;
        int end = srcArray.length - 1;
        while (start <= end) {
            mid = (end - start) / 2 + start;
            if (key < srcArray[mid]) {
                end = mid - 1;
            } else if (key > srcArray[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args){

    /*    int[] arr= {1,2,3,4,5,6,7,8,9,10};
        int i = binSearch(arr, 5);
        System.out.println(i);

        for(int q=0;q<arr.length-1;q++){
            for (int m=q;m<arr.length;m++){
                if((arr[q]+arr[m])==7){
                    System.out.println("arr["+q+"]="+arr[q]+"和arr["+m+"]="+arr[m]);
                }
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        for(int m=0;m<arr.length;m++){
            if(map.containsKey(7-arr[m])){
                System.out.println("arr["+(7-arr[m])+"],"+"arr["+m+"]");
            }
            map.put(arr[m],7-arr[m]);
        }*/
        String str= "asdf[159]sdfa[26]asdf[8x]asdf[55[9]";
            int start = 0;
            int end =0;
            int substring =0;
            for(int m = 0;m<str.length();m++){
                if(str.charAt(m)=='['){
                    start = m+1;
                }
                if(str.charAt(m)==']'){
                    end = m;
                    try {
                        substring=Integer.parseInt(str.substring(start,end));
                        System.out.println(substring+10);
                    }catch (Exception e){}
                    //System.out.println(substring+10);
                }
        }
    }
}
