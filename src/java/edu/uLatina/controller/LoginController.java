/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uLatina.controller;

import com.componentes.dao.UsuarioDAO;
import com.componentes.entidades.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kainthel
 */
@ManagedBean (name = "loginController")
@SessionScoped
public class LoginController {
    
    //Parametros para el usuario
    private String username; //Estos se consiguen directamente del login page ("index.html")
    private String password;
    private Usuario user = null;

    public LoginController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    

    public void login(){
        
        
        if (this.username.equalsIgnoreCase("") || this.password.equalsIgnoreCase("")) {
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe llenar todos los campos.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }else{
         
        UsuarioDAO uDao = new UsuarioDAO();
        
        //Busca si el username y password esta en la database
                
        this.user = (Usuario)uDao.login(this.username, this.password);
                
        if (user != null){
                    
            this.redireccionALandingPage(user); //si es valido el user lo manda a meterse al landing page
            
        }else{
            //en caso de login fallido, usuario invalido, etc...
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.password = "";
            this.username = "";
            this.user = null;
        }
      }
        
    }
    
     public void redireccionALandingPage(Usuario u){
                    try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/LandingPage.xhtml?faces-redirect=true");  //Aqui deberia ir a la Landing Page pero pues aun no existe
            }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
           
     }
     
     public void redireccionARegistro(){
                    try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/Registro.xhtml?faces-redirect=true"); 
            }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
           
     }     
    
}