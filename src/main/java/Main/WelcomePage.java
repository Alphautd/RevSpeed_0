package Main;

import Entities.ServicePlan;
import Entities.User;
import Services.UserService;

import javax.swing.text.html.parser.Parser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static Main.Utilities.*;

public class WelcomePage {
    public static void main(String[] args) {
        boolean action=true;
        do {
            int user_choice = 0;
            Scanner sc = new Scanner(System.in);
            try {
                UserService userService = new UserService();
                User user = new User();
                System.out.println("\t\t\t\t\t\t\t\t\tWelcome to RevSpeed");
                System.out.println("Enter your choice");
                System.out.println("1.Create a new account\n2.Login\n3.Exit");
                user_choice = sc.nextInt();
                switch (user_choice) {
                    case 1:
                        System.out.println("\t\t\t\t\t\t\t\t\tTo create a new account");
                        //user name validation
                        boolean UserAction = true;
                        do {
                            System.out.println("Enter your User Name: [Note:Your name must contain atleast 5 character]");
                            String userName = sc.next();
                            boolean userNameAction = isValidUserName(userName);
                            if (userNameAction) {
                                user.setUsername(userName);
                                UserAction = false;
                            } else {
                                System.out.println("Your user name doesn't contain the required length....try again!");
                            }
                        } while (UserAction);
                        //address
                        System.out.println("Enter your Address:");
                        String address=sc.nextLine();
                        sc.next();
                        user.setAddress(address);

                        //phone number validation
                        boolean phoneAction = true;
                        do {
                            System.out.println("Enter your Phone_Number");
                            String phone = sc.next();
                            boolean userPhoneNumber = isValidUserNumber(phone);
                            if (userPhoneNumber) {
                                user.setPhone(Long.parseLong(phone));
                                phoneAction = false;
                            } else {
                                System.out.println("Your user phone Number is not valid....try again!");
                            }
                        } while (phoneAction);

                        //email validation
                        boolean emailAction = true;
                        do {
                            System.out.println("Enter your Email address");
                            String email = sc.next();
                            boolean userEmail = isValidEmail(email);
                            if (userEmail) {
                                user.setEmail(email);
                                emailAction = false;
                            } else {
                                System.out.println("Your user email is not valid....try again!");
                            }
                        } while (emailAction);

                        //password validation
                        String password = "";
                        String re_password = "";
                        boolean password_action = true;
                        boolean rePassword_action = true;
                        do {
                            System.out.println("Enter your Password :[Note your password must contain atleast 8 characters of atleast one upperCase,atleast one lowerCase and atleast one numeric]");
                            password = sc.next();
                            boolean PasswordAction = isValidPassword(password);
                            if (PasswordAction) {
                                do {
                                    System.out.println("ReEnter your password");
                                    re_password = sc.next();
                                    if (password.equals(re_password)) {
                                        user.setPassword(password);
                                        password_action = false;
                                        rePassword_action = false;
                                    } else {
                                        System.out.println("Password doesn't match...try again!");
                                    }
                                }while (rePassword_action);
                            }
                            else {
                                System.out.println("Your user password is not valid....try again!");
                            }
                        } while (password_action);


                        User Current_obj = userService.createUser(user);
                        if (Current_obj != null) {
                            System.out.println("Account created successfully");
                        } else {
                            System.out.println("An Error occurred during the account creation....try again!");
                        }
                        break;
                    case 2:
                        boolean login_action=true;
                        do{
                            System.out.println("Enter your registered email address");
                            String email_check=sc.next();
                            System.out.println("Enter your password");
                            String password_check=sc.next();
                            boolean valid_user=userService.loginUser(email_check,password_check);
                            if(valid_user==true){
                                System.out.println("Login successfull");
                                do {
                                    //user logics
                                    System.out.println("Choose your choice");
                                    System.out.println("1.view my profile\n2.update my profile\n3.show my plans\n4.delete my plans");
                                    int userService_choice=sc.nextInt();
                                    switch (userService_choice){
                                        case 1:{
                                            User current_user=userService.getUserByEmail(email_check);
                                            if(current_user!=null){
                                                System.out.println("User Profile:");
                                                System.out.println("┌─────────────────┬─────────────────────┐");
                                                System.out.println("│   Attribute     │        Value        │");
                                                System.out.println("├─────────────────┼─────────────────────┤");
                                                System.out.println("│ Name            │ " + current_user.getUsername());
                                                System.out.println("│ Address         │ " + current_user.getAddress());
                                                System.out.println("│ Phone           │ " + current_user.getPhone());
                                                System.out.println("│ Email           │ " + current_user.getEmail());
                                                // Add more attributes as needed
                                                System.out.println("└─────────────────┴─────────────────────┘");
                                            }else{
                                                System.out.println("User doen't exists");
                                            }
                                            break;}
                                        case 2:{
                                            User current_user=new User();
                                            boolean updateAction=false;
                                            do{System.out.println("Enter your choice");
                                                System.out.println("1.Update user name\n2.Update Address\n3.Update PhoneNumber");
                                                int userUpdateChoice=sc.nextInt();
                                                switch (userUpdateChoice){
                                                    case 1:
                                                        boolean doActionN=true;
                                                        do{
                                                            System.out.println("Enter the name to be updated");
                                                            String updatename=sc.next();
                                                            user.setUsername(updatename);
                                                            boolean updateNameAction=userService.updateUserName(user);
                                                            if(updateNameAction){
                                                                System.out.println("Updated successfully");
                                                                doActionN=false;
                                                            }else {
                                                                System.out.println("Something went wrong......try again...");
                                                            }

                                                        }while(doActionN);
                                                        break;
                                                    case 2:
                                                        boolean doActionA=true;
                                                        do{
                                                            System.out.println("Enter the address to be updated");
                                                            String updateAddress=sc.next();
                                                            user.setUsername(updateAddress);
                                                            boolean updateAddressAction=userService.updateUserAddress(user);
                                                            if(updateAddressAction){
                                                                System.out.println("Updated successfully");
                                                                doActionA=false;
                                                            }else {
                                                                System.out.println("Something went wrong......try again...");
                                                            }
                                                        }while (doActionA);
                                                        break;

                                                    case 3:
                                                        boolean doActionP=true;
                                                        do{
                                                            System.out.println("Enter the phone_Number to be updated");
                                                            String updatePhone=sc.next();
                                                            user.setUsername(updatePhone);
                                                            boolean updatePhoneAction=userService.updateUserPhoneNumber(user);
                                                            if(updatePhoneAction){
                                                                System.out.println("Updated successfully");
                                                                doActionP=false;
                                                            }else {
                                                                System.out.println("Something went wrong......try again...");
                                                            }
                                                        }while (doActionP);
                                                        break;
                                                    case 4:
                                                        System.out.println("Your choice doesn't match our requirement....try again..!");
                                                        break;
                                                }
                                            }while (updateAction);


                                            break;}

                                        case 3:{//show plan
                                            boolean showAction=true;
                                            do{
                                                Services.ServicePlan servicePlan=new Services.ServicePlan();
                                                System.out.println("Yours plans");
                                                List<Entities.ServicePlan> al= servicePlan.getAllServicePlans();
                                                int increment=0;
                                                for (ServicePlan servicePlanList : al) {
                                                    System.out.println(++increment+". Plan Name: " + servicePlanList.getPlanName());
                                                    System.out.println("Plan Price:"+servicePlanList.getPlanPrice());
                                                    System.out.println("Plan Duration:"+servicePlanList.getPlanDuration());
                                                    System.out.println("Plan Data and Speed:"+servicePlanList.getPlanData_planSpeed());
                                                    System.out.println("===============================================================");
                                                }
                                                System.out.println("Choose the plan");
                                                String plan=sc.next();

                                                showAction=false;
                                            }while(showAction);


                                            break;
                                        }
                                        case 4:{break;}
                                    }
                                }while(true);
                                //login_action=false;
                            }else{
                                System.out.println("Your credentials doesn't match our records....try again!");
                            }
                        }while(login_action);

                        break;
                    case 3:
                        System.out.println("Thank you for your time....visit again");
                        System.exit(0);
                        break;
                    default:
                        action = false;
                        throw new NoSuchElementException();
                }
            } catch (NoSuchElementException e) {
                System.out.println("\"Unexpected value: \" " + user_choice);
                main(args);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while (action);
    }
}
