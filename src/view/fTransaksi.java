/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.MYSQLConnection;
import database.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author candzon
 */
public class fTransaksi extends javax.swing.JFrame {

    private HashMap<String, Integer> namaPaketMap;
    DefaultTableModel model;

    /**
     * Creates new form fMain
     */
    public fTransaksi() {
        initComponents();
        namaPaketMap = new HashMap<>();
        fetchRoles();
        getDataTable();
        disabledButtonSubmit();

//        Fungsi untuk menghitung total harga
        txtJumlah.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                countHargaWithJumlah();
            }
        });
    }

    private void disabledButtonSubmit() {
        // Assuming id_role is stored in a session or accessible globally
        int id_role = UserSession.getIdRole(); // Replace with your method to get id_role

        if (id_role == 3) {
            btnBeli.setEnabled(false); // Disable the submit button
        } else {
            btnBeli.setEnabled(true); // Enable the submit button if not id_role == 3
        }
    }

    private void countHargaWithJumlah() {
        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int jumlah = (int) txtJumlah.getValue();
            double totalHarga = harga * jumlah;
            lblHarga.setText(String.valueOf(totalHarga));
        } catch (NumberFormatException e) {
            lblHarga.setText("0");
        }
    }

    private void getDataTable() {
        model = (DefaultTableModel) tableTransaksi.getModel();
        model.setRowCount(0);

        String query = "SELECT a.*, b.nama_paket, b.harga FROM tb_detailtransaksi a "
                + "JOIN tb_paketmakanan b ON a.id_paket = b.id_paket "
                + "ORDER BY a.id_detail DESC";

        try (Connection conn = MYSQLConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss"); // Format tanggal dalam bahasa Indonesia
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getString("nama_pembeli");
                row[1] = rs.getString("nama_paket");
                row[2] = rs.getDouble("harga");
                row[3] = rs.getInt("jumlah");
                row[4] = rs.getDouble("total_harga");
                java.util.Date createdDate = rs.getTimestamp("created_at");
                row[5] = (createdDate != null) ? dateFormat.format(createdDate) : ""; // Format tanggal created_at
                java.util.Date updatedDate = rs.getTimestamp("updated_at");
                row[6] = (updatedDate != null) ? dateFormat.format(updatedDate) : ""; // Format tanggal updated_at
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void fetchRoles() {
        String query = "SELECT * FROM tb_paketmakanan";

        try (Connection conn = MYSQLConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idPaket = rs.getInt("id_paket");
                String namaPaket = rs.getString("nama_paket");

                // Menambahkan role ke dalam Choice
                txtPaket.add(namaPaket);
                // Opsional: Simpan id_role sebagai nilai terkait dengan role di Choice
                // Di sini Anda dapat menggunakan HashMap atau lainnya untuk memetakan id_role ke role
                // atau menggunakan cara yang sesuai dengan kebutuhan aplikasi Anda.
                // Simpan id_role sebagai nilai terkait dengan role di HashMap
                namaPaketMap.put(namaPaket, idPaket);
                txtHarga.setText(rs.getString("harga"));
                txtHarga.setEditable(false);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method untuk mendapatkan id_role berdasarkan role yang dipilih
    private Integer getNamaPaket(String namaPaket) {
        return namaPaketMap.get(namaPaket);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPaket = new java.awt.Choice();
        jLabel4 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();
        btnBeli = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtPembeli = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnBack = new javax.swing.JMenu();
        mnBackTransaksi = new javax.swing.JMenuItem();
        mnLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(778, 600));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Created by Feri");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Form Transaksi");

        jLabel5.setText("Nama Paket");

        jLabel4.setText("Harga");

        jLabel6.setText("Jumlah");

        jLabel7.setText("Total Harga");

        jLabel8.setText("Rp. ");

        lblHarga.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblHarga.setText("Harga");

        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Pembeli", "Nama Paket", "Harga", "Jumlah", "Total Harga", "Tanggal Beli", "Updated at"
            }
        ));
        jScrollPane1.setViewportView(tableTransaksi);

        btnBeli.setText("Beli");
        btnBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeliActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Pembeli");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPaket, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblHarga)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPembeli))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblHarga))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(102, 255, 102));

        mnBack.setText("Lainnya");

        mnBackTransaksi.setText("Back");
        mnBackTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBackTransaksiActionPerformed(evt);
            }
        });
        mnBack.add(mnBackTransaksi);

        mnLogout.setText("Logout");
        mnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLogoutActionPerformed(evt);
            }
        });
        mnBack.add(mnLogout);

        jMenuBar1.add(mnBack);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLogoutActionPerformed
        // TODO add your handling code here:
        // Tutup form saat ini
        this.dispose();

        //Tampilkan kembali form Login
        fLogin loginFrame = new fLogin();
        loginFrame.setVisible(true);
    }//GEN-LAST:event_mnLogoutActionPerformed

    private void mnBackTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBackTransaksiActionPerformed
        // TODO add your handling code here:
        // Tutup form saat ini
        this.dispose();

        //Tampilkan kembali form Main
        fMain mainFrame = new fMain();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_mnBackTransaksiActionPerformed

    private void btnBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeliActionPerformed
        // TODO add your handling code here:
        // Ambil data dari input pengguna
        String selectedPaket = (String) txtPaket.getSelectedItem();
        String namaPembeli = txtPembeli.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        int jumlah = (int) txtJumlah.getValue();
        double total_harga = harga * jumlah;

        // Dapatkan id_paket berdasarkan nama_paket
        Integer idPaket = getNamaPaket(selectedPaket);
        if (idPaket == null) {
            JOptionPane.showMessageDialog(this, "Paket tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format tanggal
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        // Koneksi ke database dan sisipkan data
        String query = "INSERT INTO tb_detailtransaksi (id_paket, nama_pembeli, harga, jumlah, total_harga, created_at, updated_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection conn = MYSQLConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idPaket);
            pstmt.setString(2, namaPembeli);
            pstmt.setDouble(3, harga);
            pstmt.setInt(4, jumlah);
            pstmt.setDouble(5, total_harga);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.", "Success", JOptionPane.INFORMATION_MESSAGE);
                getDataTable(); // Memperbarui tabel setelah penyimpanan data
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBeliActionPerformed

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
            java.util.logging.Logger.getLogger(fTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JMenu mnBack;
    private javax.swing.JMenuItem mnBackTransaksi;
    private javax.swing.JMenuItem mnLogout;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JSpinner txtJumlah;
    private java.awt.Choice txtPaket;
    private javax.swing.JTextField txtPembeli;
    // End of variables declaration//GEN-END:variables
}
