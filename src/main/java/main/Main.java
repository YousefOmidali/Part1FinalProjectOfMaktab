package main;

import entity.*;
import exceptions.FileIsTooBig;
import org.hibernate.engine.jdbc.BlobProxy;
import service.*;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
        Wallet wallet = null;
        Wallet wallet2 = null;
        Experts experts;
        Admin admin = null;
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
        Integer customerOrExpert;
        Boolean loop = true;
        Long cash;
        Long likes;
        Long basePrice;
        Customer customer = null;
        Long id;
        String firstname;
        String lastname;
        String email;
        String username;
        String password;
        ;
        String image;


//        customer = new Customer("a", "a", "@", "a", "Yousef78@", Status.NEW, String.valueOf(LocalDateTime.now())
//                , new Wallet(null, 100L), new ArrayList<>());
//        customerService.save(customer);
        System.out.println("1.Login \n2.signUp ");
        role1 = scanner.nextInt();
        if (role1 == 1) {
            System.out.println("1.Customer \n2.Expert \n3.Admin ");
            login = scanner.nextInt();
            if (login == 1) {
                scanner.nextLine();
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                customer = customerService.login(username, password);
                if (customer.getId() != null) {
                    while (loop) {
                        System.out.println("1.edit account\n2.delete account \n3.charge wallet \n4.have an order? " +
                                "\n5.add a comment \n6.Exit");
                        menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                scanner.nextLine();
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
                                wallet = customer.getWallet();
                                cash += customer.getWallet().getAmount();
                                wallet.setAmount(cash);
                                walletService.update(wallet);
                                break;
                            case 4:
                                serviceService.findAll().forEach(System.out::println);
                                System.out.println("enter service id: ");
                                id = scanner.nextLong();
                                subServiceService.findAllInAService(id).forEach(System.out::println);
                                System.out.println("enter subService id: ");
                                subService = subServiceService.findById(scanner.nextLong());
                                System.out.println("suggested experts:");
                                subService.getExperts().forEach(System.out::println);
                                System.out.println("enter expert id: ");
                                id = scanner.nextLong();
                                experts = expertService.findById(id);
                                order = new Order(String.valueOf(LocalDateTime.now()), customer, subService, experts
                                        , OrderStatus.NotFinished);
                                System.out.println("checking your wallet cash ");
                                cash = customer.getWallet().getAmount();
                                if (cash > subService.getBasePrice()) {
                                    System.out.println("you have enough cash");
                                    cash -= subService.getBasePrice();
                                    wallet = customer.getWallet();
                                    wallet.setAmount(cash);
                                    walletService.update(wallet);
                                    wallet2 = experts.getWallet();
                                    cash = wallet2.getAmount();
                                    cash+= subService.getBasePrice();
                                    wallet2.setAmount(cash);
                                    walletService.update(wallet2);
                                    orderService.save(order);
                                    System.out.println("saved the order");
                                } else System.out.println("not enough money!");
                                break;
                            case 5:
                                System.out.println("enter order id: ");
                                id = scanner.nextLong();
                                order = orderService.findById(id);
                                if (order.getOrderStatus() == OrderStatus.Finished) {
                                    scanner.nextLine();
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
                                loop = false;
                                break;
                        }
                    }
                }
            } else if (login == 2) {
                scanner.nextLine();
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                experts = expertService.login(username, password);
                if (experts.getId() != null) {
                    while (loop) {
                        System.out.println("1.edit account\n2.delete account \n3.add a sub service " +
                                "\n4.show my waiting orders \n5.show my wallet \n6.Exit");
                        menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("enter your email :");
                                email = scanner.nextLine();
                                System.out.println("enter your username:");
                                username = scanner.nextLine();
                                System.out.println("enter your password:");
                                password = scanner.nextLine();
                                experts.setEmail(email);
                                experts.setUsername(username);
                                experts.setPassword(password);
                                expertService.update(experts);
                                break;
                            case 2:
                                expertService.deleteById(customer.getId());
                                break;
                            case 3:
                                serviceService.findAll().forEach(System.out::println);
                                System.out.println("enter id of service: ");
                                id = scanner.nextLong();
                                subServiceService.findAllInAService(id).forEach(System.out::println);
                                System.out.println("enter id of subService: ");
                                id = scanner.nextLong();
                                subService = subServiceService.findById(id);
                                subService.getExperts().add(experts);
//                                experts.getSubService().add(subService);
//                                expertService.update(experts);
                                subServiceService.update(subService);


                                break;
                            case 4:
                                orderService.findAllOfAnExpert(experts.getId()).forEach(System.out::println);
                                break;
                            case 5:
                                System.out.println("You have " + experts.getWallet().getAmount() + "$");
                                break;
                            case 6:
                                loop = false;
                                break;
                        }
                    }
                }
            } else if (login == 3) {
                scanner.nextLine();
                System.out.println("enter your username:");
                username = scanner.nextLine();
                System.out.println("enter your password:");
                password = scanner.nextLine();
                admin = adminService.login(username, password);
                if (admin.getId() != null) {
                    while (loop) {
                        System.out.println("1.edit my account \n2.delete my account \n3.Add a service \n4.add sub service " +
                                "\n5.list of experts \n6.delete an expert \n7.delete a subService from expert" +
                                "\n8.gridSearch \n9.set an order finished tag \n10.Exit ");
                        menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("enter your email :");
                                email = scanner.nextLine();
                                System.out.println("enter your username:");
                                username = scanner.nextLine();
                                System.out.println("enter your password:");
                                password = scanner.nextLine();
                                admin.setEmail(email);
                                admin.setUsername(username);
                                admin.setPassword(password);
                                adminService.update(admin);
                                break;
                            case 2:
                                adminService.deleteById(admin.getId());
                                break;
                            case 3:
                                scanner.nextLine();
                                System.out.println("enter description:");
                                description = scanner.nextLine();
                                serviceService.save(new Service(null, description));
                                break;
                            case 4:
                                scanner.nextLine();
                                System.out.println("enter description:");
                                description = scanner.nextLine();
                                System.out.println("enter service id: ");
                                id = scanner.nextLong();
                                service = serviceService.findById(id);
                                System.out.println("enter basePrice: ");
                                basePrice = scanner.nextLong();
                                subServiceService.save(new SubService(null, description, basePrice
                                        , service, new HashSet<>())); //****************************************************
                                break;
                            case 5:
                                expertService.findAll().forEach(System.out::println);
                                break;
                            case 6:
                                System.out.println("enter expert id: ");
                                id = scanner.nextLong();
                                experts = expertService.findById(id);
                                expertService.deleteById(id);
                                break;
                            case 7:
                                System.out.println("enter expert id: ");
                                id = scanner.nextLong();
                                experts = expertService.findById(id);
                                System.out.println("enter sub service id: ");
                                subService = subServiceService.findById(id);
                                experts.getSubService().remove(subService);
                                break;
                            case 8:
                                scanner.nextLine();
                                System.out.println("enter first name: ");
                                firstname = scanner.nextLine();
                                System.out.println("enter last name: ");
                                lastname = scanner.nextLine();
                                System.out.println("enter email: ");
                                email = scanner.nextLine();
                                System.out.println("enter username: ");
                                username = scanner.nextLine();
                                System.out.println("1.Customer    2.Expert");
                                customerOrExpert = scanner.nextInt();
                                if (customerOrExpert == 1) {
                                    customerService.gridSearch(firstname, lastname, email, username)
                                            .forEach(System.out::println);
                                } else if (customerOrExpert == 2) {
                                    expertService.gridSearch(firstname, lastname, email, username)
                                            .forEach(System.out::println);
                                } else System.out.println("wrong number! ");

                                break;
                            case 9:
                                System.out.println("enter order id: ");
                                id = scanner.nextLong();
                                order = orderService.findById(id);
                                order.setOrderStatus(OrderStatus.Finished);
                                orderService.update(order);
                                break;
                            case 10:
                                loop = false;
                                break;
                        }
                    }
                }
            } else System.out.println("wrong number entered! ");

        } else if (role1 == 2) {
            System.out.println("what are you ? \n1.customer    2.expert    3.Admin");
            role2 = scanner.nextInt();
            if (role2 == 1) {
                scanner.nextLine();
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
                wallet = new Wallet(null, cash);
                walletService.save(wallet);
                customer = new Customer(firstname, lastname, email, username, password, Status.NEW
                        , String.valueOf(LocalDateTime.now()), wallet, new ArrayList<>());
                customerService.save(customer);
            } else if (role2 == 2) {
                scanner.nextLine();
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
                wallet = new Wallet(null, 0L);
                walletService.save(wallet);
                experts = new Experts(firstname, lastname, email, username, password, Status.NEW
                        , String.valueOf(LocalDateTime.now()), 0L, BlobProxy.generateProxy(expertService.getImage(image))
                        , new HashSet<>(), wallet); //******************************************
                try {
                    expertService.save(experts);
                } catch (FileIsTooBig e) {
                    System.out.println(e.getMessage());
                }
            } else if (role2 == 3) {
                scanner.nextLine();
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
                admin = new Admin(firstname, lastname, email, username, password, Status.NEW
                        , String.valueOf(LocalDateTime.now()));
                adminService.save(admin);
            }
        } else System.out.println("wrong number! ");


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
