import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Enemy extends JLabel
{
    private ImageIcon enemyIcon;
    
    public Enemy()
    {
        enemyIcon = new ImageIcon(getClass().getResource(("carrotMan.png")));
        setIcon(enemyIcon);
        setSize(enemyIcon.getIconWidth(), enemyIcon.getIconHeight());
    }
}