/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class processModel {

    Connection conn;

    public processModel() {
        conn = db.dbConnection.getConnection();
    }

    //check login method start
    public String doLogin(String username, String password) {
String msg=null;
        if (username.equals("adyapana") && password.equals("ad123")) {
            msg= "Logged In Successfully";
        } else {
            msg= "Login Failure";
        }
        return msg;
    }
//check login method ends

//check student registration method start    
    public String student_register(String student_name, String student_address, String student_dob) {
        String msg = null;
        String query = "INSERT INTO student(student_name,address,dob)VALUES(?,?,?)";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, student_name);
            psm.setString(2, student_address);
            psm.setString(3, student_dob);
            psm.execute();
            msg = "Student registration Success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Student Registration Error";
        }
        return msg;
    }
//check student registration method end 

//check Teacher registration method start    
    public String teacherRegister(String teacher_name, String teacher_address, String subjects) {
        String msg = null;
        String query = "INSERT INTO teacher(teacher_name,address,subjects)VALUES(?,?,?)";

        try {
            PreparedStatement psm1 = conn.prepareStatement(query);
            psm1.setString(1, teacher_name);
            psm1.setString(2, teacher_address);
            psm1.setString(3, subjects);
            psm1.execute();
            msg = "Teacher Registered";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Teacher registration Error";
        }
        return msg;
    }
//check Teacher Registration method ends

//check Subject register method start    
    public String subjectRegister(String sub_desc, String sub_price) {
        String msg = null;
        String query = "INSERT INTO subjects(description,price)VALUES(?,?)";

        try {
            PreparedStatement psm2 = conn.prepareStatement(query);
            psm2.setString(1, sub_desc);
            psm2.setString(2, sub_price);
            psm2.execute();
            msg = "Subject Register success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Subject register error";
        }
        return msg;
    }
//check Subject register method end

