package inventory.system.utils;

import inventory.system.entity.LoggedUser;

import javax.servlet.http.HttpSession;

public class Session {
    public static boolean isLogin(LoggedUser logged_user, HttpSession httpsession){
        return httpsession.getAttribute("logged_user") != null && logged_user.getId() != null;
    }
}
