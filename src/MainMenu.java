import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MainMenu extends JFrame {
    private JPanel rootPanel;
    private JButton addVisitButtonQ;
    private JButton addPatButton;
    private JTextField patInfoTextField;
    private JComboBox patSearchComboBox;
    private JButton searchPatButton;
    private JButton addDocButton;
    private JTextField docInfoTextField;
    private JComboBox docSearchComboBox;
    private JButton searchDocButton;
    private JButton statsSearch;
    private JComboBox statsTypeComboBox;
    private JButton helpButton;
    private JButton allVisitsButton;
    private JComboBox statsChoiceComboBox;
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Visit> visits = new ArrayList<>();
    PatientCreator patFactory = new PatientCreator();
    DoctorCreator docFactory = new DoctorCreator();


    public MainMenu() {

        patSearchComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        patSearchComboBox.addItem("по фамилии");
        patSearchComboBox.addItem("по паспорту");
        patSearchComboBox.addItem("по полису");
        docSearchComboBox.addItem("по фамилии");
        docSearchComboBox.addItem("по специальности");
        docSearchComboBox.addItem("по месту работы");
        statsTypeComboBox.addItem("по месяцам");
        statsTypeComboBox.addItem("по районам");
        String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        for (String i : months) {
            statsChoiceComboBox.addItem(i);
        }

        searchPatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searcher searcher = new Searcher();
                String searchText = patInfoTextField.getText();
                int searchType = patSearchComboBox.getSelectedIndex();
                ArrayList<Patient> found_list;
                found_list = searcher.searchPat(patients, searchType, searchText);
                if (found_list.size() != 0) {
                    TableForm dialog = new TableForm(found_list, true, true);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        searchDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searcher searcher = new Searcher();
                String searchText = docInfoTextField.getText();
                int searchType = docSearchComboBox.getSelectedIndex();
                ArrayList<Doctor> found_list;
                found_list = searcher.searchDoc(doctors, searchType, searchText);
                if (found_list.size() != 0) {
                    TableForm dialog = new TableForm(found_list, true, false);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });


        addPatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Patient pat = patFactory.create();
                NewPat dialog = new NewPat(pat);
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        pat.setID(patients.size() == 0 ? 0 : patients.get(patients.size() - 1).getID() + 1);
                        pat.setRegistrationDate(new Date());
                        try {
                            patients.add(pat);
                        } catch (NullPointerException a) {
                            ;
                        }
                    }
                });
            }
        });

        addDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor doc = docFactory.create();
                NewDoc dialog = new NewDoc(doc);
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        doc.setID(doctors.size() == 0 ? 0 : doctors.get(doctors.size() - 1).getID() + 1);
                        try {
                            doctors.add(doc);
                        } catch (NullPointerException a) {
                            ;
                        }
                    }
                });
            }
        });
        addVisitButtonQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visit visit = new Visit();
                NewVisit dialog = new NewVisit(visit);
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            visits.add(visit);
                        } catch (NullPointerException a) {
                            ;
                        }
                    }
                });
            }
        });
        statsSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searcher searcher = new Searcher();
                int searchType = statsTypeComboBox.getSelectedIndex();
                Map<String, ArrayList<Stats>> searchVisit;
                searchVisit = searcher.searchVisit(visits, searchType);
                String[][] data;
                String statType = statsChoiceComboBox.getSelectedItem().toString();
                data = new String[searchVisit.get(statType).size()][2];
                for (int i = 0; i < searchVisit.get(statType).size(); i++) {
                    data[i][0] = searchVisit.get(statType).get(i).getDiseaseName();
                    data[i][1] = Integer.toString(searchVisit.get(statType).get(i).getNumberOf());
                }
                Statistics dialog = new Statistics(data);
                dialog.pack();
                dialog.setVisible(true);
                dialog.addWindowListener(new WindowAdapter() {
                });
            }
        });
        statsTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int searchType = statsTypeComboBox.getSelectedIndex();
                statsChoiceComboBox.removeAllItems();
                //String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
                String[] regions = {"Адмиралтейский", "Василеостровский", "Выборгский", "Калининский", "Кировкский", "Колпинский",
                        "Красногвардейский", "Красносельский", "Кронштадский", "Курортный", "Московский", "Невский", "Петроградский",
                        "Петродворцовый", "Приморский", "Пушкинский", "Фрунзенский", "Центральный"};
                switch (searchType) {
                    case 0: {
                        for (String i : months) {
                            statsChoiceComboBox.addItem(i);
                        }

                        break;
                    }
                    case 1: {

                        for (String i : regions) {
                            statsChoiceComboBox.addItem(i);
                        }
                        break;
                    }
                }
            }
        });
        allVisitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (visits.size() != 0) {
                    String[][] data = new String[visits.size()][5];
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    for (int i = 0; i < visits.size(); i++) {
                        data[i][0] = sdf.format(visits.get(i).getDateOfVisit());
                        data[i][1] = visits.get(i).getDoc();
                        data[i][2] = visits.get(i).getPat();
                        data[i][3] = visits.get(i).getDiagnosis();
                        data[i][4] = visits.get(i).getRegion();
                    }
                    AllVisits dialog = new AllVisits(data);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Patient Ivanov = new Patient();
        Ivanov.setSurname("Иванов");
        Ivanov.setName("Иван");
        Ivanov.setLastName("Иванович");
        Ivanov.setPassportNum(12345);
        patients.add(Ivanov);
        Visit newvisit = new Visit();
        newvisit.setPat("Иванов Кирилл");
        newvisit.setDoc("Петров Иван");
        newvisit.setDiagnosis("Корь");
        newvisit.setDateOfVisit("12.12.2019");
        visits.add(newvisit);

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Пациенты");
        rootPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        addPatButton = new JButton();
        addPatButton.setText("Добавить пациента");
        rootPanel.add(addPatButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patInfoTextField = new JTextField();
        rootPanel.add(patInfoTextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(233, 30), null, 1, false));
        patSearchComboBox = new JComboBox();
        rootPanel.add(patSearchComboBox, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(182, 30), null, 0, false));
        searchPatButton = new JButton();
        searchPatButton.setText("Поиск");
        rootPanel.add(searchPatButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addVisitButtonQ = new JButton();
        addVisitButtonQ.setText("Новая запись к врачу");
        rootPanel.add(addVisitButtonQ, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label2 = new JLabel();
        label2.setText("Врачи");
        rootPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(64, 16), null, 1, false));
        addDocButton = new JButton();
        addDocButton.setText("Добавить врача");
        rootPanel.add(addDocButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        docInfoTextField = new JTextField();
        rootPanel.add(docInfoTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        docSearchComboBox = new JComboBox();
        rootPanel.add(docSearchComboBox, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchDocButton = new JButton();
        searchDocButton.setText("Поиск");
        rootPanel.add(searchDocButton, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Статистика");
        rootPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        statsSearch = new JButton();
        statsSearch.setText("Показать");
        rootPanel.add(statsSearch, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statsTypeComboBox = new JComboBox();
        rootPanel.add(statsTypeComboBox, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(64, 30), null, 1, false));
        final JLabel label4 = new JLabel();
        label4.setText("Быстрый доступ");
        rootPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        helpButton = new JButton();
        helpButton.setText("Помощь");
        rootPanel.add(helpButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        allVisitsButton = new JButton();
        allVisitsButton.setText("Все записи к врачам");
        rootPanel.add(allVisitsButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        statsChoiceComboBox = new JComboBox();
        rootPanel.add(statsChoiceComboBox, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
