/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package curvabezier;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author anacamargos
 */
public class Tela extends javax.swing.JFrame {
    
    //int x, y;
    int cliques = 0;
    boolean isBezier = true;
    ArrayList<Ponto> pontos;

    /** Creates new form Tela */
    public Tela() {
        initComponents();
        pontos = new ArrayList<Ponto>();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        area = new PanelDesenho();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                areaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );

        jMenu1.setText("Curva Bezier");

        jMenuItem1.setText("Desenhar Pontos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Limpar");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Limpar Tela");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaMousePressed
        // TODO add your handling code here:
        System.out.println("To no mouse pressed");
        if(isBezier && cliques < 4) {
            int x = evt.getX();
            int y = evt.getY();
            Ponto novoPonto = new Ponto (x,y);
            pontos.add(novoPonto);
            
            if(cliques == 1) {
                //System.out.println("Numero de pontos é: " + pontos.size());
            
                dda(x,y, pontos.get(0).getX(), pontos.get(0).getY(), area.getBuffer(),Color.BLACK.getRGB());
            } else if (cliques == 2) {
                
                //int x2 = pontos.get(0).getX();
                //int y2 = pontos.get(0).getY();
                dda(x,y, pontos.get(1).getX(), pontos.get(1).getY(), area.getBuffer(),Color.BLACK.getRGB());
                
            } else if (cliques == 3) {
                dda(x,y, pontos.get(2).getX(), pontos.get(2).getY(), area.getBuffer(),Color.BLACK.getRGB());
            }
          
            
            //Graphics2D graphics = area.getGraphic();
            //graphics.setColor(Color.RED);
            //graphics.fillOval((int) novoPonto.getX(), (int) novoPonto.getY(), 5, 5);
            //area.desenhaPontos(pontos);
            area.atualizar();
            
            cliques ++;
        }
        if ( isBezier && cliques == 4) {
            System.out.println("Vou chamar Bezier");
            
            int x = evt.getX();
            int y = evt.getY();
            Ponto novoPonto = new Ponto (x,y);
            pontos.add(novoPonto);
            
            //dda(x,y, pontos.get(0).getX(), pontos.get(0).getY(), area.getBuffer(),Color.BLACK.getRGB());
            
            //chamar bezier
            //area.desenhaPontos(pontos);
            area.atualizar();
            plotBezier(pontos, area.getBuffer());
            area.atualizar();
            cliques = 0;
            
            
        }
        
//        x = evt.getX();
//        y = evt.getY();
//        System.out.println("O ponto x1 é: " + x);
//        System.out.println("O ponto y1 é: " + y);
//        area.colorePonto(x,y);
    }//GEN-LAST:event_areaMousePressed

    
    public void dda(int x1, int y1, int x2, int y2, BufferedImage buffer, int cor) {
            System.out.println("Entrei no dda");
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;

            double x = (double) x1, y = (double) y1;

            // colore o primeiro pixel da reta
            buffer.setRGB((int) Math.round(x), (int) Math.round(y), cor);


            int passos = Math.max(Math.abs(deltaX), Math.abs(deltaY));
            double xIncr = ((double) deltaX) / ((double) passos);
            double yIncr = ((double) deltaY) / ((double) passos);

            for (int k = 1; k <= passos; k++) {
                x += xIncr;
                y += yIncr;

                buffer.setRGB((int) Math.round(x), (int) Math.round(y), cor);
            }
   
    }
    
    
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //BEZIER
        isBezier = true;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
        //LIMPAR TELA 
        isBezier = false;
        area.repaint();
        area.limpa();
        area.atualizar();
        pontos.clear();
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
        isBezier = false;
        area.repaint();
        area.limpa();
        area.atualizar();
        pontos.clear();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }
    
    
    /**
     * Metodo para desenhar a curva de Bezier na tela
     */
    public void plotBezier(ArrayList<Ponto> pontos , BufferedImage buffer) {
        
        Ponto p0 = pontos.get(0);
        Ponto p1 = pontos.get(1);
        Ponto p2 = pontos.get(2);
        Ponto p3 = pontos.get(3);
        
        Ponto r1 = new Ponto((int) (3 * (p1.getX() - p0.getX())), (int) (3 * (p1.getY() - p0.getY())));
        Ponto r2 = new Ponto((int) (3 * (p3.getX() - p2.getX())), (int) (3 * (p3.getY() -  p2.getY())));

        double x0 = p0.getX();
        double y0 = p0.getY();

        double x, y;

        double incr = 0.0001;

        for (double t = 0.0; t <= 1.0; t += incr) {
            
            x = ((-1 * Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1) * p0.getX()
                    + (3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t + 0) * p1.getX()
                    + (-3 * Math.pow(t, 3) + 3 * Math.pow(t, 2) + 0 * t + 0) * p2.getX()
                    + (1 * Math.pow(t, 3) + 0 * Math.pow(t, 2) + 0 * t + 0) * p3.getX());

            y = ((-1 * Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1) * p0.getY()
                    + (3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t + 0) * p1.getY()
                    + (-3 * Math.pow(t, 3) + 3 * Math.pow(t, 2) + 0 * t + 0) * p2.getY()
                    + (1 * Math.pow(t, 3) + 0 * Math.pow(t, 2) + 0 * t + 0) * p3.getY());

            buffer.setRGB((int)x, (int)y, Color.RED.getRGB());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelDesenho area;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables

}
