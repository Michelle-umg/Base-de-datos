/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial_progra;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Michelle Juarez
 */
public class CPersonas {
   
    int Codigo;
    String Nombre;
    String Apellido;
    String Sexo;
    String Edad;
    String Fecha;
    
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }
    
    
    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }
    
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    
    
    public void MostrarPersonas (JTable paramTablaTotalPersonas){
    
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        
        modelo.addColumn("Id");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido");
        modelo.addColumn("sexo");
        modelo.addColumn ("edad");
        modelo.addColumn ("fecha");
    
        
        paramTablaTotalPersonas.setModel(modelo);
        
        sql= "select * from Personas";
        
        String[] datos = new String [6];
        
        Statement st;
        
        try{
        
        st = objetoConexion.establecerConexion().createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()){
        
        datos [0] = rs.getString(1);
        datos [1] = rs.getString(2);
        datos [2] = rs.getString(3);
        datos [3] = rs.getString(4);
        datos [4] = rs.getString(5);
        datos [5] = rs.getString(6);
       
        modelo.addRow(datos);
        
        
        }
        
        paramTablaTotalPersonas.setModel(modelo);
        
        }catch (Exception e){
        
             JOptionPane.showMessageDialog(null, "Error: " +e.toString());
        
        }
    
    }
    
    public void insertar (JTextField paramNombre, JTextField paramApellido, JTextField paramSexo, JTextField paramEdad, JTextField paramFecha){
   
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setSexo(paramSexo.getText());
        setEdad(paramEdad.getText());
        setFecha(paramFecha.getText());
        
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into Personas (nombre, apellido, sexo, edad, fecha ) values (?,?,?,?,?);";
        
        try{
        
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getSexo());
            cs.setString(4, getEdad());
            cs.setString(5, getFecha());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente ");
            
            
        
        }catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        
        }
    
    }
    
    public void seleccionar (JTable paramTablaPersonas, JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido, JTextField paramSexo,  JTextField paramEdad, JTextField paramFecha){
    
        try{
        
            int fila = paramTablaPersonas.getSelectedRow();
            
            if (fila>=0){
            
                paramCodigo.setText(paramTablaPersonas.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaPersonas.getValueAt(fila, 1).toString());
                paramApellido.setText(paramTablaPersonas.getValueAt(fila, 2).toString());
                paramSexo.setText(paramTablaPersonas.getValueAt(fila, 3).toString());
                paramEdad.setText(paramTablaPersonas.getValueAt(fila, 4).toString());
                paramFecha.setText(paramTablaPersonas.getValueAt(fila, 5).toString());
                
            
            }else{
            
                JOptionPane.showMessageDialog(null, "Fila no seleccionada o vacía");
            
            }
        
        }catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: "+ e);
        
        }
    
    }
    
    public void modificar (JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido, JTextField paramSexo,JTextField paramEdad, JTextField paramFecha ){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setSexo(paramSexo.getText());
        setEdad(paramEdad.getText());
        setFecha(paramFecha.getText());
        
        
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Personas SET nombre = ?, apellido = ?, sexo= ?, edad = ?, fecha= ? WHERE Personas.id= ?;";
        
        try{
        
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getSexo());
            cs.setString(4, getEdad());
            cs.setString(5, getFecha());
            cs.setInt(6, getCodigo());
           
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modifico correctamente");
        
        }catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        
        }
    
    }
    public void eliminar (JTextField paramCodigo){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
               
        CConexion objetoConexion = new CConexion();
        
        
        String consulta = "DELETE FROM Personas WHERE Personas.id= ?";
        
        try{
        
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setInt(1, getCodigo());
            cs.execute();
        
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            
        }catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        
        }
    
    }

  
    
    
}
