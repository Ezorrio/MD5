package io.ezorrio.md5.crypto;

public class MD5Repo {
    private static String sum;

    public static boolean isSumValid(String sum){
        return MD5Repo.sum.equals(sum);
    }

    public static void setSum(String textSum) {
        MD5Repo.sum = textSum;
    }

    public static void clear(){
        sum = null;
    }
}
