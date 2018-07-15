import Models.Node;
import Models.Queues.Queue;
import Models.Queues.QueueArray;
import Models.Queues.QueueArrayCircularVirtualOverflow;
import Models.Queues.QueueArrayShiftingVirtualOverflow;

/*
public class Main extends Application implements EventHandler<ActionEvent> {

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        QueueArray qa = new QueueArray();
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        qa.enque(new Node());
        System.out.println(qa.getLength());

        window = primaryStage;
        window.setTitle("Data Structure Visualization");
        window.setFullScreen(true);

        Scene scene = new StackScene(new VBox(),600,600, window);
        window.setScene(scene);

        window.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
*/
public class Main {

    public static void main(String[] args){
        QueueArrayShiftingVirtualOverflow qa = new QueueArrayShiftingVirtualOverflow(14);

        for (int i=0; i<14; i++){
            qa.enqueue(new Node());
        }
        System.out.println("The length of the array is: " + qa.getLength());

        qa.dequeue();
        qa.dequeue();
        System.out.println("The length of the array is: " + qa.getLength());

        for (int i=0; i<14; i++)
        {
            System.out.println(qa.getArray()[i]);
        }

        System.out.print("Head: ");
        System.out.println(qa.getHead());
        System.out.print("Tail: ");
        System.out.println(qa.getTail());

        qa.enqueue(new Node());

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for (int i=0; i<14; i++)
        {
            System.out.println(qa.getArray()[i]);
        }
        System.out.print("Head: ");
        System.out.println(qa.getHead());
        System.out.print("Tail: ");
        System.out.println(qa.getTail());

        qa.enqueue(new Node());


        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


        for (int i=0; i<14; i++)
        {
            System.out.println(qa.getArray()[i]);
        }
        System.out.print("Head: ");
        System.out.println(qa.getHead());
        System.out.print("Tail: ");
        System.out.println(qa.getTail());

        qa.enqueue(new Node());

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        for (int i=0; i<14; i++)
        {
            System.out.println(qa.getArray()[i]);
        }
        System.out.print("Head: ");
        System.out.println(qa.getHead());
        System.out.print("Tail: ");
        System.out.println(qa.getTail());
        System.out.println("ok");
    }
}
