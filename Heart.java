import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Heart extends JLabel
{
    private ImageIcon heartIcon;
    
    public Heart()
    {
        heartIcon = new ImageIcon(getClass().getResource(("heart.png")));
        setIcon(heartIcon);
        setSize(heartIcon.getIconWidth(), heartIcon.getIconHeight());
    }
}