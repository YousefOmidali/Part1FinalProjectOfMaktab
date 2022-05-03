package repository;

import entity.Admin;
import entity.Experts;
import entity.Status;
import exceptions.FileIsTooBig;
import org.hibernate.engine.jdbc.BlobProxy;

import java.nio.file.Path;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        AdminRepository adminRepository = new AdminRepository();
        ExpertRepository expertRepository = new ExpertRepository();
        adminRepository.save(new Admin("a", "a", "@", "a", "Yousef78$"
                , Status.NEW, String.valueOf(LocalDateTime.now())));
        adminRepository.gridSearch("a", "", "", "").forEach(System.out::println);
        System.out.println("***************************************************");
        try {
            expertRepository.save(new Experts("b", "b", "@@", "b", "Yousef78$0"
                    , Status.NEW, String.valueOf(LocalDateTime.now()), 0l, null, null,
                    BlobProxy.generateProxy(expertRepository.getImage("F:\\Wallpaper\\tiger .jpg"))));
        } catch (FileIsTooBig e) {
            System.out.println(e.getMessage());
        }
    }
}
