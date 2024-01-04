package Main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Utilities {

        public static boolean isValidUserName(String userName){
            int length=userName.length();
            if(length <5 || length >30){
                return false;
            }
            Pattern pattern=Pattern.compile("^(?=.*[a-zA-Z0-9])[a-zA-Z0-9_]+$");
            Matcher matcher=pattern.matcher(userName);
            return matcher.matches();
        }

    public static boolean isValidUserNumber(String phone){
            if(phone.length()<10){
                return false;
            }
            try{
                Long phone_num=Long.parseLong(phone);
                return true;
            }catch(Exception e){
                return false;
            }
    }

    public static boolean isValidEmail(String email){
            if(email ==null || email.isEmpty()){
                return false;
            }
            Pattern pattern=Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
            Matcher matcher=pattern.matcher(email);
            return matcher.matches();
    }

    public static boolean isValidPassword(String password){
          if (password.length()<7){
              return false;
          }
          boolean hasNum=false ,hasUpp=false,hasLow=false;
          char c;
          for(int i=0;i<password.length();i++){
             c=password.charAt(i);
             if(Character.isDigit(c)){
                 hasNum=true;
             } else if (Character.isUpperCase(c)) {
                 hasUpp=true;
             } else if (Character.isLowerCase(c)){
                 hasLow=true;
             }
             if(hasNum && hasUpp && hasLow){
                 return true;
             }
          }
          return false;
    }


}
