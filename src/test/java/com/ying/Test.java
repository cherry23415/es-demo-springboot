package com.ying;

public class Test {

    public static void main(String[] args) {
        f1(" aa aera aaoooooooa a a a aa aa a aaaasrrss");
    }

    static void f1(String str) {
        int len = 0;
        int temp = 0;
        for (char c : str.toCharArray()) {
            if (c != 32) {
                len++;
            } else {
                len = 0;
            }
            if (len > temp) {
                temp = len;
            }
        }
        System.out.println(temp);
    }

    static void f2(String str) {
        String[] arr = str.split(" ");
        int len;
        int temp = 0;
        for (String a : arr) {
            len = a.length();
            if (len > temp) {
                temp = len;
            }
        }
        System.out.println(temp);
    }

//    static void split(String str, String... listOfTokens) {
//        List<String> results = new ArrayList<>();
//        String[] temp1;
//        String[] temp2;
//        for (int i = 0; i < listOfTokens.length - 1; i++) {
//            temp1 = str.split(listOfTokens[i]);
//            for (String a : temp1) {
//                temp2 = a.split(listOfTokens[i + 1]);
//                results.addAll(Arrays.asList(temp2));
//            }
//        }
//        System.out.println(results);
//    }
//
//    public static void main(String[] args) {
////        split("abc,def.ghi", "ef", "c");
//        String str1 = "hello";
//
//        String str2 = "he" + new String("llo");
//
//        String str3 = "he" + "llo";
//
//        System.err.println(str1 == str2);
//
//        System.err.println(str1 == str3);
//    }


//    public static void test(List a) {
//
//        a = new ArrayList();
//
//        a.add("abc");
//
//    }
}
