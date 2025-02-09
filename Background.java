import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Background extends JLabel
{
    private ImageIcon background;
    
    public Background()
    {
        background = new ImageIcon(getClass().getResource(("background.png")));
        
        
        Image bg = background.getImage();
        Image resizedBG = bg.getScaledInstance(700,700, Image.SCALE_SMOOTH);
        ImageIcon resizedBgIcon = new ImageIcon(resizedBG);
        
        
        setIcon(resizedBgIcon);
        setSize(resizedBgIcon.getIconWidth(), resizedBgIcon.getIconHeight());
        
    }
}