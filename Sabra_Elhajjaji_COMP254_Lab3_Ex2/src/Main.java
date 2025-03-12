//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String s ="racecarr";
        System.out.println(isPalindrome(s))     ;

    }

    public static boolean isPalindrome(String s){
        if (s.length() <= 1) {
            return true;
        }
        char[] words=s.toCharArray();
        if (words[0]!=words[words.length-1]) return false;
        return isPalindrome(s.substring(1,s.length()-1));

    }
}