package com.uv.jsfhibernate.beans;

import com.uv.jsfhibernate.entity.Empleado;
import com.uv.jsfhibernate.model.EmpleadoModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private Empleado empleado = new Empleado();
    private EmpleadoModel empleadoModel = new EmpleadoModel();
    List<Empleado> empleados = new ArrayList<>();

    /**
     * Creates a new instance of EmpleadosBean
     */
    public EmpleadoBean() {
        obtenerEmpleados();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleados() {
        return empleados = empleadoModel.findAll();
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void guardar() {
        if (empleadoModel.create(empleado)) {
            addMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado", empleado.getNombre());
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido guardar", empleado.getNombre());
        }

    }

    public void obtenerEmpleados() {
        this.empleados = empleadoModel.findAll();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
