import java.security.MessageDigest;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String s2 = "nbasfhdnbajdnbaaanb";
        String s1 = "asdinbajdaab";
        String s;
        s = getMaxSubString(s1, s2);
        System.out.println(s);

    }

    public static String getMaxSubString(String s1, String s2) {
        String max = (s1.length() > s2.length()) ? s1 : s2;
        String min = (max.equals(s1)) ? s2 : s1;       //保持s1是大字符串，s2是小字符串
        if (max.contains(min)) {                            //如果s2本身就是两者的最大子串，则返回s2本身
            return min;
        }
        for (int i = 0; i < min.length(); i++) {                                                                   //进行s2.length()次循环匹配查找，
            for (int a = 0, b = min.length() - i; b != min.length() + 1; a++, b++) {
                //每次查找，都是从小字符串的起始位置查找，
                //a，b代表的是每次查找时的从小字符串中截取的子串的起始位置和末尾位置+1，
                //每次查找将小字符串s2进行取子串，然后再将该子串与s1匹配，看看是s1否包含该子串。
                String sub = min.substring(a, b);
                if (max.contains(sub)) {
                    return sub;
                }
            }
        }
        return null;
    }
}

