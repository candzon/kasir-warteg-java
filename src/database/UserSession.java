/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author candzon
 */
public class UserSession {
    private static String name;
    private static int idRole;
    private static String username;

    public static void setName(String name) {
        UserSession.name = name;
    }

    public static String getName() {
        return name;
    }
    
    public static void setUsername(String username){
        UserSession.username = username;
    }
    
    public static String getUsername(){
     return username;
    }
    public static void setIdRole(int idRole) {
        UserSession.idRole = idRole;
    }

    public static int getIdRole() {
        return idRole;
    }

    public static void clearSession() {
        name = null;
        idRole = 0;
    }
}
