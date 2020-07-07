/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Conexion;
import modelo.modelo;
import modelo.usuario;
import org.apache.commons.dbutils.DbUtils;


public class Login extends javax.swing.JFrame {
    
    Conexion con=new Conexion();
    
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        //se establece el boton defaulta
        SwingUtilities.getRootPane(btnEntrar).setDefaultButton(btnEntrar);
        txtUsuario.requestFocus();
        
        //imagen de icono
        //imagen de icono
//        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("graficos\\icono.png"));
//        setIconImage(icon);
    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(251, 206, 1));
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("CLAVE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 190, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("USUARIO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtClave.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        getContentPane().add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, -1));

        btnEntrar.setBackground(new java.awt.Color(255, 102, 204));
        btnEntrar.setText("ACCEDER");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 140, 50));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, -1, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficos/logo.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        String password=String.valueOf(txtClave.getPassword());
        boolean acceso = false;
        Connection cn=con.conectar();
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try {
            String sql = "SELECT * FROM personal WHERE usuario = ? and clave=? and activo='SI'";
            ps=cn.prepareStatement(sql);
            ps.setString(1,txtUsuario.getText());
            ps.setString(2,password);
            rs = ps.executeQuery();
            while (rs.next()) {
                     if (rs.getString("categoria").equals("ADMINISTRADOR")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                        
                        usuario user= new usuario();
                        user.setUsuario(rs.getString("nombre"));
                        user.setCategoria(rs.getString("categoria"));
                        new EscritorioPrincipal().setVisible(true);
                        this.dispose();
                        acceso = true;
                     }
                     else if (rs.getString("categoria").equals("ENCARGADO")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido Encargado");
                        
                        usuario user= new usuario();
                        user.setUsuario(rs.getString("nombre"));
                        user.setCategoria(rs.getString("categoria"));
                        new EscritorioPrincipal().setVisible(true);
                        this.dispose();
                        acceso = true;
                    }
                     else if (rs.getString("categoria").equals("DESPACHADOR")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido despachador");
                        
                        usuario user= new usuario();
                        user.setUsuario(rs.getString("nombre"));
                        user.setCategoria(rs.getString("categoria"));
                        new EscritorioPrincipal().setVisible(true);
                        this.dispose();
                        acceso = true;
                    }
                     
                
            }if (acceso==false){
            JOptionPane.showMessageDialog(null, "Usuario no reconocido por el sistema");
                txtClave.setText("");
                txtUsuario.requestFocus();
                txtUsuario.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cn);
       
        }


    }//GEN-LAST:event_btnEntrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
