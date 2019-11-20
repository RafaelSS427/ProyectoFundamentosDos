/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

/**
 *
 * @author HP
 */
public class Empleado {

    private String codigo;
    private String nombre;
    private String apell_uno;
    private String apell_dos;
    private String cedula;
    private String telefono;
    private String correo;
    private int rol;
    private int estado;
    private int salario;
    
    //Los constructores se utilizarán según lo que se necesite, pueden crear otro
    //si lo ven necesario

    //Contructor para agregar a un empleado
    public Empleado(String codigo, String nombre, String apell_uno, String apell_dos, String cedula, String telefono, String correo, int rol, int estado, int salario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apell_uno = apell_uno;
        this.apell_dos = apell_dos;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
        this.salario = salario;
    }
    
    //contructor para eliminar o actualizar a un empleado
    public Empleado(String codigo) {
        this.codigo = codigo;
    }
    
    //Contructor para actualizar un empleado(recuperar información según se necesite)
    public Empleado(String codigo, String nombre, String apell_uno, String apell_dos, String cedula, String telefono, String correo, int rol, int estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apell_uno = apell_uno;
        this.apell_dos = apell_dos;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.estado = estado;
    }
    
    //Contructor vacío
    public Empleado(){
        
    }

    public String getCodigo() {
        return codigo;
    }

    //Métodos get y set
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApell_uno() {
        return apell_uno;
    }

    public void setApell_uno(String apell_uno) {
        this.apell_uno = apell_uno;
    }

    public String getApell_dos() {
        return apell_dos;
    }

    public void setApell_dos(String apell_dos) {
        this.apell_dos = apell_dos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}



