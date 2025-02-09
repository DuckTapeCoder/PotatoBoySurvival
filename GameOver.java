import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GameOver extends JLabel
{
    private ImageIcon gameOver;
    
    public GameOver()
    {
        gameOver = new ImageIcon(getClass().getResource(("gameOver.png")));
        
        
        Image bg = gameOver.getImage();
        Image resizedBG = bg.getScaledInstance(700,700, Image.SCALE_SMOOTH);
        ImageIcon resizedBgIcon = new ImageIcon(resizedBG);
        
        
        setIcon(resizedBgIcon);
        setSize(resizedBgIcon.getIconWidth(), resizedBgIcon.getIconHeight());
        
    }
}