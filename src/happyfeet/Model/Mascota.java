/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happyfeet.Model;

/**
 *
 * @author Prog. Junior Daniel
 */
public class Mascota {
 private int id;
    private int duenoId;
    private String nombre;
    private int razaId;
    private String fechaNacimiento; // Puedes usar LocalDate si prefieres
    private String sexo; // "Macho" o "Hembra"
    private String urlFoto;

    // Constructor vacío
    public Mascota() {}

    // Constructor completo
    public Mascota(int id, int duenoId, String nombre, int razaId, String fechaNacimiento, String sexo, String urlFoto) {
        this.id = id;
        this.duenoId = duenoId;
        this.nombre = nombre;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.urlFoto = urlFoto;
    }
    
    @Override
    public String toString() {
        return nombre + " - RazaID: " + razaId + " - Fecha Nac.: " + fechaNacimiento + " - Sexo: " + sexo;
    }


    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDuenoId() { return duenoId; }
    public void setDuenoId(int duenoId) { this.duenoId = duenoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getRazaId() { return razaId; }
    public void setRazaId(int razaId) { this.razaId = razaId; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
}

