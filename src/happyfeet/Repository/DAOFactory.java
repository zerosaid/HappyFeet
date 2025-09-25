/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Repository;

/**
 *
 * @author Prog. Junior Daniel
 */
public class DAOFactory {
        public static DuenoDAO getDuenoDAO() {
        return new DuenoDAO();
    }
    
    public static MascotaDAO getMascotaDAO() {
        return new MascotaDAO();
    }
    
    public static EspecieDAO getEspecieDAO() {
        return new EspecieDAO();
    }
    
    public static RazaDAO getRazaDAO() {
        return new RazaDAO();
    }
}
