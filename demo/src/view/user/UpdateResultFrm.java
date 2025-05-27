package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import model.User;
import dao.RaceTrackDAO;
import dao.RaceResultDAO;// Assuming this is the package for RaceTrackDAO
import model.RaceResult;
import model.RaceTrack; // Assuming this is the package for RaceTrack model

public class UpdateResultFrm extends JFrame implements ActionListener {
    private JButton btnSave, btnBack;
    private JComboBox<RaceTrack> cbxRaceTrack;
    private JLabel lblTitle, lblRaceTrack, lblUsername, lblLapCount;
    private JScrollPane jScrollPane1;
    private JTable tblRaceResult;
    private User user;
    private ArrayList<RaceResult> listRaceResult;
    private ArrayList<RaceTrack> listRaceTrack;
    
    public UpdateResultFrm(User user) {
        this.user = user;

        setTitle("Update Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("Update Result");
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18)); 
        
        lblLapCount = new JLabel("");
        lblLapCount.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblUsername = new JLabel("Username: " + user.getUsername());
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18)); 

        btnSave = new JButton("Lưu");
        btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSave.addActionListener(this);

        lblRaceTrack = new JLabel("Chặng đua");
        lblRaceTrack.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cbxRaceTrack = new JComboBox<>(new RaceTrack[] {}); // Initial placeholder
        cbxRaceTrack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        getRaceTrackComboBox();
        cbxRaceTrack.addActionListener(this); // For handling selection changes
        
        btnBack = new JButton("Trở lại");
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBack.addActionListener(e -> {
            new DataEntryHomeFrm(user).setVisible(true); 
            dispose(); 
        });

        tblRaceResult = new JTable();
        tblRaceResult.setModel(new DefaultTableModel(
            new Object[][]{ 
            },
            new String[]{
                "STT", "Tên", "Tên đội đua", "Quốc tịch", "Ngày sinh", "Tiểu sử", "Thời gian hoàn thành", "Số vòng hoàn thành"
            }
        ) {
            Class[] types = new Class[]{
                Integer.class, String.class, String.class, String.class, Object.class, Object.class, Object.class, Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7;
            }
        });
        tblRaceResult.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JTextField()));
        jScrollPane1 = new JScrollPane(tblRaceResult);

        // GroupLayout setup (unchanged)
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        	    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	        .addGroup(layout.createSequentialGroup()
        	            .addGap(38, 38, 38)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
        	                .addGroup(layout.createSequentialGroup()
        	                    .addComponent(lblRaceTrack)
        	                    .addGap(18, 18, 18)
        	                    .addComponent(cbxRaceTrack, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
        	                    .addGap(18, 18, 18)
        	                    .addComponent(lblLapCount)))
        	            .addContainerGap(28, Short.MAX_VALUE))
        	        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        	            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        	                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
        	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        	                    .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
        	                    .addGap(78, 78, 78))
        	                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        	                    .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
        	                    .addGap(265, 265, 265))
        	                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        	                    .addComponent(lblUsername)
        	                    .addGap(28, 28, 28))))
        	);

        layout.setVerticalGroup(
        	    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	        .addGroup(layout.createSequentialGroup()
        	            .addContainerGap()
        	            .addComponent(lblTitle)
        	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        	            .addComponent(lblUsername)
        	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	                .addComponent(lblRaceTrack)
        	                .addComponent(cbxRaceTrack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	                .addComponent(lblLapCount))
        	            .addGap(18, 18, 18)
        	            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
        	            .addGap(18, 18, 18)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        	                .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        	            .addGap(24, 24, 24))
        	);
        layout.setVerticalGroup(
        	    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	        .addGroup(layout.createSequentialGroup()
        	            .addContainerGap()
        	            .addComponent(lblTitle)
        	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        	            .addComponent(lblUsername)
        	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	                .addComponent(lblRaceTrack)
        	                .addComponent(cbxRaceTrack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        	                .addComponent(lblLapCount))
        	            .addGap(18, 18, 18)
        	            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
        	            .addGap(18, 18, 18)
        	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
        	                .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        	            .addGap(24, 24, 24))
        	);


        pack();
        setLocationRelativeTo(null);
    }

    private void getRaceTrackComboBox() {
        RaceTrackDAO raceTrackDAO = new RaceTrackDAO();
        listRaceTrack = raceTrackDAO.getList();
        
        cbxRaceTrack.removeAllItems();
        cbxRaceTrack.addItem(null);
        
        if (listRaceTrack != null && !listRaceTrack.isEmpty()) {
            for (RaceTrack raceTrack : listRaceTrack) {
                cbxRaceTrack.addItem(raceTrack);
            }
        } else {
        	JOptionPane.showMessageDialog(this, "No race tracks available");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
        	if (tblRaceResult.isEditing()) {
        	    tblRaceResult.getCellEditor().stopCellEditing();
        	}

        	ArrayList<RaceResult> updatedResults = new ArrayList<>();
        	DateTimeFormatter flexFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("HH:mm:ss")
                    .optionalStart()
                    .appendFraction(ChronoField.MILLI_OF_SECOND, 0, 3, true)
                    .optionalEnd()
                    .toFormatter();
        	
        	for (int i = 0; i < listRaceResult.size(); i++) {
        		RaceResult r = listRaceResult.get(i);
        		
        		Object  time = tblRaceResult.getValueAt(i, 6); 
        		Object  laps = tblRaceResult.getValueAt(i, 7);
                
        		LocalTime  updatedTime = null;
                if(time != null && !time.toString().trim().isEmpty()) {
                	try {
                            updatedTime = LocalTime.parse(time.toString().trim(), flexFormatter);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Thời gian không hợp lệ (định dạng HH:mm:ss hoặc HH:mm:ss.fff)");
                        return;
                    }
                }
                int updatedLaps = 0;
                if(laps != null && !laps.toString().trim().isEmpty()) {
                	try {
                   	 double lapsDouble = Double.parseDouble(laps.toString().trim());
                   	    if (lapsDouble % 1 != 0 || lapsDouble < 0) {
                   	        throw new Exception();
                   	    }
                   	    updatedLaps = (int) lapsDouble;
                   } catch(Exception ex) {
	                   	JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Số vòng hoàn thành phải là 1 số nguyên >= 0");
	                   	return;
                   }
                }
                
                if(updatedLaps > r.getRaceTrack().getNumOfLaps()) {
                	JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Số vòng hoàn thành không thể lớn hơn " 
                + r.getRaceTrack().getNumOfLaps() + " - số vòng của chặng đua");
                   	return;
                }
                
//              if((updatedLaps > 0 && updatedTime == null)
                if(updatedTime != null && updatedLaps < r.getRaceTrack().getNumOfLaps()) {
                	JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Vòng đua hoàn thành cần bằng số vòng của chặng đua. ");
                   	return;
                }
                if(updatedLaps == r.getRaceTrack().getNumOfLaps() && updatedTime == null) {
                	JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Thiếu thông tin thời gian. ");
                   	return;
                }
                
        		r.setCompletedLaps(updatedLaps);
        		r.setCompletionTime(updatedTime);
        		
        		updatedResults.add(r);
        	}
        	
        	RaceResultDAO rd = new RaceResultDAO();
        	for (RaceResult r : updatedResults) {
                rd.updateRaceResult(r);
            }
        	int ok = JOptionPane.showConfirmDialog(this, "Cập nhật kết quả thành công!", "Message", JOptionPane.DEFAULT_OPTION);
        	if (ok == JOptionPane.OK_OPTION) {
        	    this.dispose();
        	}
        	
        } else if (e.getSource() == cbxRaceTrack) {
            if (cbxRaceTrack.getSelectedItem() != null) {
            	
            	if (tblRaceResult.isEditing()) {
                    tblRaceResult.getCellEditor().stopCellEditing();  
                }
            	
            	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
            	RaceTrack selectedRaceTrack = (RaceTrack) cbxRaceTrack.getSelectedItem();
            	lblLapCount.setText("Số vòng: " + selectedRaceTrack.getNumOfLaps() + " vòng");
            	RaceResultDAO rd = new RaceResultDAO();
            	listRaceResult = rd.getList(selectedRaceTrack.getId());
            	DefaultTableModel model = (DefaultTableModel) tblRaceResult.getModel();
            	model.setRowCount(0);
            	
            	for (int i = 0; i < listRaceResult.size(); i++) {
            	    RaceResult result = listRaceResult.get(i);
            	    
            	    Object[] rowData = new Object[]{
            	        i + 1,                      
            	        result.getMembership().getDriver().getName(),       
            	        result.getMembership().getRaceTeam().getName(),        
            	        result.getMembership().getDriver().getNational(),     
            	        result.getMembership().getDriver().getDob(),     
            	        result.getMembership().getDriver().getBio(),      
            	        result.getCompletionTime() != null 
	            	        ? result.getCompletionTime().format(timeFormatter)
	            	        : null,     
            	        result.getCompletedLaps()    
            	    };
            	    
            	    model.addRow(rowData);
            	}
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User testUser = new User(1, "datauser", "123", "Data User", "dataentry");
            new UpdateResultFrm(testUser).setVisible(true);
        });
    }
}