package main;

import entity.*;
import org.hibernate.engine.jdbc.BlobProxy;
import service.*;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        CommentService commentService = new CommentService();
        CustomerService customerService = new CustomerService();
        ExpertService expertService = new ExpertService();
        OrderService orderService = new OrderService();
        ServiceService serviceService = new ServiceService();
        SubServiceService subServiceService = new SubServiceService();
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);
        Experts experts;
        Service service;
        SubService subService;
        Order order;
        Comment comment;
        String description;
        Integer role1;
        Integer role2;
        Integer login;
        Integer menu;
        Integer likeOrNot;
        Boolean loopCustomer = true;
        Long cash;
        Long likes;
        Customer customer;
        Long id;
        String firstname;
        String lastname;
        String email;
        String username;
        String password;
        Status status;
        String signUpTime;
        String image;

        System.out.println("1.Login \n2.signUp ");
        role1 = scanner.nextInt();
        if (role1 == 1) {
            System.out.println("1.Customer \n2.Expert \n3.Admin ");
            login = scanner.nextInt();
            if (login == 1) {
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                customer = customerService.login(username, password);
                if (customer != null) {
                    while (loopCustomer) {
                        System.out.println("1.edit account\n2.delete account \n3.charge wallet \n4.have an order? " +
                                "\n5.add a comment \n6.Exit");
                        menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                System.out.println("enter your email :");
                                email = scanner.nextLine();
                                System.out.println("enter your username:");
                                username = scanner.nextLine();
                                System.out.println("enter your password:");
                                password = scanner.nextLine();
                                customer.setEmail(email);
                                customer.setUsername(username);
                                customer.setPassword(password);
                                customerService.update(customer);
                                break;
                            case 2:
                                customerService.deleteById(customer.getId());
                                break;
                            case 3:
                                System.out.println("enter charge amount:");
                                cash = scanner.nextLong();
                                cash += customer.getWallet().getAmount();
                                customer.getWallet().setAmount(cash);
                                customerService.save(customer);
                                break;
                            case 4:
                                serviceService.findAll().forEach(System.out::println);
                                id = scanner.nextLong();
                                subServiceService.findAllInAService(id).forEach(System.out::println);
                                System.out.println("enter subService id: ");
                                subService = subServiceService.findById(scanner.nextLong());
                                System.out.println("suggested experts:");
                                SubService finalSubService = subService;
                                expertService.expertSuggestions()
                                        .forEach(c -> System.out.println(c.getSubService().equals(finalSubService)));
                                System.out.println("enter expert id: ");
                                id = scanner.nextLong();
                                experts = expertService.findById(id);
                                order = new Order(String.valueOf(LocalDateTime.now()), customer, subService, experts
                                        , OrderStatus.NotFinished);
                                System.out.println("you should pay the price: ");
                                cash = customer.getWallet().getAmount();
                                if (cash > subService.getBasePrice()) {
                                    cash -= subService.getBasePrice();
                                    customer.getWallet().setAmount(cash);
                                    customerService.update(customer);
                                    orderService.save(order);
                                } else System.out.println("not enough money!");
                                break;
                            case 5:
                                System.out.println("enter order id: ");
                                id = scanner.nextLong();
                                order = orderService.findById(id);
                                if (order.getOrderStatus() == OrderStatus.Finished) {
                                    System.out.println("enter your comment: ");
                                    description = scanner.nextLine();
                                    comment = new Comment(null, description, customer, order);
                                    commentService.save(comment);
                                    System.out.println("do you want to like the expert? \n1.Yes  2. No");
                                    likeOrNot = scanner.nextInt();
                                    if (likeOrNot == 1) {
                                        experts = order.getExpert();
                                        likes = experts.getLikes();
                                        likes += 1;
                                        experts.setLikes(likes);
                                        expertService.update(experts);
                                    }
                                } else System.out.println("order is not finished yet! ");
                                break;
                            case 6:
                                loopCustomer = false;
                                break;
                        }
                    }
                }
            } else if (login == 2) {
            } else if (login == 3) {
            } else System.out.println("wrong number entered! ");

        } else if (role1 == 2) {
            System.out.println("what are you ? \n1.customer    2.expert");
            role2 = scanner.nextInt();
            if (role2 == 1) {
                System.out.println("enter your first name:");
                firstname = scanner.nextLine();
                System.out.println("enter your last name:");
                lastname = scanner.nextLine();
                System.out.println("enter your email :");
                email = scanner.nextLine();
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                System.out.println("enter amount you want to put in your wallet:");
                cash = scanner.nextLong();
                customer = new Customer(firstname, lastname, email, username, password, Status.NEW
                        , String.valueOf(LocalDateTime.now()), new Wallet(null, cash), new ArrayList<>());
                customerService.save(customer);
            } else if (role2 == 2) {
                System.out.println("enter your first name:");
                firstname = scanner.nextLine();
                System.out.println("enter your last name:");
                lastname = scanner.nextLine();
                System.out.println("enter your email :");
                email = scanner.nextLine();
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                System.out.println("enter image link:");
                image = scanner.nextLine();
                System.out.println("enter amount you want to put in your wallet:");
                cash = scanner.nextLong();
                experts = new Experts(firstname, lastname, email, username, password, Status.NEW
                        , String.valueOf(LocalDateTime.now()), 0L, BlobProxy.generateProxy(expertService.getImage(image))
                        , new ArrayList<>(), new Wallet(null, cash));
                expertService.save(experts);
            } else System.out.println("wrong number! ");
        }


//        System.out.println("***************************************************");


//        try {
//            expertService.save(new Experts("b", "b", "@@", "b", "Yousef78$0"
//                    , Status.NEW, String.valueOf(LocalDateTime.now()), 0l, null, null,
//                    BlobProxy.generateProxy(expertRepository.getImage("F:\\Wallpaper\\tiger .jpg"))));
//        } catch (FileIsTooBig e) {
//            System.out.println(e.getMessage());
//        }
    }
}
