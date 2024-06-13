package guis;

import control.ControlChat;
import entidades.Usuarios;

public class FrameChat extends javax.swing.JFrame {
    private Usuarios usuarioLog;
    private ControlChat ctrlChat;
    
    public FrameChat() {
        initComponents();
    }
    
    public void setControlChat(ControlChat ctrChat){
        this.ctrlChat=ctrChat;
    }
    public void setUsuarioLog(Usuarios usuarioLog){
        this.usuarioLog = usuarioLog;
    }
    
    public Usuarios getUsuarioLog(){
    return this.usuarioLog;
    }
    
    public void cargar(){
        this.ctrlChat.cargarContactosChat();
    }
    
    public void borrarContactos(){
        this.ctrlChat.borrarContactos();
    }
    
    public void borrarHistorial(){
    this.ctrlChat.borrarHistorial();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlContactos = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEscribir = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mPerfil = new javax.swing.JMenu();
        mNuevoChat = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jlContactos.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jlContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlContactosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlContactos);

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        txtChat.setLineWrap(true);
        txtChat.setRows(5);
        jScrollPane3.setViewportView(txtChat);

        txtEscribir.setColumns(20);
        txtEscribir.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txtEscribir.setLineWrap(true);
        txtEscribir.setRows(1);
        txtEscribir.setTabSize(2);
        jScrollPane4.setViewportView(txtEscribir);

        btnEnviar.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        mPerfil.setText("Perfil");
        mPerfil.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        mPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mPerfilMouseClicked(evt);
            }
        });
        jMenuBar1.add(mPerfil);

        mNuevoChat.setText("Nuevo chat");
        mNuevoChat.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        mNuevoChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mNuevoChatMouseClicked(evt);
            }
        });
        jMenuBar1.add(mNuevoChat);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mNuevoChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mNuevoChatMouseClicked
        this.ctrlChat.nuevoChat();
    }//GEN-LAST:event_mNuevoChatMouseClicked

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        this.ctrlChat.enviarMensaje();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void jlContactosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlContactosMouseClicked
        this.ctrlChat.cargarHistorialChat();
    }//GEN-LAST:event_jlContactosMouseClicked

    private void mPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mPerfilMouseClicked
        this.ctrlChat.mostrarPerfil();
    }//GEN-LAST:event_mPerfilMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JList<Usuarios> jlContactos;
    private javax.swing.JMenu mNuevoChat;
    private javax.swing.JMenu mPerfil;
    public javax.swing.JTextArea txtChat;
    public javax.swing.JTextArea txtEscribir;
    // End of variables declaration//GEN-END:variables
}
