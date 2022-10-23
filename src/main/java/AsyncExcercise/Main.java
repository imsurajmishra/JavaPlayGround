package AsyncExcercise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        User user = new User(1,
                "sam",
                ProductType.CREDIT_CARD,
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                "abc@gmail.com");

        User user1 = new User(1,
                "sam",
                ProductType.CREDIT_CARD,
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                "");

        apply(user);
        apply(user1);

        Thread.sleep(3000);
    }

    // submit userRequest
    private static void apply(User user){
        if(validate()){
            // send thank you mail to the customer
            sendMail(user); // non blocking operation

            // save to database
            savetoDB(user); //
        }

    }

    private static void savetoDB(User user) {
        // jpa persit;
        System.out.println("persisted to db");
    }


    private static void sendMail(User user){
         CompletableFuture.supplyAsync(() -> send(user))
                .exceptionally(throwable -> throwable.getMessage())
                .thenAccept(System.out::println);
    }

    private static String send(User user){
        try {
            Thread.sleep(1000);
        } catch (Exception ex){
            throw  new RuntimeException("Thread Interrupted");
        }
        // mail sender logic
        if(!user.getEmailId().isEmpty()){
            // send email
            return "SUCCESS ! email sent !";
        }
        throw new RuntimeException("FAILED ! email sent failed :(");
    }

    private static boolean validate(){

        // validation
        return true;
    }


}
