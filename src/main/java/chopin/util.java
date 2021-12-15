package chopin;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

public class util {
    public  Boolean isImg(String path){
        String ft;
        boolean out=false;
        MimetypesFileTypeMap mt= new MimetypesFileTypeMap();
        ft = mt.getContentType(path);
        String[] sa = ft.split("/");
        if(sa[0].equals("image")){
            out=true;
        }
        return out;}
    public  Boolean isPNG(String path){
        String ft;
        boolean out=false;
        MimetypesFileTypeMap mt= new MimetypesFileTypeMap();
        ft = mt.getContentType(path);
        String[] sa = ft.split("/");
        if(sa[1].equals("png")){
            out=true;
        }
        return out;}


    public void setImage(ImageView imgv , String path){
        Image image = new Image("file:"+path);écut
        imgv.setImage(image);
    }
    public String ocr(String path ,String lang) throws TesseractException {
        String out;
        Tesseract tess = new Tesseract();
        File td = LoadLibs.extractTessResources("tessdata");
        tess.setDatapath(td.getAbsolutePath());
        tess.setLanguage(lang);
        File  f = new File(path);
        out=tess.doOCR(f);
    return out;}
    public void alert(String txt) throws IOException {
        Button exit = new Button();
        Label lbl = new Label();
        Stage win = new Stage();
        StackPane sp = new StackPane();
        exit.setText("Ok");
        lbl.setText(txt);
        sp.getChildren().addAll(exit,lbl);
        sp.setAlignment(exit, Pos.BOTTOM_CENTER);
        sp.setAlignment(lbl,Pos.TOP_CENTER);
        exit.setOnAction(e -> win.close());
        Scene scene = new Scene(sp, 300, 75);
        win.setTitle("Warning");
        win.setResizable(false);
        win.setScene(scene);
        win.getIcons().add(new Image(getClass().getResourceAsStream("/Image/icon.png")));
        win.show();

    }
}
