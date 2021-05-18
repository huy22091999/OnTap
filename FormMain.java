
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author huycn
 */
public class FormMain extends JFrame implements ActionListener{
    Connection con;
    JTable jt;
    
    JButton btnThem,btnSua,btnXoa,btnHuy;
    JTextField txtID,txtTen,txtTg,txtNXB,txtNhaXB;
    JLabel lbID,lbTen,lbTG,lbNXB,lbNhaXB;
    JScrollPane js;
    JPanel jtMain,jtText,jtBtn;
    
    public FormMain()
    {
        super("Main");
        initDataBase();
       
        jt=new JTable();
        lbID=new JLabel("ID:");
        lbTen=new JLabel("Tên sách:");
        lbTG=new JLabel("Tác Giả:");
        lbNXB=new JLabel("Năm xuất bản:");
        lbNhaXB=new JLabel("Nhà xuất bản:");
        txtID=new JTextField(5);
        txtTen=new JTextField(10);
        txtTg=new JTextField(10);
        txtNXB=new JTextField(10);
        txtNhaXB=new JTextField(10);
        jtText=new JPanel(new GridLayout(5,2));
        jtText.add(lbID);
        jtText.add(txtID);
        jtText.add(lbTen);
        jtText.add(txtTen);
        jtText.add(lbTG);
        jtText.add(txtTg);
        jtText.add(lbNXB);
        jtText.add(txtNXB);
        jtText.add(lbNhaXB);
        jtText.add(txtNhaXB);
        btnThem=new JButton("Thêm");
        btnSua=new JButton("Sửa");
        btnXoa=new JButton("Xóa");
        btnHuy=new JButton("Hủy");
        jtBtn=new JPanel(new FlowLayout());
        jtBtn.add(btnThem);
        jtBtn.add(btnSua);
        jtBtn.add(btnXoa);
        jtBtn.add(btnHuy);
        jtMain=new JPanel(new BorderLayout());
        jtMain.add(jtText,BorderLayout.NORTH);
        jtMain.add(jtBtn,BorderLayout.SOUTH);
        js=new JScrollPane(jt);
        this.getContentPane().add(jtMain,BorderLayout.NORTH);
        this.getContentPane().add(js,BorderLayout.CENTER);
        

        this.setBounds(200, 200, 600, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void initDataBase()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysach","root","");
            System.out.println("connect ok!");
        } catch (SQLException|ClassNotFoundException e) {
            System.out.print("lỗi kết nối");
        }
    }
    public ResultSet loadData()
    {
        ResultSet rs=null;
        String sql="select * from sach";
        try {
            if(con!=null)
            {
                Statement st=con.createStatement();
                rs=st.executeQuery(sql);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("lỗi load data!");
        }
        System.out.println("load data thành công!");
        return rs;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
       FormMain f= new FormMain();

    }
    class MTLS implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row=jt.getSelectedRow();
            if(row>0)
            {
                txtID.setText(String.valueOf(jt.getValueAt(row, 0)));
                txtTen.setText(String.valueOf(jt.getValueAt(row, 1)));
                txtTg.setText(String.valueOf(jt.getValueAt(row, 2)));
                txtNXB.setText(String.valueOf(jt.getValueAt(row, 3)));
                txtNhaXB.setText(String.valueOf(jt.getValueAt(row, 4)));
            }
        }
        
    }
    
    
}
