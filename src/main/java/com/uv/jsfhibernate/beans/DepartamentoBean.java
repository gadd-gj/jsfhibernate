package com.uv.jsfhibernate.beans;

import com.uv.jsfhibernate.entity.Departamento;
import com.uv.jsfhibernate.model.DepartamentoModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    private Departamento departamento;
    private DepartamentoModel departamentoModel;
    List<Departamento> departamentos = new ArrayList<>();

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new Departamento();
        departamentoModel = new DepartamentoModel();
        obtenerDepartamentos();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos = departamentoModel.findAldd();
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public void guardar() {
        if (departamentoModel.create(departamento)) {
            addMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado", departamento.getNombre());
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido guardar", departamento.getNombre());

        }
    }

    public void obtenerDepartamentos() {
        this.departamentos = departamentoModel.findAldd();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
