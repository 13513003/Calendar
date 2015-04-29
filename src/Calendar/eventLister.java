package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by user on 4/29/2015.
 */
public class eventLister extends JFrame {

    private JList listevent;
    private int row = 0;
    private int col = 0;
    private String selectedvalue = new String();
    private int listindex;
    private String tablevalue = new String();
    private String listvalue = new String();
    private String combovalue = new String();
    private CalendarModel model;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        eventAdder adder = new eventAdder(row,col,model,tablevalue,listvalue,listindex,combovalue);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        selectedvalue = listevent.getSelectedValue().toString();
        if(!selectedvalue.contains("-----") && !selectedvalue.contains(tablevalue + " " + listvalue + " " + combovalue)) {
            while (listevent.getSelectedIndex() % 7 != 0) {
                listevent.setSelectedIndex(listevent.getSelectedIndex() + 1);
                selectedvalue += "`" + listevent.getSelectedValue().toString();
            }
            int typeofEvent = 0;
            if(selectedvalue.contains("Party: ")){
                selectedvalue = selectedvalue.replace("Party: ", "");
                selectedvalue = selectedvalue.replace("Tema: ", "");
                selectedvalue = selectedvalue.replace("DC: ", "");
            }
            else if(selectedvalue.contains("Meeting: ")){
                selectedvalue = selectedvalue.replace("Meeting: ", "");
                selectedvalue = selectedvalue.replace("Topik: ", "");
                selectedvalue = selectedvalue.replace("Partisipan: ", "");
                typeofEvent = 1;
            }

            selectedvalue = selectedvalue.replace("Lokasi: ", "");
            selectedvalue = selectedvalue.replace("Pesan: ", "");

            try{
                FileReader fileReader = new FileReader("src/input.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String inputLine;
                String removeline = new String();
                if(typeofEvent == 0)
                    removeline = combovalue
                            + "`" + (listindex + 1)
                            + "`" + tablevalue
                            + "`" + "Party"
                            + "`" + selectedvalue;
                else if(typeofEvent == 1)
                    removeline = combovalue
                            + "`" + (listindex + 1)
                            + "`" + tablevalue
                            + "`" + "Meeting"
                            + "`" + selectedvalue;
                java.util.List<String> lineList = new ArrayList<String>();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    if(!inputLine.equals(removeline))
                        lineList.add(inputLine);
                }
                fileReader.close();

                FileWriter fileWriter = new FileWriter("src/input.txt");
                PrintWriter out = new PrintWriter(fileWriter);
                for (String outputLine : lineList) {
                    out.println(outputLine);
                }
                out.flush();
                out.close();
                fileWriter.close();
            }
            catch (IOException e){
                System.out.println("File I/O error!");
            }
            dispose();
            model.init();
        }
    }

    public eventLister(int selectedrow,int selectedcol, CalendarModel inputmodel, String inputtablevalue, String inputlistvalue, int inputlistindex, String inputcombovalue, String[] years){
        setLocation(400, 200);
        setTitle("Today's Events");
        setVisible(true);
        setResizable(false);

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        row = selectedrow;
        col = selectedcol;
        model = inputmodel;
        tablevalue = inputtablevalue;
        combovalue = inputcombovalue;
        listvalue = inputlistvalue;
        listindex = inputlistindex;
        setLayout(new BorderLayout());
        listevent = new JList();
        listevent.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listevent.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(listevent);

        ArrayList<String> listModel = new ArrayList<String>();
        listModel.add(tablevalue + " " + listvalue + " " + combovalue);
        listModel.add("--------------------------------------------------------------------------------------------------------");
        for(int i=0; i<model.getDay()[Integer.parseInt(combovalue) - Integer.parseInt(years[0])][listindex][Integer.parseInt(tablevalue) - 1].getEvent().size();i++){
            String event = model.getDay()[Integer.parseInt(combovalue) - Integer.parseInt(years[0])][listindex][Integer.parseInt(tablevalue) - 1].toStringDay(i);
            String[] tokenevent = event.split(";");
            for(String partevent : tokenevent )
                listModel.add(partevent);
            listModel.add("--------------------------------------------------------------------------------------------------------");
        }

        listevent.setModel(new javax.swing.AbstractListModel() {
            Object[] strings = listModel.toArray();

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        listScroller.setViewportView(listevent);

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(listScroller)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(34, Short.MAX_VALUE)
                                .addComponent(listScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(31, 31, 31))
        );
        pack();
    }
}
