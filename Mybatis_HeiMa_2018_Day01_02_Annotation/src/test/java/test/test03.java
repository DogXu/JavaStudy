package test;

public class test03 {
    public static void main(String[] args){
        String fc = "2x=x";
        System.out.println(solution(fc));
    }
    public static String solution(String str){
        if(str.length() == 0)
            return "No Solution";
        String[] split = str.split("=");
        int k;
        int[] num = {0,0};
        int[] sum = {0,0};
        for(int j = 0 ; j < 2 ; j++){
            for(int i = 0 ; i < split[j].length(); i++){
                k = i;
                while(split[j].charAt(i) != '+' && split[j].charAt(i) != '-' && i < split[j].length() - 1){
                    i++;
                }
                String s = new String();
                if(k == 0){
                    s = split[j].substring(k,i);
                }else{
                    s = split[j].substring(k-1,i);
                }
                if(s.charAt(s.length()-1) == 'x'){
                    if(s.charAt(0) == '-'){
                        num[j] -= judge(s);
                    }else{
                        num[j] += judge(s);
                    }
                }else{
                    if(s.charAt(0) == '-'){
                        sum[j] -= judge(s);
                    }else{
                        sum[j] += judge(s);
                    }
                }

            }
        }
        int n = num[0] - num[1];
        int m = sum[1] - sum[0];
        if(n == 0 && m == 0){
            return "Infinite Solution";
        }
        if(n == 0){
            return "Nofinite Solution";
        }
        return "x="+ String.valueOf((-m)/n);

    }
    public static int judge(String s){
        int a = 0;
        if(s.charAt(0) == '+' || s.charAt(0) == '-'){
            s = s.substring(1,s.length());
        }
        if(s.length() == 1 && s.charAt(0) == 'x'){
            return 1;
        }
        if(s.length() > 1 && s.charAt(s.length()-1)=='x'){
            s = s.substring(0,s.length()-1);
        }
        for(int i = 0 ; i < s.length() ; i++){
        //    a = a *10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            a = a * 10 + s.charAt(i) - '0';
        }
        return  a;
    }
}
