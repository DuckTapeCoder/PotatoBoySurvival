import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Player extends JLabel
{
    private ImageIcon playerIcon;
    
    public Player()
    {
        playerIcon = new ImageIcon(getClass().getResource(("potatoBoy.png")));
        setIcon(playerIcon);
        setSize(playerIcon.getIconWidth(), playerIcon.getIconHeight());
    }
}