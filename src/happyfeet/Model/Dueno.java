/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

/**
 *
 * @author 
 */
public class Dueno {
    private int id;
    private String nombre_Completo;
    private String documento_identidad;
    private String direccion;
    private String telefono;
    private String email;

    // Constructor vacío
    public Dueno() {}

    // Constructor completo
    public Dueno(int id, String nombre_Completo, String documento_identidad, String direccion,
                 String telefono, String email) {
        this.id = id;
        this.nombre_Completo = nombre_Completo;
        this.documento_identidad = documento_identidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCompleto() { return nombre_Completo; }
    public void setNombreCompleto(String nombreCompleto) { this.nombre_Completo = nombreCompleto; }

    public String getDocumentoIdentidad() { return documento_identidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documento_identidad = documentoIdentidad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