// student table loading method start
    public void loadStudentTable(String keyword, DefaultTableModel dtm) {
        String query = "SELECT * FROM student WHERE student_name LIKE '%" + keyword + "%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String userId = rs.getString("sno");
                String name = rs.getString("student_name");
                String address = rs.getString("address");
                String db = rs.getString("dob");
                dataRow = new String[4];
                dataRow[0] = userId;
                dataRow[1] = name;
                dataRow[2] = address;
                dataRow[3] = db;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//student table loading method ends    

// teacher table loading method start
    public void loadTeacherTable(String keyword, DefaultTableModel dtm) {
        String query = "SELECT * FROM teacher WHERE teacher_name LIKE '%" + keyword + "%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String tId = rs.getString("tno");
                String name = rs.getString("teacher_name");
                String address = rs.getString("address");
                String sub = rs.getString("subjects");
                dataRow = new String[4];
                dataRow[0] = tId;
                dataRow[1] = name;
                dataRow[2] = address;
                dataRow[3] = sub;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//teacher table loading method ends    

// subject table loading method start
    public void loadSubjectTable(String keyword, DefaultTableModel dtm) {
        String query = "SELECT * FROM subjects WHERE description LIKE '%" + keyword + "%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String subID = rs.getString("subno");
                String desc = rs.getString("description");
                String price = rs.getString("price");
                dataRow = new String[4];
                dataRow[0] = subID;
                dataRow[1] = desc;
                dataRow[2] = price;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//subject table loading method ends    

    
//class table loading method start
    public void loadClassTable(String string, DefaultTableModel dtm) {
//        int value=Integer.parseInt(string);
       String query = "select classno,teacher_name,description,timeslot from class \n" +
"join subjects on class.subno=subjects.subno\n" +
"join teacher on class.tno=teacher.tno\n" +
"where class.classno LIKE '%"+string+"%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String classNo = rs.getString("classno");
                String tea_name = rs.getString("teacher_name");
                String sub_name = rs.getString("description");
                String timeSlot = rs.getString("timeslot");
                dataRow = new String[4];
                dataRow[0] = classNo;
                dataRow[1] = tea_name;
                dataRow[2] = sub_name;
                dataRow[3] = timeSlot;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//class table loading method ends

    
//payment table loading method starts
    public void loadPaymentTable(String keyword, DefaultTableModel dtm) {
        String query = "select invono,student_name,teacher_name,description,month,value from invoice \n" +
                                 "join student on invoice.sno=student.sno \n" +
                                 "join teacher on invoice.tno=teacher.tno\n" +
                                 "join subjects on invoice.subno=subjects.subno\n" +
                                 "where invoice.month LIKE '%"+keyword+"%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String invo_no = rs.getString("invono");
                String student_name = rs.getString("student_name");
                String teacher_name = rs.getString("teacher_name");
                String subject_name = rs.getString("description");
                String month = rs.getString("month");
                String value = rs.getString("value");
                dataRow = new String[6];
                dataRow[0] = invo_no;
                dataRow[1] = student_name;
                dataRow[2] = teacher_name;
                dataRow[3] = subject_name;
                dataRow[4] = month;
                dataRow[5] = value;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//payment table loading method ends    
    
    
    //update student
    public String student_update(String id, String name, String address, String dob) {
        String msg = null;
        String query = "UPDATE student SET  student_name=?,address=?,dob=? WHERE  sno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, name);
            psm.setString(2, address);
            psm.setString(3, dob);
            psm.setString(4, id);
            psm.execute();
            return msg = "Student update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Student Update error";
    }

    
    //delete student 
    public String student_delete(String id) {
        String msg = null;
        String query = "DELETE FROM student  WHERE  sno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "Student delete success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Student delete error";
    }

    
    
    
    
    
    
    
    //update subject
    public String subject_update(String id, String desc, String price) {
        String msg = null;
        String query = "UPDATE subjects SET  description=?,price=? WHERE  subno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, desc);
            psm.setString(2, price);
            psm.setString(3, id);
            psm.execute();
            return msg = "Subject Update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Subject Update error";
    
    }

    //delete subject
    public String subject_delete(String id) {
        String msg = null;
        String query = "DELETE FROM subjects  WHERE  subno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "subject delete success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "subject delete error";
    }
    

    
    
    
    
    
    
    //update teacher
    public String teacher_update(String id, String name, String address, String subject) {
        String msg = null;
        String query = "UPDATE teacher SET  teacher_name=?,address=?,subjects=? WHERE  tno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, name);
            psm.setString(2, address);
            psm.setString(3, subject);
            psm.setString(4, id);
            psm.execute();
            return msg = "teacher update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Teacher update error";
    }

    //delete teacher
    public String teacher_delete(String id) {
        String msg = null;
        String query = "DELETE FROM teacher  WHERE  tno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "Teacher delete success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Teacher delete Error";
    }
    
    
    
    
//register payment
    public String payment_add(String sid, String tid, String subid, String month, String value) {
        String msg=null;
        String query="INSERT INTO invoice(sno,tno,subno,month,value)VALUES(?,?,?,?,?)";
        
         try {
            PreparedStatement psm2 = conn.prepareStatement(query);
            psm2.setString(1, sid);
            psm2.setString(2, tid);
            psm2.setString(3, subid);
            psm2.setString(4, month);
            psm2.setString(5, value);
            psm2.execute();
            msg = "Payment Added success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Payment added error";
        }
        return msg;
    }

    
    //update payment
    public String payment_update(String inid, String sid, String tid, String subid, String month, String value) {
    String msg = null;
        String query = "UPDATE invoice SET  sno=?,tno=?,subno=?,month=?,value=?  WHERE  invono=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, sid);
            psm.setString(2, tid);
            psm.setString(3, subid);
            psm.setString(4, month);
            psm.setString(5, value);
            psm.setString(6, inid);
            psm.execute();
            return msg = "Payment update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Payment update error";
    }

    
    //class data add
    public String classRegister(String teacherID, String subID, String time) {
       String msg=null;
        String query="INSERT INTO class(subno,tno,timeslot)VALUES(?,?,?)";
        
         try {
            PreparedStatement psm2 = conn.prepareStatement(query);
            psm2.setString(1, teacherID);
            psm2.setString(2, subID);
            psm2.setString(3, time);
            psm2.execute();
            msg = "Class Added success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Class added error";
        }
        return msg;
    }

    //class data update
    public String class_update(String classID, String teacherID, String subID, String time) {
      String msg = null;
        String query = "UPDATE class SET  subno=?,tno=?,timeslot=? WHERE  classno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, subID);
            psm.setString(2, teacherID);
            psm.setString(3, time);
            psm.setString(4, classID);
            psm.execute();
            return msg = "class update success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "class update error";
        }
        return msg;
    }

    
 //class data delete
    public String class_delete(String id) {
        String msg = null;
        String query = "DELETE FROM class  WHERE  classno=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "Class Deletion success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "Class Deletion Error";
    }
   
    
    //load student enroll data
    public void loadStuEnrollTable(String string, DefaultTableModel dtm) {
    //        int value=Integer.parseInt(string);
       String query = "select id_student_enroll,class_classno,student_name,description,teacher_name from student_class_enroll \n" +
"join student on student_class_enroll.student_sno=student.sno\n" +
"join teacher on student_class_enroll.teacher_tno=teacher.tno\n" +
"join subjects on student_class_enroll.subjects_subno=subjects.subno\n" +
"where student_class_enroll.class_classno LIKE '%"+string+"%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String enroll_no = rs.getString("id_student_enroll");
                String classno = rs.getString("class_classno");
                String studentno = rs.getString("student_name");
                String subjectno = rs.getString("description");
                String teacherno = rs.getString("teacher_name");
                dataRow = new String[5];
                dataRow[0] = enroll_no;
                dataRow[1] = classno;
                dataRow[2] = studentno;
                dataRow[3] = subjectno;
                dataRow[4] = teacherno;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //enroll a student
    public String student_enroll_reg(String classID, String studentID,String subjectID,String teacherID) {
        String msg=null;
        String query="INSERT INTO student_class_enroll(class_classno,student_sno,teacher_tno,subjects_subno)VALUES(?,?,?,?)";
        
         try {
            PreparedStatement psm2 = conn.prepareStatement(query);
            psm2.setString(1, classID);
            psm2.setString(2, studentID);
            psm2.setString(3, teacherID);
            psm2.setString(4, subjectID);
            psm2.execute();
            msg = "student enrollment Added success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "student enrollment added error";
        }
        return msg;
    }

    //update an enrolled student
    public String student_enroll_update(String enrID, String classID, String stuID,String subjectID,String teacherID) {
    String msg = null;
        String query = "UPDATE student_class_enroll SET  class_classno=?,student_sno=?,teacher_tno=?,subjects_subno=? WHERE  id_student_enroll=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, classID);
            psm.setString(2, stuID);
            psm.setString(3, teacherID);
            psm.setString(4, subjectID);
            psm.setString(5, enrID);
            psm.execute();
            return msg = "enrollment update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "enrollment update error";
    }

    
    //delete an enrolled student
    public String enroll_delete(String id) {
         String msg = null;
        String query = "DELETE FROM student_class_enroll  WHERE  id_student_enroll=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "enroll delete success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "enroll delete Error";
    }

    
    //view enrollment table load
    public void loadStudentJoinClassTable(String string, DefaultTableModel dtm) {
//        int id=Integer.parseInt(string);
           String query ="SELECT classno,description,teacher_name,timeslot,student_name FROM class \n" +
"join student_class_enroll on class.classno=student_class_enroll.class_classno \n" +
"join student on student_class_enroll.student_sno=student.sno  \n" +
"join teacher on student_class_enroll.teacher_tno=teacher.tno \n" +
"join subjects on student_class_enroll.subjects_subno=subjects.subno  \n" +
"WHERE class.classno LIKE '%"+string+"%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                int classno = rs.getInt("classno");
                String subno = rs.getString("description");
                String teacherno = rs.getString("teacher_name");
                String time = rs.getString("timeslot");
                String student_name = rs.getString("student_name");
                dataRow = new String[5];
                String classID=String.valueOf(classno);
                dataRow[0] = classID;
                dataRow[1] = subno;
                dataRow[2] = teacherno;
                dataRow[3] = time;
                dataRow[4] = student_name;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //student and class due list table load
    public void loadStudentJoinClassTable2(String classId,String stname, DefaultTableModel dtm) {
//        int id=Integer.parseInt(string);
           String query ="SELECT classno,description,teacher_name,timeslot,student_name FROM class \n" +
"join student_class_enroll on class.classno=student_class_enroll.class_classno \n" +
"join student on student_class_enroll.student_sno=student.sno  \n" +
"join teacher on student_class_enroll.teacher_tno=teacher.tno \n" +
"join subjects on student_class_enroll.subjects_subno=subjects.subno  \n" +
"WHERE class.classno LIKE '%"+classId+"%' AND student.student_name LIKE '%"+stname+"%' ";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                int classno = rs.getInt("classno");
                String subno = rs.getString("description");
                String teacherno = rs.getString("teacher_name");
                String time = rs.getString("timeslot");
                String student_name = rs.getString("student_name");
                dataRow = new String[5];
                String classID=String.valueOf(classno);
                dataRow[0] = classID;
                dataRow[1] = subno;
                dataRow[2] = teacherno;
                dataRow[3] = time;
                dataRow[4] = student_name;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //load attendance table
    public void loadAttendanceTable(String string, DefaultTableModel dtm) {
        //        int value=Integer.parseInt(string);
       String query = "SELECT id_attendance,classno,student_name,status,date FROM student join attendance on student.sno=attendance.student_sno\n" +
"join class on attendance.class_classno=class.classno \n" +
"WHERE attendance.class_classno LIKE '%"+string+"%'";
        dtm.setRowCount(0);
        try {
            Connection conn = db.dbConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String[] dataRow;
            while (rs.next()) {
                String attendanceID = rs.getString("id_attendance");
                String classno = rs.getString("classno");
                String studentName = rs.getString("student_name");
                String status = rs.getString("status");
                String date = rs.getString("date");
                dataRow = new String[5];
                dataRow[0] = attendanceID;
                dataRow[1] = classno;
                dataRow[2] = studentName;
                dataRow[3] = status;
                dataRow[4] = date;
                dtm.addRow(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    //insert attendance detail
       public String attendance_register(String classID, String studentID, String status, String date) {
         String msg=null;
        String query="INSERT INTO attendance(status,date,student_sno,class_classno)VALUES(?,?,?,?)";
        
         try {
            PreparedStatement psm2 = conn.prepareStatement(query);
            psm2.setString(1, status);
            psm2.setString(2, date);
            psm2.setString(3, studentID);
            psm2.setString(4, classID);
            psm2.execute();
            msg = "attendance registration success";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "attendance registration error";
        }
         return msg;
    }

       
       
       
       
       
       //update attendanc detail
    public String attendance_update(String id, String classID, String studentID, String status, String date) {
     String msg = null;
     int studentno=Integer.parseInt(studentID);
     int classno=Integer.parseInt(classID);
        String query = "UPDATE attendance SET  status=?,date=?,student_sno=?,class_classno=? WHERE  id_attendance=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, status);
            psm.setString(2, date);
            psm.setInt(3, studentno);
            psm.setInt(4, classno);
            psm.setString(5, id);
            psm.execute();
            return msg = "attendance update success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "attendance update error";
    }

    
    
    
    
    
    
    
    //delete attendance detail
    public String attendance_delete(String id) {
       String msg = null;
        String query = "DELETE FROM attendance  WHERE  id_attendance=?";

        try {
            PreparedStatement psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.execute();
            return msg = "attendence Deletion success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg = "attendence Deletion Error";
    }
}

