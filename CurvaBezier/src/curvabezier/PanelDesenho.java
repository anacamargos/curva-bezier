/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvabezier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 *
 * @author anacamargos
 */
public class PanelDesenho extends JPanel {
    
    //private BufferedImage buffer = new BufferedImage(821, 546, BufferedImage.TYPE_INT_RGB);
    private BufferedImage buffer = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics; 
    
    public PanelDesenho(){
        System.out.println("Chamei Panel Desenho");
        limpa();
        repaint();
    }
    
    @Override
    public void paint (Graphics graphic) {
        //Graphics2D graphics = (Graphics2D) graphic;
        graphics = (Graphics2D) graphic;

        // desenha a matriz na tela
        graphics.drawImage(buffer, null, 0, 0);
        
    }
    
    public void limpa(){
        for( int y = 0; y < buffer.getHeight(); y++){
            for( int x = 0; x < buffer.getWidth(); x++){
                buffer.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
    }
    
    public void atualizar(){
        //System.out.println("teste");
        repaint();
    }
    
    public BufferedImage getBuffer(){
        return this.buffer;
    }
    
    public Graphics2D getGraphic(){
        return this.graphics;
    }
    
    /**
     * Metodo para desenhar os pontos da curva interpolada
     *
     * @param graphics
     */
    public void desenhaPontos(ArrayList<Ponto> pontos) {
        for (int i = 0; i < 4; i++) {

            Ponto ponto = pontos.get(i);

            if (ponto.getX() != -1.0 && ponto.getY() != -1.0) {

                // Os pontos de controle de BEZIER vou colocar como vermelho
                //if (this.existeDesenho && this.idCurvaDesenho == BEZIER) {
                    if (i == 1 || i == 2) {
                        this.graphics.setColor(Color.RED);
                    } else {
                        this.graphics.setColor(Color.BLACK);
                    }
                //}// fim if

                this.graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 5, 5);
                
            }
        }
    }
    
    public void colorePonto (int x, int y) {
        System.out.println("To no colore ponto");
        buffer.setRGB(x, y, Color.BLACK.getRGB());
    }
    
}
