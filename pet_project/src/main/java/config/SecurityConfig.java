package config;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityConfig {
    private Map<User.Role,List<String>> protectedURLs;
    public static final SecurityConfig INSTANCE = new SecurityConfig();

    private SecurityConfig(){
            List<String> listAdminsPages = new ArrayList<>();
                listAdminsPages.add("/jsp/admin/adminUsers.jsp");
                listAdminsPages.add("/jsp/admin/createCar.jsp");
                listAdminsPages.add("/jsp/admin/editCar.jsp");
                listAdminsPages.add("/jsp/cars.jsp");
                listAdminsPages.add("/jsp/home.jsp");
                listAdminsPages.add("/jsp/login.jsp");
            List<String> listManagersPages = new ArrayList<>();
                listManagersPages.add("/jsp/manager/createCrash.jsp");
                listManagersPages.add("/jsp/manager/createReject.jsp");
                listManagersPages.add("/jsp/userOrders.jsp");
                listManagersPages.add("/jsp/home.jsp");
                listManagersPages.add("/jsp/login.jsp");
            List<String> listClientsPages = new ArrayList<>();
                listClientsPages.add("/jsp/client/createOrder.jsp");
                listClientsPages.add("/jsp/client/damage.jsp");
                listClientsPages.add("/jsp/client/reject.jsp");
                listClientsPages.add("/jsp/cars.jsp");
                listClientsPages.add("/jsp/home.jsp");
                listClientsPages.add("/jsp/userOrders.jsp");
                listClientsPages.add("/jsp/login.jsp");
            List<String> listNullUserPages = new ArrayList<>();
                listNullUserPages.add("/jsp/login.jsp");
                listNullUserPages.add("/jsp/registration.jsp");

                protectedURLs = new HashMap<>();
                protectedURLs.put(User.Role.ADMIN,listAdminsPages);
                protectedURLs.put(User.Role.MANAGER,listManagersPages);
                protectedURLs.put(User.Role.CLIENT,listClientsPages);
                protectedURLs.put(null,listNullUserPages);
        }
        public static SecurityConfig getInstance(){
        return INSTANCE;
        }
        public Map<User.Role,List<String>> getProtectedURLs(){
            return this.protectedURLs;
        }
}
