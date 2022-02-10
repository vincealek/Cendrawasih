import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TesApp {

    private static int SIZE;
    private static JFrame frame;
    private JPanel panel;
    private ArrayList<ArrayList<JButton>> board;

    public TesApp() {
        SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setFrame();
        setPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    private void setFrame() {
        frame = new JFrame("Cendrawasih");
        frame.setSize(SIZE*2/3, SIZE*2/3);
        frame.setMinimumSize(new Dimension(SIZE/3, SIZE/3));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));

        board = new ArrayList<>();
        ImageIcon icon = new ImageIcon("strategy.ico");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(frame.getWidth()/10,
                frame.getHeight()/10, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        for(int i = 0; i < 8; i++)
        {
            ArrayList<JButton> arrayList = new ArrayList<>();
            for(int j = 0; j < 8; j++)
            {
                JButton button = new JButton("");
                button.setBorder(null);
                if((i+j)%2 == 1)  {
                    button.setBackground(Color.ORANGE);
                    button.setForeground(Color.RED);
                }
                else {
                    button.setBackground(Color.green);
                }
                arrayList.add(button);
                panel.add(button);
            }
            board.add(arrayList);
        }
        System.out.println(frame);
        System.out.println(board);
    }
}
