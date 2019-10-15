import javafx.application.Application;
import javafx.stage.Stage;

public class TranslateApp extends Application{

	private View myView;

	public TranslateApp()
	{
		myView = new View();
	}
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		myView.setStage(primaryStage);
	}
}
