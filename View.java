import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View
{
	private Stage myStage;
	private TranslateScene myTranslateScene;

	public View()
	{
		myTranslateScene = new TranslateScene(new BorderPane(), "English", "Spanish");
	}

	public Stage getStage()
	{
		return myStage;
	}

	public void setStage(Stage stage)
	{
		myStage = stage;
		myStage.setTitle("Translator");
		
		
		myStage.setWidth(1300);
		myStage.setHeight(700);
		myStage.setScene(myTranslateScene);

		stage.show();
	}
}