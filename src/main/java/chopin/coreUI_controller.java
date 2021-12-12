package chopin;


import java.awt.*;
import java.awt.image.BufferedImage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.io.*;

public class coreUI_controller {
    public String save;
    util utile = new util();

    @FXML
    private Text pathtxt;
    @FXML
    private ImageView imgview;
    @FXML
    private ComboBox cmblang;
    @FXML
    private TextArea outarea;
    @FXML
    private AnchorPane anch;
    @FXML
    public void initialize(){


        cmblang.getItems().addAll("fra","eng");
        cmblang.setPromptText("fra");
        save=outarea.getText();



    }
    public void fileselect() throws IOException, AWTException {

        outarea.setText(null);
        Stage win = (Stage) anch.getScene().getWindow();


        File f;
        FileChooser fc = new FileChooser();
        fc.setTitle("Select an image to process");

        try{
        f=fc.showOpenDialog(win);
           if(f==null){
               outarea.setText(save);
           }

        String fpath = f.getAbsolutePath();
        if(true){
            System.out.println("file selected path ="+fpath);
            if(utile.isImg(fpath)){
                System.out.println("File is indeed an Image");
                if(utile.isPNG(fpath)){
                pathtxt.setText(fpath);
                utile.setImage(imgview,fpath);
                outarea.setText("Click process , if the image contain text it will show up here");
                }
                else{


                    BufferedImage bi = ImageIO.read(f);
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    ImageIO.write(bi,"png",bo);
                    imgview.setImage(new Image(new ByteArrayInputStream((bo.toByteArray()))));
                    pathtxt.setText(fpath);
                    outarea.setText("Click process , if the image contain text it will show up here");
                }

            }else{
                utile.alert("File is not an Image(png/jpg)");
                reset();
            }
        }
        }catch(Exception InvocationTargetException){
            outarea.setText(save);
        }
    }
    public void reset() throws IOException, AWTException {
    imgview.setImage(new Image(getClass().getResourceAsStream("/Image/NOimgSELECTED.png")));
    outarea.setText("output field (select an image with text and then click process)");
    pathtxt.setText("No image selected (use the file selector beside)");
        save=outarea.getText();




    }
    public void process(){
        String lang;
        if(cmblang.getValue() == null ||cmblang.getValue().equals("fra")){
            lang = "fra";
        }else {
            lang="eng";
        }
        try {
            outarea.setText(utile.ocr(pathtxt.getText(),lang));
        } catch (TesseractException e) {

        }
      save=outarea.getText();


    }
}
