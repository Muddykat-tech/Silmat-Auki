package muddykat.silmat.auki;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import muddykat.silmat.auki.application.EyeMessage;
import muddykat.silmat.auki.application.EyeMessages;
import muddykat.silmat.auki.modules.CipherModule;

public class EyeController {

    //Should these be private, probably.
    @FXML
    ToggleButton trigrams;
    @FXML
    MenuButton eyeMessageOptions;
    @FXML
    SplitPane displaySplitPane;
    @FXML
    AnchorPane splitTop;
    @FXML
    AnchorPane splitBottom;
    @FXML
    TextArea eyeRawText;
    @FXML
    GridPane displayGrid;
    @FXML
    Button updateEyeGraphics;
    @FXML
    TextArea outputTextPane;
    @FXML
    MenuButton btnCipher;
    @FXML
    ToggleButton overlayEyes;
    @FXML
    TextField keyInput;
    @FXML
    Button outputToInput;

    @FXML
    Button btnEncrypt;

    @FXML
    Button btnDecrypt;

    @FXML
    public void initialize() {
        initializeUI();
        initializeModules();
    }

    private void initializeModules(){
        CipherModule cipherModule = new CipherModule();
        cipherModule.initializeButtons(eyeRawText, btnCipher, outputTextPane, keyInput, btnEncrypt, btnDecrypt);

    }

    private void initializeUI(){
        setupEyeMessageButton();
    }

    private static boolean overlayMode = false;
    private void setupEyeMessageButton(){
        setupOutputShift();
        setupOverlayMode();
        setupEyeSelection();

    }
    private void setupEyeSelection(){
        for (EyeMessages e : EyeMessages.values()) {
            MenuItem messageItem = new MenuItem(e.name());

            messageItem.setOnAction(event -> {
                if(!overlayMode) {
                    splitBottom.getChildren().clear();
                }
                eyeRawText.setText(e.getMessage().getRawString());
                e.getMessage().setDisplayPane(splitBottom);
                eyeMessageOptions.setText("Selected: " + e.name());
            });

            eyeMessageOptions.getItems().add(messageItem);
        }

        updateEyeGraphics.setOnAction(event -> {
            if(!overlayMode) {
                splitBottom.getChildren().clear();
            }
            eyeMessageOptions.setText("Selected: CUSTOM");
            String rawText = eyeRawText.getText().replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "");
            EyeMessage custom = new EyeMessage(rawText);
            custom.setDisplayPane(splitBottom);
        });
    }

    private void setupOverlayMode(){
        overlayEyes.setOnAction(event -> {
            overlayMode = overlayEyes.isSelected();
            if (!overlayEyes.isSelected()){
                displayGrid.getChildren().clear();
            }
        });
    }

    private void setupOutputShift(){
        outputToInput.setOnAction(event -> {
            String output = outputTextPane.getText();
            eyeRawText.setText(output);
            outputTextPane.clear();
        });
    }
    private void setupTrigrams(){

    }

}
