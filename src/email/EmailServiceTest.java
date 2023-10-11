package email;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EmailServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Email> emailList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            emailList.add(new Email("v.shtronda@gmail.com", "hello v.shtronda@gmail.com" + i));
        }

        EmailServiceImpl emailService = new EmailServiceImpl();
        List<Future<String>> res = new ArrayList<>();
        for (Email e: emailList){
            res.add(emailService.sendEmail(e));

        }


        while(true) {
            long count = res.stream().filter(f -> f.isDone()).count();

            if (count == res.size()){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!");

                for (Future<String> f: res) {
                    System.out.println(f.get());
            }
                System.exit(0);
        }


        }
    }
}
