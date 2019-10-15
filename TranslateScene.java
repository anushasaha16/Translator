import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class TranslateScene extends Scene
{
	private BorderPane myRoot;
	private String myLang1;
	private String myLang2;
	private String myText;
	private Translator myTranslator;

	public TranslateScene(Parent root, String lang1, String langg2) {
		super(root);
		myRoot = (BorderPane) root;
		myRoot.setStyle("-fx-background-color: PowderBlue");
		myTranslator = new Translator();


		myLang1 = lang1;
		myLang2 = langg2;

		BorderPane titleBox = new BorderPane();
		titleBox.setPrefHeight(100);
		myRoot.setTop(titleBox);

		Text title = new Text("Translator");
		title.setFont(new Font(40));
		titleBox.setCenter(title);

		HBox contents = new HBox();
		contents.setSpacing(10);
		contents.setPadding(new Insets(10));
		contents.setAlignment(Pos.TOP_CENTER);
		VBox lang = new VBox();
		ComboBox<String> langOptions = new ComboBox<>();
		langOptions.setPrefSize(100, 30);
		langOptions.setStyle("-fx-background-color: White");
		langOptions.setPromptText(myLang1);
		for(int i = 0; i < myTranslator.getLanguages().length; i++)
		{
			langOptions.getItems().add(myTranslator.getLanguages()[i]);
		}
		lang.getChildren().add(langOptions);
		TextArea input = new TextArea();
		input.setWrapText(true);
		input.setPrefSize(550, 400);
		lang.getChildren().add(input);
		contents.getChildren().add(lang);

		Button switchButton = new Button("-->" + "\n<--");
		switchButton.setFont(new Font(7.5));
		switchButton.setPrefSize(50, 30);
		switchButton.setStyle("-fx-background-color: White");
		switchButton.setTextFill(Color.BLUE);
		contents.getChildren().add(switchButton);

		VBox lang2 = new VBox();
		ComboBox<String> lang2Options = new ComboBox<>();
		lang2Options.setPrefSize(100, 30);
		lang2Options.setStyle("-fx-background-color: White");
		lang2Options.setPromptText(myLang2);
		for(int i = 0; i < myTranslator.getLanguages().length; i++)
		{
			lang2Options.getItems().add(myTranslator.getLanguages()[i]);
		}
		lang2.getChildren().add(lang2Options);
		TextArea input2 = new TextArea();
		input2.setPrefSize(550, 400);
		lang2.getChildren().add(input2);
		contents.getChildren().add(lang2);

		myRoot.setCenter(contents);

		BorderPane bottomBox = new BorderPane();
		bottomBox.setPrefHeight(100);
		myRoot.setBottom(bottomBox);


		Button translateButton = new Button("Translate");
		translateButton.setPrefSize(150, 50);
		translateButton.setFont(new Font(20));
		translateButton.setStyle("-fx-background-color: Pink");
		bottomBox.setCenter(translateButton);

	/*******************************Back End*******************************************************************************/
		langOptions.setOnAction(e -> {
			String l = langOptions.getSelectionModel().getSelectedItem();
			myLang1 = l;
		});

		lang2Options.setOnAction(e -> {
			String l = lang2Options.getSelectionModel().getSelectedItem();;
			myLang2 = l;
		});

		switchButton.setOnAction(e -> {
			String temp = myLang1;
			myLang1 = myLang2;
			myLang2 = temp;
			langOptions.setPromptText(myLang1);
			lang2Options.setPromptText(myLang2);
		});

		translateButton.setOnAction(e -> {
			String i = input.getText();
			myText = i;
			String tr = myTranslator.translate(myLang1, myLang2, myText);
			input2.setText(tr);
		});
	}

	public void setLang1(String lang) {myLang1 = lang;}

	public void setLang2(String lang) {myLang2 = lang;}


}