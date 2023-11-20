import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MastermindGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private List<String> codeColors;
    private List<List<String>> feedbackColors;

    private static final int CIRCLE_SIZE = 30;

    private static final Map<String, Color> COLOR_MAP = new HashMap<>();
    static {
        COLOR_MAP.put("RO", Color.RED);
        COLOR_MAP.put("NA", Color.BLUE);
        COLOR_MAP.put("AZ", Color.GREEN);
        COLOR_MAP.put("NE", Color.BLACK);
        COLOR_MAP.put("VE", Color.MAGENTA);
        COLOR_MAP.put("AM", Color.YELLOW);
        COLOR_MAP.put("MO", Color.ORANGE);
        COLOR_MAP.put("BL", Color.WHITE);
    }

    public MastermindGUI() {
        frame = new JFrame("Mastermind");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(0, 1));
        frame.add(boardPanel);

        codeColors = new ArrayList<>();
        feedbackColors = new ArrayList<>();

        frame.setVisible(true);
    }

    public void updateBoard(String code, String feedback) {
        parseCodeColors(code);
        parseFeedbackColors(feedback);

        JPanel roundPanel = new JPanel(new FlowLayout());

        roundPanel.add(createCodePanel());
        roundPanel.add(createFeedbackPanel());

        boardPanel.add(roundPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void parseCodeColors(String code) {
        codeColors = Arrays.asList(code.split(" "));
    }

    private void parseFeedbackColors(String feedback) {
        List<String> feedbackRound = Arrays.asList(feedback.split(" "));
        feedbackColors.add(feedbackRound);
    }

    private JPanel createCodePanel() {
        JPanel codePanel = new JPanel();
        for (String colorCode : codeColors) {
            codePanel.add(createColorCircle(colorCode));
        }
        return codePanel;
    }

    private JPanel createFeedbackPanel() {
        JPanel feedbackPanel = new JPanel();
        List<String> feedbackRound = feedbackColors.get(feedbackColors.size() - 1);
        for (String feedbackCode : feedbackRound) {
            feedbackPanel.add(createColorCircle(feedbackCode));
        }
        return feedbackPanel;
    }

    private JLabel createColorCircle(String colorCode) {
        JLabel circle = new JLabel();
        circle.setOpaque(true);
        circle.setBackground(getColor(colorCode));
        circle.setPreferredSize(new Dimension(CIRCLE_SIZE, CIRCLE_SIZE));
        circle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return circle;
    }

    private Color getColor(String colorCode) {
        return COLOR_MAP.getOrDefault(colorCode, Color.BLACK);
    }

    public static void main(String[] args) {
        MastermindGUI mastermindGUI = new MastermindGUI();
        mastermindGUI.updateBoard("RO NA AZ NE VE AM", "NE BL BL BL BL BL");
    }
}

