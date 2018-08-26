package at.fikar.raphael.cashhelper.gui.javafx.stages;

import at.fikar.raphael.cashhelper.api.AccountAPI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.inject.Inject;

public class AccountCreationStage implements IStage{

    private final AccountAPI accountAPI;

    @Inject
    public AccountCreationStage(final AccountAPI accountAPI){
        this.accountAPI = accountAPI;
    }

    @Override
    public Stage getStage(){
        Stage stage = new Stage();
        Scene scene = new Scene(new VBox(), 800, 600);
        stage.setScene(scene);

        TextField accountName = new TextField("AccountName");
        Button createButton = new Button("Create");
        createButton.setDefaultButton(true);
        createButton.setOnAction(event -> {
            accountAPI.createNewAccount(accountName.getText());
            stage.hide();
        });

        ((VBox)scene.getRoot()).getChildren().addAll(accountName, createButton);

        return stage;
    }
}
