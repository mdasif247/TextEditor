import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//implementing ActionListner class to use its actionperform method on menuitems
public class TextEditor implements ActionListener {

    //frame for texteditor
    JFrame frame;

    //menuBar object to create MenuBar
    JMenuBar menuBar;

    //menus
    JMenu file;
    JMenu edit;

    //object to create menuitems for file menu
    JMenuItem newfile,openfile,savefile;

    //object to create menuitems for edit menu
    JMenuItem cut,copy,paste,selectall,close;

    //object to create textarea
    JTextArea textArea;

    TextEditor(){
        frame=new JFrame();
        menuBar=new JMenuBar();

        //initialize menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //initialize menuitems for file menu
        newfile=new JMenuItem("New");
        openfile=new JMenuItem("Open");
        savefile=new JMenuItem("Save");

        //initialize menuitems for edit menu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("Select all");
        close=new JMenuItem("Close");

        //initialize textarea
        textArea=new JTextArea();

        //set height and width of frame
        frame.setBounds(100,100,800,400);
        //make frame visible
        frame.setVisible(true);

        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //add menubar to frame
        frame.setJMenuBar(menuBar);

        //add file menuitems to file menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //add edit menuitems to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        //add textarea to frame
        frame.add(textArea);

        //add ActionListner to file menu items or sends this object to ActionListner
        //this is an obect of TextEditor on which we are adding AcionListner
        //ex here we are performing actionlistner event on new and other menuitems
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        //add ActionListner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);
    }

    //using actionPerformed method present in ActionListener so override
    //ActionEvent defines what action to be performed
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //gets the source of actionEvent i.e cut
        if(actionEvent.getSource()==cut){
            //performs cut
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform paste
            textArea.paste();
        }
        if(actionEvent.getSource()==selectall){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //just exit from the window
            System.exit(0);
        }
        if(actionEvent.getSource()==newfile){
            //call the instance of a class again to create a new frame
            TextEditor newtexteditor=new TextEditor();
        }
        if(actionEvent.getSource()==openfile){
            //fileChooser will allow to choose a file of selected directory
            JFileChooser fileChooser=new JFileChooser("C:");

            //to check if the file is selected or not(1 for open/0 for cancel)
            int chooseoption=fileChooser.showOpenDialog(null);
            //if option is open
            //make the size of textarea as the size of the file
            //display the data in texteditor as present in the file
            if(chooseoption==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                String filepath=file.getPath();
                try{
                    //create bufferReader
                    //bufferReader will read the file line by line
                    //filereader object will read the file
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filepath));
                    //create string to store the contents of the file
                    String line="",output="";
                    //send content line by line
                    while((line=bufferedReader.readLine())!=null){
                        output= output + line+"\n";
                    }
                    //output in text area
                    textArea.setText(output);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        if(actionEvent.getSource()==savefile){
            JFileChooser fileChooser1=new JFileChooser("C:");
            //check if to save or not
            int chooseoption1= fileChooser1.showSaveDialog(null);
            if(chooseoption1==JFileChooser.APPROVE_OPTION){
                //create file object with selected path
                File file=new File(fileChooser1.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //create bufferedwriter to write content to file
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    //get content from textarea to outfile
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        }
    }
    public static void main(String[] args) {
        TextEditor texteditor=new TextEditor();

    }
}

